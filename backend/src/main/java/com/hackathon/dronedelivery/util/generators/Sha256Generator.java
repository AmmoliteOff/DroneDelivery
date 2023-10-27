package com.hackathon.dronedelivery.util.generators;


import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Generator {
    public static String generate(String string) {
        string+="someSalt";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(string.getBytes(StandardCharsets.UTF_8));
        String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        return sha256;
    }
}