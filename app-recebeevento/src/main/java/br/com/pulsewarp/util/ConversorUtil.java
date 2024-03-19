package br.com.pulsewarp.util;

import br.com.pulsewarp.domains.TipoDado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public final class ConversorUtil {

    private static final String DATA_ANO_MES_DIA = "yyyy-MM-dd";
    private static final String DATA_DIA_MES_ANO = "dd-MM-yyyy";
    private static final String DATA_DIA_MES_ANO_SEM_TRACO = "ddMMyyyy";
    private static final String DATA_DIA_MES_ANO_SEP_PONTO = "dd.MM.yyyy";


    private ConversorUtil() {
    }

    public static String convertLongToString(Long value) {
        return (value != null) ? String.valueOf(value) : null;
    }

    public static String converteCharsequenceParaString(CharSequence valor) {
        if (valor == null) {
            return null;
        }
        return String.valueOf(valor);
    }

    public static Long converteCharsequenceParaLong(CharSequence valor) {
        if (valor == null) {
            return null;
        }
        return Long.parseLong(String.valueOf(valor));
    }

    public static Long converteIntParaLong(Integer valor) {
        if (valor == null) {
            return null;
        }
        return valor.longValue();
    }

    public static Integer converteCharSequenceParaInteger(CharSequence valor) {
        if (valor == null) {
            return null;
        }
        return Integer.parseInt(String.valueOf(valor));
    }

    public static BigDecimal converteFloatParaBigDecimal(Float valor) {
        if (valor == null) {
            return null;
        }
        return new BigDecimal(Float.toString(valor));
    }

    public static Character converteCharSequenceParaChar(CharSequence valor) {
        if (valor == null || valor.isEmpty()) {
            return null;
        }

        return valor.charAt(0);
    }

    public static Integer converteDataParaDiaInteger(CharSequence valor) {
        if (valor == null) {
            return null;
        }

        return Integer.parseInt(String.valueOf(valor).substring(0, 2));
    }

    public static BigDecimal converteDoubleParaBigDecimal(Double valor) {
        if (valor == null) {
            return null;
        }
        return new BigDecimal(Double.toString(valor));
    }

    public static Double converteBigDecimalParaDouble(BigDecimal valor) {
        if (valor == null) {
            return null;
        }
        return valor.doubleValue();
    }

    public static String converteIntegerParaString(Integer valor) {
        if (valor == null) {
            return null;
        }
        return String.valueOf(valor);
    }

    public static Integer converteLongParaInteger(Long valor) {
        if (valor == null) {
            return null;
        }
        return valor.intValue();
    }

    public static Long convertStringToLong(String value) {
        try {
            return (value != null) ? Long.parseLong(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Métodos de conversão utilizando enums para tipos padrão
    public static String convertObjectToString(Object value, TipoDado tipo) {
        if (value == null || tipo == null) {
            return null;
        }

        switch (tipo) {
            case STRING:
                return String.valueOf(value);
            case INTEGER:
                return convertToInteger(value);
            case LONG:
                return convertToLong(value);
            case FLOAT:
                return convertToFloat(value);
            case DOUBLE:
                return convertToDouble(value);
            case BIG_DECIMAL:
                return convertToBigDecimal(value);
            case CHARACTER:
                return convertToCharacter(value);
            case DATE:
                return convertToDate(value);
            default:
                return null;
        }
    }

    private static String convertToInteger(Object value) {
        if (value instanceof Integer) {
            return String.valueOf((Integer) value);
        } else if (value instanceof String) {
            return String.valueOf(Integer.parseInt((String) value));
        }
        return null;
    }

    private static String convertToLong(Object value) {
        if (value instanceof Long) {
            return String.valueOf((Long) value);
        } else if (value instanceof String) {
            return String.valueOf(Long.parseLong((String) value));
        }
        return null;
    }

    private static String convertToFloat(Object value) {
        if (value instanceof Float) {
            return String.valueOf((Float) value);
        } else if (value instanceof String) {
            return String.valueOf(Float.parseFloat((String) value));
        }
        return null;
    }

    private static String convertToDouble(Object value) {
        if (value instanceof Double) {
            return String.valueOf((Double) value);
        } else if (value instanceof String) {
            return String.valueOf(Double.parseDouble((String) value));
        }
        return null;
    }

    private static String convertToBigDecimal(Object value) {
        if (value instanceof BigDecimal) {
            return value.toString();
        } else if (value instanceof String) {
            return String.valueOf(new BigDecimal((String) value));
        }
        return null;
    }

    private static String convertToCharacter(Object value) {
        if (value instanceof Character) {
            return String.valueOf((Character) value);
        } else if (value instanceof String && !((String) value).isEmpty()) {
            return String.valueOf(((String) value).charAt(0));
        }
        return null;
    }

    private static String convertToDate(Object value) {
        if (value instanceof LocalDate) {
            return ((LocalDate) value).format(DateTimeFormatter.ofPattern(DATA_DIA_MES_ANO_SEP_PONTO));
        }
        return null;
    }

    public static String converteCharsequenceParaDataDiaMesAnoSemTraco(CharSequence valor) {
        return converteCharSeqParaDataDiaMesAnoComFormat(valor, DATA_DIA_MES_ANO_SEM_TRACO);
    }

    public static String converteCharsequenceParaDataDiaMesAno(CharSequence valor) {
        return converteCharSeqParaDataDiaMesAnoComFormat(valor, DATA_DIA_MES_ANO);
    }

    public static String converteCharsequenceParaDataDiaMesAnoSeparadorPonto(CharSequence valor) {
        return converteCharSeqParaDataDiaMesAnoComFormat(valor, DATA_DIA_MES_ANO_SEP_PONTO);
    }

    public static String converteLocalDateParaDataDiaMesAnoSeparadorPonto(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATA_DIA_MES_ANO_SEP_PONTO));
    }

    public static String converteCharSeqParaDataDiaMesAnoComFormat(CharSequence valor, String pattern) {
        if (valor == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatterOrigem = DateTimeFormatter.ofPattern(DATA_ANO_MES_DIA);
        DateTimeFormatter dateTimeFormatterDestino = DateTimeFormatter.ofPattern(pattern);
        LocalDate data = LocalDate.parse(String.valueOf(valor), dateTimeFormatterOrigem);
        return data.format(dateTimeFormatterDestino);
    }

    public static Map<CharSequence, String> convertMapObjectParaString(Map<CharSequence, Object> mapOriginal) {
        Map<CharSequence, String> mapConvertido = new HashMap<>();

        for (Map.Entry<CharSequence, Object> entry : mapOriginal.entrySet()) {
            String valorConvertido = String.valueOf(entry.getValue());
            mapConvertido.put(entry.getKey(), valorConvertido);
        }
        return mapConvertido;
    }

    public static String convertCharSeqToFormattedDate(CharSequence value, String pattern) {
        if (value == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatterOrigem = DateTimeFormatter.ofPattern(DATA_ANO_MES_DIA);
        DateTimeFormatter dateTimeFormatterDestino = DateTimeFormatter.ofPattern(pattern);
        LocalDate data = LocalDate.parse(String.valueOf(value), dateTimeFormatterOrigem);
        return data.format(dateTimeFormatterDestino);
    }

    // Método para converter um Map<CharSequence, Object> para Map<CharSequence, String>
    public static Map<CharSequence, String> convertMapObjectToString(Map<CharSequence, Object> originalMap) {
        Map<CharSequence, String> convertedMap = new HashMap<>();

        for (Map.Entry<CharSequence, Object> entry : originalMap.entrySet()) {
            String convertedValue = String.valueOf(entry.getValue());
            convertedMap.put(entry.getKey(), convertedValue);
        }
        return convertedMap;
    }
}
