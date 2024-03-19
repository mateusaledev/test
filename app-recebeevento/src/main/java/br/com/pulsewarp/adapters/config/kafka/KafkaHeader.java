package br.com.pulsewarp.adapters.config.kafka;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class KafkaHeader {

    private KafkaHeader() {
    }

    public static Map<String, String> toLog(Map<String, Object> headers) {
        Map<String, String> headerMap = new HashMap<>();
        headers.forEach((key, value) -> {
            if (key != null && !key.equalsIgnoreCase("kafka_acknowledgment")) {
                String valueString = "";
                if (value != null) {
                    try {
                        if (value instanceof byte[]) {
                            valueString = new String((byte[]) value, StandardCharsets.UTF_8);
                        } else {
                            valueString = value.toString();
                        }
                    } catch (Exception e) {
                        valueString = "";
                    }
                }
                headerMap.put(key, valueString);
            }
        });
        return headerMap;
    }

    public static String getValueByKey(Map<String, Object> headers, String key) {
        return Optional.ofNullable(headers.get(key))
                .map(h -> new String((byte[]) h, StandardCharsets.UTF_8))
                .orElse(UUID.randomUUID().toString()); // Ou você pode retornar null ou outro valor padrão, se preferir
    }
}
