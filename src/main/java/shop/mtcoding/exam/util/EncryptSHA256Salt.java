package shop.mtcoding.exam.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class EncryptSHA256Salt {

    public static String getSalt() {
        // 1, random, salt 생성
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[20];
        // 2. 난수 생성
        sr.nextBytes(salt);
        // 3. byte to string(10진수 문자열로 변환)
        StringBuffer sb = new StringBuffer();
        for (byte b : salt) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }

    public static String encryptSHA256(String str, String salt) {

        String sha = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // sh.update(str.getBytes());
            // byte[] byteData = sh.digest();
            // StringBuilder sb = new StringBuilder();
            // for (byte byteDatum : byteData) {
            // sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));}

            digest.update((str + salt).getBytes());
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : hash) {
                String hex = Integer.toHexString(0xff & byteDatum);

                sb.append(String.format("%02x", byteDatum));
            }

            sha = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("암호화 에러-NoSuchAlgorithmException");
            sha = null;
        }
        return sha;
    }
}
