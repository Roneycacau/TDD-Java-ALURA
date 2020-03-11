package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class TesteDoAvaliador {

    private Avaliador leiloeiro;
    private Usuario maria;
    private Usuario jose;
    private Usuario joao;

    @BeforeEach
    public void setUp() {
        this.leiloeiro = new Avaliador();
        joao = new Usuario("João");
        jose = new Usuario("José");
        maria = new Usuario("Maria");
    }

    @Test
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .constroi();
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> leiloeiro.avalia(leilao));
        assertTrue(runtimeException.getMessage().contains("Não é possível avaliar um leilão sem lances!"));
    }


    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .lance(joao, 250)
                .lance(jose, 300)
                .lance(maria, 400)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .lance(joao, 1000)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(),equalTo(leilao.getLances().get(0).getValor()));
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .lance(joao, 100)
                .lance(maria, 200)
                .lance(joao, 300)
                .lance(maria, 400)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertThat(maiores, hasItems(
                new Lance(maria, 400),
                new Lance(joao, 300),
                new Lance(maria, 200)
        ));
    }

}
