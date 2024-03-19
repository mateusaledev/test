package br.com.pulsewarp.adapters.kafka.consumer;

import br.com.pulsewarp.application.exception.KafkaExceptionConsumer;
import br.com.pulsewarp.application.service.ComUseCase;
import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.util.ComandoComConverter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.support.Acknowledgment;

import java.util.HashMap;

import static org.mockito.Mockito.*;

class ConsumerComEstornoTest {

    @Mock
    private KafkaExceptionConsumer kafkaExceptionConsumer;

    @Mock
    private ComandoComConverter converter;

    @Mock
    private ComUseCase comUseCase;

    @InjectMocks
    private ConsumerComEstorno consumerComEstorno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsumeEstorno_Success() throws Throwable {
        // Criar um objeto ComandoComissaoEstorno de teste
        ComandoComissaoEstorno estorno = new ComandoComissaoEstorno();
        estorno.setNumContrato("123");
        estorno.setValorComVista("100.00");

        // Simular o comportamento do converter.estorno()
        when(converter.estorno(any())).thenReturn(estorno);

        // Simular o comportamento do comUseCase.estorno()
        doNothing().when(comUseCase).estorno(estorno);

        // Simular o comportamento do ack.acknowledge()
        Acknowledgment ack = mock(Acknowledgment.class);

        // Chamar o método de teste
        consumerComEstorno.consumeEstorno(createConsumerRecord(), new HashMap<>(), ack);

        // Verificar se o método handleExecutionFailedException não foi chamado
        verify(kafkaExceptionConsumer, never()).handleExecutionFailedException(any());

        // Verificar se o método ack.acknowledge() foi chamado
        verify(ack, times(1)).acknowledge();
    }

    @Test
    void testConsumeEstorno_InvalidContractNumber() throws Throwable {
        // Criar um objeto ComandoComissaoEstorno de teste com número de contrato inválido
        ComandoComissaoEstorno estorno = new ComandoComissaoEstorno();
        estorno.setValorComVista("100.00");

        // Simular o comportamento do converter.estorno()
        when(converter.estorno(any())).thenReturn(estorno);

        // Chamar o método de teste
        consumerComEstorno.consumeEstorno(createConsumerRecord(), new HashMap<>(), mock(Acknowledgment.class));

        // Verificar se o método handleGeneralException foi chamado
        verify(kafkaExceptionConsumer, times(1)).handleGeneralException(any());
    }

    @Test
    void testConsumeEstorno_MissingCommissionValue() throws Throwable {
        // Criar um objeto ComandoComissaoEstorno de teste com valor da comissão em falta
        ComandoComissaoEstorno estorno = new ComandoComissaoEstorno();
        estorno.setNumContrato("123");

        // Simular o comportamento do converter.estorno()
        when(converter.estorno(any())).thenReturn(estorno);

        // Chamar o método de teste
        consumerComEstorno.consumeEstorno(createConsumerRecord(), new HashMap<>(), mock(Acknowledgment.class));

        // Verificar se o método handleExecutionFailedException foi chamado
        verify(kafkaExceptionConsumer, times(1)).handleGeneralException(any());
    }

    private ConsumerRecord<String, GenericRecord> createConsumerRecord() {
        // Simular um ConsumerRecord
        GenericRecord record = new GenericData.Record(new org.apache.avro.Schema.Parser().parse("{ \"type\": \"record\", \"name\": \"test\", \"fields\": [ {\"name\": \"field\", \"type\": \"string\"} ] }"));
        record.put("field", "value");
        return new ConsumerRecord<>("test-topic", 0, 0, "key", record);
    }
}
