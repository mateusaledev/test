package br.com.pulsewarp.application.service.impl;

import br.com.pulsewarp.adapters.database.procedure.ProcedureComissaoIn;
import br.com.pulsewarp.application.service.ComUseCase;
import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComUseCaseImpl implements ComUseCase {

    private final ProcedureComissaoIn procedureComissaoIn;

    @Override
    public void pagamento(ComandoComissaoPagamento comPagamento) {
        procedureComissaoIn.comissaoPagamento(comPagamento);
    }

    @Override
    public void estorno(ComandoComissaoEstorno comEstorno) {
        procedureComissaoIn.comissaoEstorno(comEstorno);
    }
}
