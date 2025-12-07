package br.com.engTestesJava;

public class CustomerService {
    public boolean registerCustomer(Customer customer) {
        validateInputs(customer);
        return true; // Cadastro bem-sucedido
    }

    public boolean updateCustomer (Customer customer, String newName, String newEmail,int newAge) {
        if (!customer.isActive()) {
            throw new ValidationException("Cliente inativo não pode ser atualizado"); // Cliente inativo não pode ser atualizado
        }

        validateInputs(customer);
        customer.setName(newName);
        customer.setEmail(newEmail);
        customer.setAge(newAge);

        return true; // update bem sucedido
    }

    public boolean deleteCustomer (Customer customer) {
        if (!customer.isActive()) {
            throw new ValidationException("Cliente inativo não pode ser excluído"); // Cliente inativo não pode ser excluído
        }

        return true; // delete bem sucedido
    }

    private void validateInputs(Customer customer) {
        if (customer.getAge() < 18 || customer.getAge() > 99) {
            throw new ValidationException("Idade não permitida"); // Idade inválida
        }

        if (!customer.getEmail().matches("^[\\w.-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$")) {
            throw new EmailValidation("Email inválido"); // E-mail inválido
        }
    }
}