package br.com.pulsewarp.adapters.config.kafka;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.RetryListener;
import org.springframework.util.backoff.FixedBackOff;

import java.time.Duration;
import java.util.Map;
import java.util.logging.Logger;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        properties.put(KafkaAvroDeserializerConfig.VALUE_SUBJECT_NAME_STRATEGY, TopicRecordNameStrategy.class);
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean(name = "concurrentKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setConsumerFactory(consumerFactory);
        factory.setContainerCustomizer(container -> container.getContainerProperties().setAuthExceptionRetryInterval(Duration.ofMinutes(5L)));
        factory.setCommonErrorHandler(defaultErrorHandler());
        return factory;
    }

    @Bean
    public CommonErrorHandler defaultErrorHandler() {
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((((consumerRecord, exception) -> {
        })));
        errorHandler.setBackOffFunction(((consumerRecord, exception) -> new FixedBackOff(1000L, 5)));

        errorHandler.setRetryListeners(new RetryListener() {
            @Override
            public void failedDelivery(ConsumerRecord<?, ?> consumerRecord, Exception e, int i) {
                Logger.getLogger("failedDelivery");
            }

            @Override
            public void recovered(ConsumerRecord<?, ?> myRecord, Exception ex) {
                Logger.getLogger("recovered");
            }

            @Override
            public void recoveryFailed(ConsumerRecord<?, ?> myRecord, Exception original, Exception failure) {
                Logger.getLogger("recovery");
            }
        });
        return errorHandler;
    }
}
