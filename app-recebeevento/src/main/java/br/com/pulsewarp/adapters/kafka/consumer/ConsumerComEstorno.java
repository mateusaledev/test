package br.com.pulsewarp.adapters.kafka.consumer;

import br.com.pulsewarp.application.exception.KafkaExceptionConsumer;
import br.com.pulsewarp.application.service.ComUseCase;
import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.util.ComandoComConverter;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Service
public class ConsumerComEstorno {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ConsumerComEstorno.class);
    private final KafkaExceptionConsumer kafkaExceptionConsumer;

    private final ComandoComConverter converter;
    private final ComUseCase comUseCase;

    public ConsumerComEstorno(KafkaExceptionConsumer kafkaExceptionConsumer, ComandoComConverter converter, ComUseCase comUseCase) {
        this.kafkaExceptionConsumer = kafkaExceptionConsumer;
        this.converter = converter;
        this.comUseCase = comUseCase;
    }

    @KafkaListener(
            topics = "${topic.comando_comissao_estorno.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consumeEstorno(@NotNull ConsumerRecord<String, GenericRecord> payload,
                               @Headers Map<String, Object> headers,
                               Acknowledgment ack) {
        try {
            LOGGER.info("Iniciando o processamento da mensagem do Kafka: {}", payload);

            ComandoComissaoEstorno estorno = converter.estorno(payload.value());
            validarEstorno(estorno);

            comUseCase.estorno(estorno);

            LOGGER.info("Mensagem do Kafka processada com sucesso: {}", payload);
            ack.acknowledge();
        } catch (ListenerExecutionFailedException e) {
            LOGGER.error("Erro ao processar mensagem do Kafka: {}", e.getCause().getMessage(), e.getCause());
            kafkaExceptionConsumer.handleExecutionFailedException(e);
        } catch (Exception e) {
            LOGGER.error("Erro ao processar mensagem do Kafka: {}", e.getMessage(), e);
            kafkaExceptionConsumer.handleGeneralException(e);
        }
    }

    private void validarEstorno(@Valid ComandoComissaoEstorno estorno) {
        if (estorno.getNumContrato() == null || estorno.getNumContrato().isEmpty()) {
            throw new IllegalArgumentException("Número do contrato é obrigatório");
        }

        if (estorno.getValorComVista() == null || estorno.getValorComVista().isEmpty()) {
            throw new IllegalArgumentException("Valor comissão a vista é obrigatório");
        }
    }
}