package br.com.pulsewarp.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComandoComissaoEstorno {


    @NotBlank(message = "Número do contrato é obrigatório")
    private String numContrato;

    @NotNull(message = "Valor com vista é obrigatório")
    @Positive(message = "Valor comissao a vista deve ser positivo")
    private String valorComVista;

    @NotBlank(message = "Data do contrato é obrigatória")
    private String dataContrato;

    @NotBlank(message = "Data de estorno é obrigatória")
    private String dataEstorno;

    @NotBlank(message = "Sigla de origem é obrigatória")
    private String siglaOrigem;
}
