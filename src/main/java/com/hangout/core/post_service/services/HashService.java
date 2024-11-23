package com.hangout.core.post_service.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.hangout.core.post_service.dto.User;
import com.hangout.core.post_service.exceptions.FileUploadFailed;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HashService {
    private static final String ALGORITHM = "SHA3-256";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    // used to create file name
    public String hash(User user) {
        String seedText = user.toString() + ZonedDateTime.now(ZoneOffset.UTC);
        log.debug("inuput seed text for hashing: {}", seedText);
        byte[] textBytes = seedText.getBytes(UTF_8);
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalArgumentException(ex);
        }
        byte[] result = md.digest(textBytes);
        return bytesToHex(result);
    }

    // used to calculate checksum
    public String checkSum(String filePath) throws FileUploadFailed {
        try (FileInputStream fStream = new FileInputStream(filePath)) {
            byte[] data = new byte[1024];
            MessageDigest md;
            try {
                md = MessageDigest.getInstance(ALGORITHM);
            } catch (NoSuchAlgorithmException ex) {
                throw new IllegalArgumentException(ex);
            }
            int bytesRead;
            while ((bytesRead = fStream.read(data)) != -1) {
                md.update(data, 0, bytesRead);
            }
            byte[] mdBytes = md.digest();

            String checksum = bytesToHex(mdBytes);
            log.debug("Calculated Checksum: {}", checksum);
            return checksum;
        } catch (IOException e) {
            log.error(e.toString());
            throw new FileUploadFailed("could not calculate file checksum");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X",
                    b));
        }
        return sb.toString();

    }
}
