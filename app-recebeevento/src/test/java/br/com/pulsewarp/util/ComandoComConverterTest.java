package br.com.pulsewarp.util;

import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;
import org.apache.avro.generic.GenericRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ComandoComConverterTest {

    @Test
    void testPagamento() {
        // Configurar o mock do GenericRecord
        GenericRecord record = Mockito.mock(GenericRecord.class);
        when(record.get("numContrato")).thenReturn("123456");
        when(record.get("valorComVista")).thenReturn(new BigDecimal("1000.50"));
        when(record.get("dataContrato")).thenReturn("2023-05-15");
        when(record.get("dataEfetiva")).thenReturn("2023-05-20");
        when(record.get("siglaOrigem")).thenReturn("A");

        // Criar o conversor
        ComandoComConverter converter = new ComandoComConverter();

        // Executar o método de pagamento
        ComandoComissaoPagamento pagamento = converter.pagamento(record);

        // Verificar se os dados foram convertidos corretamente
        assertEquals("123456", pagamento.getNumContrato());
        assertEquals("1000.50", pagamento.getValorComVista());
        assertEquals("2023-05-15", pagamento.getDataContrato());
        assertEquals("2023-05-20", pagamento.getDataEfetiva());
        assertEquals("A", pagamento.getSiglaOrigem());
    }

    @Test
    void testPagamentoGenericRecordNulo() {
        ComandoComConverter converter = new ComandoComConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.pagamento(null));
    }

    @Test
    void testEstorno() {
        // Configurar o mock do GenericRecord
        GenericRecord record = Mockito.mock(GenericRecord.class);
        when(record.get("numContrato")).thenReturn("654321");
        when(record.get("valorComVista")).thenReturn(new BigDecimal("500.75"));
        when(record.get("dataContrato")).thenReturn("2022-10-20");
        when(record.get("dataEstorno")).thenReturn("2022-11-05");
        when(record.get("siglaOrigem")).thenReturn("B");

        // Criar o conversor
        ComandoComConverter converter = new ComandoComConverter();

        // Executar o método de estorno
        ComandoComissaoEstorno estorno = converter.estorno(record);

        // Verificar se os dados foram convertidos corretamente
        assertEquals("654321", estorno.getNumContrato());
        assertEquals("500.75", estorno.getValorComVista());
        assertEquals("2022-10-20", estorno.getDataContrato());
        assertEquals("2022-11-05", estorno.getDataEstorno());
        assertEquals("B", estorno.getSiglaOrigem());
    }

    @Test
    void testEstornoGenericRecordNulo() {
        ComandoComConverter converter = new ComandoComConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.estorno(null));
    }

}