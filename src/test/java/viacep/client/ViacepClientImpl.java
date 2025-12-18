package viacep.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ViacepClientImpl implements IViacepClient {

    private static final String BASE_URL = "https://viacep.com.br/ws";

    @Override
    public Response buscarPorCep(String cep) {
        return RestAssured
                .given()
                .when()
                .get(BASE_URL + "/" + cep + "/json/");
    }

    @Override
    public Response buscarPorEndereco(String uf, String cidade, String logradouro) {
        return RestAssured
                .given()
                .when()
                .get(BASE_URL + "/" + uf + "/" + cidade + "/" + logradouro + "/json/");
    }
}
