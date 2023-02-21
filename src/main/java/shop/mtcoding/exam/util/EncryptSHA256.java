package shop.mtcoding.exam.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptSHA256 {

    public static String encryptSHA256(String str) {
        String sha = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // sh.update(str.getBytes());
            // byte[] byteData = sh.digest();
            // StringBuilder sb = new StringBuilder();
            // for (byte byteDatum : byteData) {
            // sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));}

            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : hash) {
                String hex = Integer.toHexString(0xff & byteDatum);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }

            sha = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("암호화 에러-NoSuchAlgorithmException");
            sha = null;
        }
        return sha;
    }
}
