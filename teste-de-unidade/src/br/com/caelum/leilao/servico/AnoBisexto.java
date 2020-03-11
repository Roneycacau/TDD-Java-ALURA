package br.com.caelum.leilao.servico;

public class AnoBisexto {
    private int ano;

    public AnoBisexto() {}

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public AnoBisexto(int ano) {
        this.ano = ano;
    }

    public boolean ehbisexto(int ano) {
        return ((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0);
    }
}
