package site.klol.app.common.utils;

import lombok.experimental.UtilityClass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@UtilityClass
public class SHA256 {

    // 평문 텍스트를 SHA-256 해시로 변환하는 메서드 회원가입 시 사용
    public static String encrypt(String plainText) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(plainText.getBytes());

            // 바이트 데이터를 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(b & 0xFF); // 바이트를 0~255로 변환
                if (hex.length() == 1) {
                    hexString.append('0'); // 한 자리 16진수는 앞에 '0'을 붙임
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while encrypting data: " + e.getMessage(), e);
        }
    }

    // 해시값을 비교하는 메서드 로그인 시 사용
    public static boolean verify(String plainText, String hashedText) {
        // 입력된 평문 텍스트를 해시하고, 저장된 해시와 비교
        String inputHash = encrypt(plainText);
        return inputHash.equals(hashedText);
    }
}
