package br.com.pulsewarp.domains;


import lombok.Generated;

public enum SiglasOperacao {
    REFIN_SF("SF"),
    JX8("JX8");

    public final String sigla;

    SiglasOperacao(String sigla) {
        this.sigla = sigla;
    }

    public static SiglasOperacao fromTipo(String tipo) {
        SiglasOperacao status;
        try {
            status = SiglasOperacao.valueOf(tipo);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tipo da operacao nao encontrada : " + tipo);
        }
        return status;
    }

    @Generated
    public String getSigla(){
        return this.sigla;
    }
}
