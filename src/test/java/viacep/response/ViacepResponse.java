package viacep.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViacepResponse(String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        Boolean erro) {
}
