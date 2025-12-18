package viacep.test;

import viacep.response.ViacepResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import viacep.client.IViacepClient;
import viacep.client.ViacepClientImpl;

import static org.junit.jupiter.api.Assertions.*;

public class ViacepTest {

    private final IViacepClient client = new ViacepClientImpl();

    @Test
    void deveRetornarEnderecoParaCepValido() {
        Response response = client.buscarPorCep("01001000");


        ViacepResponse body = response.as(ViacepResponse.class);
        assertEquals("SP", body.uf());
        assertNotNull(body.logradouro());
    }

    @Test
    void deveRetornarErroParaCepInexistente() {
        Response response = client.buscarPorCep("99999999");

        ViacepResponse body = response.as(ViacepResponse.class);
        assertTrue(body.erro());
    }

    @Test
    void deveFalharParaCepComLetras() {
        Response response = client.buscarPorCep("ABC12345");

        assertTrue(response.statusCode() >= 400 ||
                response.asString().contains("erro"));
    }

    @Test
    void deveFalharParaCepVazio() {
        Response response = client.buscarPorCep("");

        assertTrue(response.statusCode() >= 400);
    }
}
