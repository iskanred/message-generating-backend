package dev.iskandev.messagegeneratingbackend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void generateRandomAlphaNumeric() {
        String str = StringUtils.generateRandomAlphaNumeric(100);
        assertEquals(100, str.length());
        for (int i = 0; i < str.length(); ++i) {
            assertTrue(StringUtils.alphanumeric.contains(Character.toString(str.charAt(i))));
        }
    }

    @Test
    void computeSHA256() {
        String test1 = "";
        String test2 = "abcde";
        String test3 = "jf28jh3j2dfre";
        String testNull = null;

        assertEquals("", StringUtils.computeSHA256(test1));
        assertEquals("36BBE50ED96841D10443BCB670D6554F0A34B761BE67EC9C4A8AD2C0C44CA42C",
                StringUtils.computeSHA256(test2));
        assertEquals("9B1A79B35856B7364FD75DD6D0F664F068901548C708B27272341C9D06E1FB50",
                StringUtils.computeSHA256(test3));
        assertThrows(IllegalArgumentException.class, () -> StringUtils.computeSHA256(testNull));
    }
}