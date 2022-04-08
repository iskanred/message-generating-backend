package dev.iskandev.messagegeneratingbackend.util;

import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;

public class StringUtils {
    /**
     * Uppercase english letters
     */
    public static final String alphaUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Lowercase enghlish letters
     */
    public static final String alphaLower = alphaUpper.toLowerCase(Locale.ROOT);

    /**
     * Decimal digits
     */
    public static final String numeric = "0123456789";

    /**
     * Uppercase and lowercase english letters and decimal digits
     */
    public static final String alphanumeric = alphaUpper + alphaLower + numeric;

    private static final byte[] HEX_DIGITS =
            "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    /**
     * Generates string containing randomly generated
     *  alphanumeric characters of given length.
     *
     * It uses {@link java.util.Random} for generating.
     *
     * @param length is length of resulting string
     * @return string that contains randomly generated
     *  alphanumeric characters of given length
     * @throws IllegalArgumentException if length is negative
     */
    public static String generateRandomAlphaNumeric(int length) throws IllegalArgumentException {
        if (length < 0)
            throw new IllegalArgumentException("length must be non-negative");

        Random random = new Random();
        char[] result = new char[length];
        for (int index = 0; index < length; ++index) {
            result[index] = alphanumeric.charAt(random.nextInt(alphanumeric.length()));
        }
        return new String(result);
    }

    /**
     * Computes hashcode of given string using SHA-256.
     * Empty string results to empty string.
     *
     * It uses {@link java.security.MessageDigest} for computing
     *  SHA-256 haschode.
     *
     * @param str is string to compute hashcode of
     * @return string that represents hexadecimal integer which
     *  is SHA-256 hashcode of given string
     * @throws IllegalArgumentException if str is {@literal null}
     */
    public static String computeSHA256(String str) throws IllegalArgumentException {
        Assert.notNull(str, "string must be not null");
        if (str.isEmpty())
            return "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    str.getBytes(StandardCharsets.UTF_8));
            return byteArrayToHexString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("impossible exception");
        }
    }

    /**
     * Converts array of bytes to string that represents hexadecimal integer.
     *
     * @param bytes is array to convert
     * @return string that represents hexadecimal integer which is result of conversion
     */
    public static String byteArrayToHexString(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int resByte = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_DIGITS[resByte >>> 4];
            hexChars[i * 2 + 1] = HEX_DIGITS[resByte & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}
