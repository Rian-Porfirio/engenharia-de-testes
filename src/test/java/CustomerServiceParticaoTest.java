import br.com.engTestesJava.Customer;
import br.com.engTestesJava.CustomerService;
import br.com.engTestesJava.EmailValidation;
import br.com.engTestesJava.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerServiceParticaoTest {

    CustomerService customerService;
    Customer customer;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
        customer = new Customer();
        customer.setId(1);
        customer.setName("Teste");
        customer.setEmail("teste@gmail.com");
        customer.setActive(true);
        customer.setAge(30);
    }

    @Test
    @DisplayName("Validar a idade mínima invalida no sistema")
    public void testeRegistrarIdadeMinimaInvalida() {
        int idadeMinimaInvalida = 10;
        customer.setAge(idadeMinimaInvalida);

        assertThrows(ValidationException.class,
                () -> customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validar a idade máxima invalida no sistema")
    public void testeRegistrarIdadeMaximaInvalida() {
        int idadeMaximaInvalida = 150;
        customer.setAge(idadeMaximaInvalida);

        assertThrows(ValidationException.class,
                () -> customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validar cliente ativo pode ser atualizado")
    public void testeAtualizarAtivo() {
        String nomeUpdate = "Nome Teste";
        String emailUpdate = "emailTeste@gmail.com";
        int idadeUpdate = 30;

        assertTrue(customerService.updateCustomer(customer, nomeUpdate, emailUpdate, idadeUpdate));
    }

    @Test
    @DisplayName("Validar cliente inativo não pode ser atualizado")
    public void testeAtualizarInativo() {
        customer.setActive(false);

        String nomeUpdate = "Nome Teste";
        String emailUpdate = "emailTeste@gmail.com";
        int idadeUpdate = 30;

        assertThrows(ValidationException.class,
                () -> customerService.updateCustomer(customer, nomeUpdate, emailUpdate, idadeUpdate));
    }

    @Test
    @DisplayName("Validar se um cliente ativo pode ser excluído corretamente")
    public void testeExcluirAtivo() {
        assertTrue(customerService.deleteCustomer(customer));
    }

    @Test
    @DisplayName("Validar se um cliente inativo não pode ser excluído")
    public void testeExcluirInativo() {
        customer.setActive(false);

        assertThrows(ValidationException.class,
                () -> customerService.deleteCustomer(customer));
    }

    @Test
    @DisplayName("Validando registro formato valido do Email do cliente")
    public void testeFormatoValidoEmail() {
        assertTrue(() -> customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validando registro formato invalido sem @ (arroba) do Email do cliente")
    public void testeFormatoInvalidoArrobaEmail() {
        customer.setEmail("testegmail.com");

        assertThrows(EmailValidation.class,
                () -> customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validando registro formato invalido sem dominio do Email do cliente")
    public void testeFormatoInvalidoDominioEmail() {
        customer.setEmail("teste@gmail");

        assertThrows(EmailValidation.class,
                () -> customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validando registro com todos os dados corretos conforme regra de negócio")
    public void testeRegistroCompletoValido() {
        assertTrue(customerService.registerCustomer(customer));
    }
}
