package br.com.pulsewarp.application.service;


import br.com.pulsewarp.application.exception.ErroReprocessamentoException;

public interface ReprocessarOpUseCase {
    void reprocessar(String numeroContrato) throws ErroReprocessamentoException;
}
