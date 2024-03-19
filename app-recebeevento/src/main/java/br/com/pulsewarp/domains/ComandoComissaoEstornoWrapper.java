package br.com.pulsewarp.domains;

public class ComandoComissaoEstornoWrapper {
    private ComandoComissaoEstorno payload;

    public ComandoComissaoEstornoWrapper(ComandoComissaoEstorno payload) {
        this.payload = payload;
    }

    public ComandoComissaoEstorno getPayload() {
        return payload;
    }

    public void setPayload(ComandoComissaoEstorno payload) {
        this.payload = payload;
    }
}
