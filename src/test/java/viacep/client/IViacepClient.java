package viacep.client;

import io.restassured.response.Response;

public interface IViacepClient {
    Response buscarPorCep(String cep);

    Response buscarPorEndereco(String uf, String cidade, String logradouro);
}
