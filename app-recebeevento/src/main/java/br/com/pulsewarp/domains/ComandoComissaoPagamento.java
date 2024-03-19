package br.com.pulsewarp.domains;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ComandoComissaoPagamento {

    private String numContrato;
    private String valorComVista;
    private String dataContrato;
    private String dataEfetiva;
    private String siglaOrigem;
}
