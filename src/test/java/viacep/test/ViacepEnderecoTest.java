package viacep.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import viacep.client.IViacepClient;
import viacep.client.ViacepClientImpl;

import static org.junit.jupiter.api.Assertions.*;

public class ViacepEnderecoTest {

    private final IViacepClient client = new ViacepClientImpl();

    @Test
    void deveRetornarEnderecosComDadosValidos() {
        Response response = client.buscarPorEndereco(
                "SP", "Sao Paulo", "Avenida Paulista");

        assertEquals(200, response.statusCode());
        assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test
    void deveAceitarCidadeComAcento() {
        Response response = client.buscarPorEndereco(
                "SP", "São Paulo", "Avenida Paulista");

        assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test
    void deveRetornarListaVaziaParaLogradouroInexistente() {
        Response response = client.buscarPorEndereco(
                "SP", "Sao Paulo", "Rua Inexistente");

        assertTrue(response.jsonPath().getList("$").isEmpty());
    }

    @Test
    void deveRetornarListaVaziaParaUfInvalida() {
        Response response = client.buscarPorEndereco(
                "XX", "Sao Paulo", "Avenida Paulista");

        assertTrue(response.jsonPath().getList("$").isEmpty());
    }
}
