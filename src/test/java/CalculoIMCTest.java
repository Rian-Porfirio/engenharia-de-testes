import br.com.engTestesJava.calculoIMC.CalculoIMC;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CalculoIMCTest {

    // 1. Entradas Válidas

    @Test
    public void testeEntradaValidaSaudavel() {
        double imc = CalculoIMC.calcularPeso(70, 1.75);
        assertEquals("Saudável", CalculoIMC.classificarIMC(imc));
    }

    @Test
    public void testeEntradaValidaMagrezaLeve() {
        double imc = CalculoIMC.calcularPeso(45, 1.60);
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(imc));
    }

    @Test
    public void testeEntradaValidaSobrepeso() {
        double imc = CalculoIMC.calcularPeso(90, 1.80);
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(imc));
    }


    // 2. Entradas Inválidas

    @Test
    public void testePesoNegativo() {
        double peso = -10;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class, () -> {
            CalculoIMC.calcularPeso(peso, altura);
        });
    }

    @Test
    public void testeAlturaNegativa() {
        double peso = 70;
        double altura = -1.75;

        assertThrows(IllegalArgumentException.class, () -> {
            CalculoIMC.calcularPeso(peso, altura);
        });
    }

    @Test
    public void testeEntradaComLetra() {
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble("abc");
        });
    }

    @Test
    public void testeEntradaComCaracteresEspeciais() {
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble("@!");
        });
    }

    @Test
    public void testeEntradaComVirgula() {
        // O comportamento atual do programa quebra.
        // Aqui testamos se realmente lança erro.
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble("70,5");
        });
    }

    // 3. Entradas Limítrofes

    @Test
    public void testePesoZero() {
        double peso = 0;
        double altura = 1.70;

        double imc = CalculoIMC.calcularPeso(peso, altura);
        assertEquals(0.0, imc);
    }

    @Test
    public void testeAlturaZero() {
        assertThrows(ArithmeticException.class, () -> {
            CalculoIMC.calcularPeso(70, 0);
        });
    }

    @Test
    public void testePesoExtremo() {
        double imc = CalculoIMC.calcularPeso(300, 1.40);
        assertTrue(imc > 0);
    }

    @Test
    public void testeAlturaExtremamenteBaixa() {
        double imc = CalculoIMC.calcularPeso(45, 0.50);
        assertTrue(imc > 0);
    }
}
