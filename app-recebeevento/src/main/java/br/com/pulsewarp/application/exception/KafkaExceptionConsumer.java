package br.com.pulsewarp.application.exception;

import org.springframework.kafka.listener.ListenerExecutionFailedException;

public class KafkaExceptionConsumer {

    public void handleExecutionFailedException(ListenerExecutionFailedException e) {
        // Aqui você pode implementar a lógica para lidar com a exceção específica que ocorreu durante o processamento
        // da mensagem do Kafka, como reprocessamento ou envio para uma fila de retry
        Throwable cause = e.getCause();
        if (cause instanceof BusinessException) {
            // Lógica para reprocessar a mensagem ou enviar para uma fila de retry
        } else {
            // Lógica para lidar com outros tipos de exceção
        }
    }

    public void handleGeneralException(Exception e) {
        // Aqui você pode implementar a lógica para lidar com outras exceções que podem ocorrer durante o processamento
        // da mensagem do Kafka, como reprocessamento ou envio para uma fila de retry
        // Lógica para reprocessar a mensagem ou enviar para uma fila de retry
    }
}

