import br.com.engTestesJava.MathFunctions;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class MathFunctionsTest {

    private final int MAX_RANGE_MULTIPLY_BY_TWO = Integer.MAX_VALUE / 2;

    @Property
    boolean testeMultiplyByTwoRetornaMaiorQueInput(@ForAll @IntRange(max = MAX_RANGE_MULTIPLY_BY_TWO) int number) {
        int result = MathFunctions.multiplyByTwo(number);
        return result >= number;
    }

    @Property
    boolean testeMultiplyByTwoRetornaParCasoInputForPar(@ForAll("gerarPares") int number) {
        int result = MathFunctions.multiplyByTwo(number);
        return MathFunctions.isEven(result);
    }

    @Provide
    Arbitrary<Integer> gerarPares() {
        return Arbitraries.integers()
                .between(0, MAX_RANGE_MULTIPLY_BY_TWO)
                .map(n -> n * 2);
    }
}
