package br.com.pulsewarp.application.service;

import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;

public interface ComUseCase {

    void pagamento(ComandoComissaoPagamento comPagamento);

    void estorno(ComandoComissaoEstorno comPagamento);
}
