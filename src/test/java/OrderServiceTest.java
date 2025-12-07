import br.com.engTestesJava.OrderService;
import br.com.engTestesJava.PaymentProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    PaymentProcessor paymentProcessorMock;

    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(paymentProcessorMock);
    }

    @Test
    @DisplayName("Deve Retornar True Quando Pagamento Aprovado")
    public void testePagamentoAprovado() {
        when(paymentProcessorMock.processPayment(100.0))
                .thenReturn(true);

        boolean result = orderService.processOrder(100.0);

        assertTrue(result);
        verify(paymentProcessorMock).processPayment(100.0);
    }

    @Test
    @DisplayName("Deve Retornar False Quando Pagamento Recusado")
    public void testePagamentoRecusado() {
        when(paymentProcessorMock.processPayment(100.0)).thenReturn(false);

        boolean result = orderService.processOrder(100.0);

        assertFalse(result);
        verify(paymentProcessorMock).processPayment(100.0);
    }
}
