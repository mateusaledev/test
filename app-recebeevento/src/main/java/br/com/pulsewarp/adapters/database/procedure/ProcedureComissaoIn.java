package br.com.pulsewarp.adapters.database.procedure;

import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;


public interface ProcedureComissaoIn {

    void comissaoPagamento(ComandoComissaoPagamento comissaoPagamento);

    void comissaoEstorno(ComandoComissaoEstorno comissaoEstorno);
}
