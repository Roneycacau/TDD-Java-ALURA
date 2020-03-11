package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanceTest {
    private Usuario jose;

    @BeforeEach
    public void setUp(){
        jose = new Usuario("Jose");
    }

    @Test
    public void naoDeveAceitarLanceIgualZero(){
        IllegalArgumentException lanceValorZero = assertThrows(IllegalArgumentException.class,
                () -> new Lance(jose, 0));
        assertTrue(lanceValorZero.getMessage().contains("Lance deve ser maior que 0"));
    }
    @Test
    public void naoDeveAceitarLanceMenorZero(){
        IllegalArgumentException lanceMenorZero = assertThrows(IllegalArgumentException.class,
                () -> new Lance(jose, -12));
        assertTrue(lanceMenorZero.getMessage().contains("Lance deve ser maior que 0"));
    }
}