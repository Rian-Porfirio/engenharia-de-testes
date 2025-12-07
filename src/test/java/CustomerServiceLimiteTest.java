import br.com.engTestesJava.Customer;
import br.com.engTestesJava.CustomerService;
import br.com.engTestesJava.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerServiceLimiteTest {

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
    @DisplayName("Validar a idade mínima limite permitida no sistema")
    public void testeRegistrarIdadeMinimaValida() {
        int idadeMinimaInvalida = 18;
        customer.setAge(idadeMinimaInvalida);

        assertTrue(customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validar a idade máxima limite permitida no sistema")
    public void testeRegistrarIdadeMaximaValida() {
        int idadeMaximaLimiteInvalida = 99;
        customer.setAge(idadeMaximaLimiteInvalida);

        assertTrue(customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validar a idade mínima limite invalida no sistema")
    public void testeRegistrarIdadeMinimaInvalida() {
        int idadeMinimaLimiteInvalida = 17;
        customer.setAge(idadeMinimaLimiteInvalida);

        assertThrows(ValidationException.class,
                () -> customerService.registerCustomer(customer));
    }

    @Test
    @DisplayName("Validar a idade máxima limite invalida no sistema")
    public void testeRegistrarIdadeMaximaInvalida() {
        int idadeMaximaLimiteInvalida = 100;
        customer.setAge(idadeMaximaLimiteInvalida);

        assertThrows(ValidationException.class,
                () -> customerService.registerCustomer(customer));
    }
}
