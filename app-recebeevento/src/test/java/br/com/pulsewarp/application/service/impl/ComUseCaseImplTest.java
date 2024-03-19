package br.com.pulsewarp.application.service.impl;

import br.com.pulsewarp.adapters.database.procedure.ProcedureComissaoIn;
import br.com.pulsewarp.domains.ComandoComissaoEstorno;
import br.com.pulsewarp.domains.ComandoComissaoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ComUseCaseImplTest {

    @Mock
    private ProcedureComissaoIn procedureComissaoIn;

    @InjectMocks
    private ComUseCaseImpl comUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPagamento() {
        // Criar um objeto ComandoComissaoPagamento de teste
        ComandoComissaoPagamento comPagamento = new ComandoComissaoPagamento();
        // Chamar o método de teste
        comUseCase.pagamento(comPagamento);
        // Verificar se o método comissaoPagamento foi chamado uma vez
        verify(procedureComissaoIn, times(1)).comissaoPagamento(comPagamento);
    }

    @Test
    void testEstorno() {
        // Criar um objeto ComandoComissaoEstorno de teste
        ComandoComissaoEstorno comEstorno = new ComandoComissaoEstorno();
        // Chamar o método de teste
        comUseCase.estorno(comEstorno);
        // Verificar se o método comissaoEstorno foi chamado uma vez
        verify(procedureComissaoIn, times(1)).comissaoEstorno(comEstorno);
    }
}