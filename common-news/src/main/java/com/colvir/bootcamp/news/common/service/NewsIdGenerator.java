package com.colvir.bootcamp.news.common.service;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class NewsIdGenerator {

    public String generateNewsId(NewsDto newsDto) {
        return generateMD5(newsDto.getLink());
    }

    public String generateNewsId(String link) {
        return generateMD5(link);
    }

    /**
     * Generates an MD5 hash for the given link string.
     *
     * @param link the string to hash
     * @return the MD5 hash as a hexadecimal string
     */
    public static String generateMD5(String link) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(link.getBytes());

            // Convert byte array into signum representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // pad with leading zero
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }

}
