package br.com.pulsewarp.util;

import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;
import br.com.pulsewarp.domains.TipoDado;
import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ComandoComConverter {
    public ComandoComissaoPagamento pagamento(GenericRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("GenericRecord não pode ser nulo.");
        }

        // Validar e extrair dados do GenericRecord de forma segura usando ConversorUtil
        String numContrato = ConversorUtil.convertObjectToString(record.get("numContrato"), TipoDado.STRING);
        String valorComVista = ConversorUtil.convertObjectToString(record.get("valorComVista"), TipoDado.BIG_DECIMAL);
        String dataContrato = ConversorUtil.convertObjectToString(record.get("dataContrato"), TipoDado.STRING);
        String dataEfetiva = ConversorUtil.convertObjectToString(record.get("dataEfetiva"), TipoDado.STRING);
        String siglaOrigem = ConversorUtil.convertObjectToString(record.get("siglaOrigem"), TipoDado.STRING);

        // Construir e retornar ComandoComissaoPagamento
        return ComandoComissaoPagamento.builder()
                .numContrato(numContrato)
                .valorComVista(valorComVista)
                .dataContrato(dataContrato)
                .dataEfetiva(dataEfetiva)
                .siglaOrigem(siglaOrigem)
                .build();
    }

    public ComandoComissaoEstorno estorno(GenericRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("GenericRecord não pode ser nulo.");
        }

        // Validar e extrair dados do GenericRecord de forma segura usando ConversorUtil
        String numContrato = ConversorUtil.convertObjectToString(record.get("numContrato"), TipoDado.STRING);
        String valorComVista = ConversorUtil.convertObjectToString(record.get("valorComVista"), TipoDado.STRING);
        String dataContrato = ConversorUtil.convertObjectToString(record.get("dataContrato"), TipoDado.STRING);
        String dataEstorno = ConversorUtil.convertObjectToString(record.get("dataEstorno"), TipoDado.STRING);
        String siglaOrigem = ConversorUtil.convertObjectToString(record.get("siglaOrigem"), TipoDado.STRING);

        // Construir e retornar ComandoComissaoEstorno
        return ComandoComissaoEstorno.builder()
                .numContrato(numContrato)
                .valorComVista(valorComVista)
                .dataContrato(dataContrato)
                .dataEstorno(dataEstorno)
                .siglaOrigem(siglaOrigem)
                .build();
    }

}