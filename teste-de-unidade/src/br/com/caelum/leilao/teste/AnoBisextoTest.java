package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.servico.AnoBisexto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnoBisextoTest {

    @Test
    public void eBisextoMultiploDe4MasNão100(){
        AnoBisexto ano = new AnoBisexto();
        assertTrue(ano.ehbisexto(2020));
        assertFalse(ano.ehbisexto(100));
    }
    @Test
    public void eBisextoMultiplode4E400(){
        AnoBisexto ano = new AnoBisexto();
        assertTrue(ano.ehbisexto(2000));
    }

    @Test
    public void naoEBisextoMultiploDe100(){
        AnoBisexto ano = new AnoBisexto();
        assertFalse(ano.ehbisexto(100));
        assertFalse(ano.ehbisexto(1000));
    }

}