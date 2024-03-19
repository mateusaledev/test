package br.com.pulsewarp.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConversorUtilTest {

    @Test
    void testConvertLongToString() {
        assertEquals("123", ConversorUtil.convertLongToString(123L));
        assertEquals("-456", ConversorUtil.convertLongToString(-456L));
        assertEquals(null, ConversorUtil.convertLongToString(null));
    }

    @Test
    void testConverteCharsequenceParaString() {
        assertEquals("Test", ConversorUtil.converteCharsequenceParaString("Test"));
        assertEquals("", ConversorUtil.converteCharsequenceParaString(""));
        assertEquals(null, ConversorUtil.converteCharsequenceParaString(null));
    }

    @Test
    void testConverteCharsequenceParaLong() {
        assertEquals(123L, ConversorUtil.converteCharsequenceParaLong("123"));
        assertEquals(-456L, ConversorUtil.converteCharsequenceParaLong("-456"));
        assertEquals(null, ConversorUtil.converteCharsequenceParaLong(null));
    }

    @Test
    void testConverteIntParaLong() {
        assertEquals(123L, ConversorUtil.converteIntParaLong(123));
        assertEquals(-456L, ConversorUtil.converteIntParaLong(-456));
        assertEquals(null, ConversorUtil.converteIntParaLong(null));
    }

    @Test
    void testConverteCharSequenceParaInteger() {
        assertEquals(123, ConversorUtil.converteCharSequenceParaInteger("123"));
        assertEquals(-456, ConversorUtil.converteCharSequenceParaInteger("-456"));
        assertEquals(null, ConversorUtil.converteCharSequenceParaInteger(null));
    }

    @Test
    void testConverteFloatParaBigDecimal() {
        assertEquals(new BigDecimal("123.45"), ConversorUtil.converteFloatParaBigDecimal(123.45f));
        assertEquals(new BigDecimal("-67.89"), ConversorUtil.converteFloatParaBigDecimal(-67.89f));
        assertEquals(null, ConversorUtil.converteFloatParaBigDecimal(null));
    }

    @Test
    void testConverteCharSequenceParaChar() {
        assertEquals('A', ConversorUtil.converteCharSequenceParaChar("ABC"));
        assertEquals('1', ConversorUtil.converteCharSequenceParaChar("123"));
        assertEquals(null, ConversorUtil.converteCharSequenceParaChar(null));
        assertEquals(null, ConversorUtil.converteCharSequenceParaChar(""));
    }

    @Test
    void testConverteDataParaDiaInteger() {
        assertEquals(15, ConversorUtil.converteDataParaDiaInteger("15-01-2022"));
        assertEquals(1, ConversorUtil.converteDataParaDiaInteger("01-12-2023"));
        assertEquals(null, ConversorUtil.converteDataParaDiaInteger(null));
    }

    @Test
    void testConverteDoubleParaBigDecimal() {
        assertEquals(new BigDecimal("123.45"), ConversorUtil.converteDoubleParaBigDecimal(123.45));
        assertEquals(new BigDecimal("-67.89"), ConversorUtil.converteDoubleParaBigDecimal(-67.89));
        assertEquals(null, ConversorUtil.converteDoubleParaBigDecimal(null));
    }

    @Test
    void testConverteBigDecimalParaDouble() {
        assertEquals(123.45, ConversorUtil.converteBigDecimalParaDouble(new BigDecimal("123.45")));
        assertEquals(-67.89, ConversorUtil.converteBigDecimalParaDouble(new BigDecimal("-67.89")));
        assertEquals(0.0, ConversorUtil.converteBigDecimalParaDouble(BigDecimal.ZERO));
        assertEquals(null, ConversorUtil.converteBigDecimalParaDouble(null));
    }

    @Test
    void testConverteIntegerParaString() {
        assertEquals("123", ConversorUtil.converteIntegerParaString(123));
        assertEquals("-456", ConversorUtil.converteIntegerParaString(-456));
        assertEquals(null, ConversorUtil.converteIntegerParaString(null));
    }

    @Test
    void testConverteLongParaInteger() {
        assertEquals(123, ConversorUtil.converteLongParaInteger(123L));
        assertEquals(-456, ConversorUtil.converteLongParaInteger(-456L));
        assertEquals(null, ConversorUtil.converteLongParaInteger(null));
    }

    @Test
    void testConvertStringToLong() {
        assertEquals(123L, ConversorUtil.convertStringToLong("123"));
        assertEquals(-456L, ConversorUtil.convertStringToLong("-456"));
        assertEquals(null, ConversorUtil.convertStringToLong(null));
        assertEquals(null, ConversorUtil.convertStringToLong("abc")); // Teste para valor inválido
    }

    @Test
    void testConverteCharSeqParaDataDiaMesAnoComFormat() {
        assertEquals("31-12-2022", ConversorUtil.converteCharSeqParaDataDiaMesAnoComFormat("2022-12-31", "dd-MM-yyyy"));
        assertEquals(null, ConversorUtil.converteCharSeqParaDataDiaMesAnoComFormat(null, "dd-MM-yyyy"));
    }

    @Test
    void testConverteMapObjectParaString() {
        Map<CharSequence, Object> originalMap = new HashMap<>();
        originalMap.put("key1", 123);
        originalMap.put("key2", "value");
        originalMap.put("key3", 45.67);

        Map<CharSequence, String> convertedMap = ConversorUtil.convertMapObjectParaString(originalMap);
        assertEquals("123", convertedMap.get("key1"));
        assertEquals("value", convertedMap.get("key2"));
        assertEquals("45.67", convertedMap.get("key3"));
    }

    @Test
    void testConvertCharSeqToFormattedDate() {
        assertEquals("31-12-2022", ConversorUtil.convertCharSeqToFormattedDate("2022-12-31", "dd-MM-yyyy"));
        assertNull(ConversorUtil.convertCharSeqToFormattedDate(null, "dd-MM-yyyy"));
    }

    @Test
    void testConvertMapObjectToString() {
        Map<CharSequence, Object> originalMap = new HashMap<>();
        originalMap.put("key1", 123);
        originalMap.put("key2", "value");
        originalMap.put("key3", 45.67);

        Map<CharSequence, String> convertedMap = ConversorUtil.convertMapObjectToString(originalMap);
        assertEquals("123", convertedMap.get("key1"));
        assertEquals("value", convertedMap.get("key2"));
        assertEquals("45.67", convertedMap.get("key3"));
    }

}