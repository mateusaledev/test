package br.com.pulsewarp.application.exception;

import lombok.Getter;

@Getter
public class ErroReprocessamentoException extends Exception{
    private final String numeroContrato;

    public ErroReprocessamentoException(String numeroContrato, String message){
        super(message);
        this.numeroContrato = numeroContrato;
    }
    public ErroReprocessamentoException(String numeroContrato, String message, Throwable cause){
        super(message, cause);
        this.numeroContrato = numeroContrato;
    }
    public ErroReprocessamentoException(String numeroContrato, Throwable cause){
        super(cause);
        this.numeroContrato = numeroContrato;
    }
    public ErroReprocessamentoException(String numeroContrato, String message, Throwable cause, boolean enableSuppresion, boolean writableStackTrace){
        super(message, cause, enableSuppresion, writableStackTrace);
        this.numeroContrato = numeroContrato;
    }
}
