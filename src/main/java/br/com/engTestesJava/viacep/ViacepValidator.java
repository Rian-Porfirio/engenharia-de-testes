package br.com.engTestesJava.viacep;

public class ViacepValidator {
    public static boolean isCepValido(String cep) {
        return cep != null && cep.matches("\\d{8}");
    }
}
