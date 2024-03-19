package br.com.pulsewarp.domains;

public enum TipoDado {
    STRING,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    BIG_DECIMAL,
    CHARACTER,
    DATE;

    // Método utilitário para converter string para enum
    public static TipoDado fromString(String text) {
        for (TipoDado tipo : TipoDado.values()) {
            if (tipo.name().equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        return null;
    }
}

