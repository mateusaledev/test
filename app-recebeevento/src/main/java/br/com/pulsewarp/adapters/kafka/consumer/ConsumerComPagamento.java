package br.com.pulsewarp.adapters.kafka.consumer;

import br.com.pulsewarp.util.ComandoComConverter;
import br.com.pulsewarp.application.service.ComUseCase;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.MDC;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Map;

import static br.com.pulsewarp.adapters.config.kafka.KafkaHeader.getValueByKey;

@Service
@RequiredArgsConstructor
public class ConsumerComPagamento {

    private final ComandoComConverter converter;
    private final ComUseCase comUseCase;

    @KafkaListener(
            topics = "${topic.comando_comissao_pagamento.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consumer(@NotNull ConsumerRecord<String, GenericRecord> payload,
                         @Headers Map<String, Object> headers,
                         Acknowledgment ack) {
        try {
            if (payload.value() instanceof ComandoComissaoPagamento pagamentoRecord) {
                trataSolicitacaoPagamento(pagamentoRecord, payload.offset(), headers);
            }
            ack.acknowledge();
        } catch (KafkaException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            MDC.clear();
        }
    }

    private void trataSolicitacaoPagamento(@NotNull ComandoComissaoPagamento payload, long offset, @Headers Map<String, Object> headers) { // Corrigido para ComandoComPagamento
        String transactionId = getValueByKey(headers, "transactionid");

        var dados = converter.pagamento((GenericRecord) payload);

        comUseCase.pagamento(dados);
    }
}
