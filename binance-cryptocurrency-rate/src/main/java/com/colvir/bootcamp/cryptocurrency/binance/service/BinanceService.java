package com.colvir.bootcamp.cryptocurrency.binance.service;

import com.colvir.bootcamp.cryptocurrency.binance.dto.CryptoRateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.longBitsToDouble;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceService {

    private final AtomicBoolean connected = new AtomicBoolean(false);
    private final AtomicLong btcToUsdtBits = new AtomicLong(-1L);

    @Value("${app.frontend.path}")
    public String frontendPath;
    @Value("${app.binance.websocket.url}")
    private String binanceWsUrl;

    private final ObjectMapper objectMapper;
    private final ReactorNettyWebSocketClient client;
    private final SimpMessagingTemplate messagingTemplate;


    @PostConstruct
    public void init() {
        connect();
    }

    @Scheduled(fixedDelayString = "${app.binance.websocket.reconnection.delay}")
    public void reconnectIfNeeded() {
        if (!connected.get()) {
            log.atInfo().log("[WebSocket] Disconnected. Attempting to reconnect...");
            connect();
        }
    }

    @Scheduled(fixedRateString = "${app.frontend.rate}")
    public void sendPriceUpdate() {
        messagingTemplate.convertAndSend(frontendPath, String.valueOf(longBitsToDouble(btcToUsdtBits.get())));
    }

    private void connect() {
        client.execute(URI.create(binanceWsUrl), session -> {
            connected.set(true);
            log.atInfo().log("[WebSocket] Connected to Binance.");
            return session.receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .doOnNext(this::handleMessage)
                    .doOnError(err -> {
                        log.atError().log("[WebSocket] Error: {}", err.getMessage());
                        connected.set(false);
                    })
                    .doFinally(signal -> {
                        log.atInfo().log("[WebSocket] Connection closed with signal: {}", signal);
                        connected.set(false);
                    })
                    .then();
        }).subscribe();
    }

    private void handleMessage(String message) {
        try {
            CryptoRateDto dto = objectMapper.readValue(message, CryptoRateDto.class);
            btcToUsdtBits.set(doubleToLongBits(dto.getPrice()));
            log.atTrace().log(() -> "Received exchange rate: " + dto);

        } catch (Exception e) {
            log.error("Error parsing message: {}", e.getMessage(), e);
        }
    }

}
