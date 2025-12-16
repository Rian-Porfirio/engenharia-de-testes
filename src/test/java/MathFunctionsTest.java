import br.com.engTestesJava.MathFunctions.MathFunctions;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.Arrays;

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

    @Property
    boolean tabelaSempreContemMultiplos(
            @ForAll int number,
            @ForAll @IntRange(min = 1, max = 50) int limit) {

        int[] table = MathFunctions.generateMultiplicationTable(number, limit);

        for (int value : table) {
            if (number != 0 && value % number != 0) {
                return false;
            }
        }
        return true;
    }

    @Property
    boolean primoNaoPossuiOutrosDivisores(@ForAll("gerarPrimos") int prime) {
        for (int i = 2; i < prime; i++) {
            if (prime % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static double calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            throw new IllegalArgumentException();
        return Arrays.stream(numbers).average().orElseThrow();
    }

    @Provide
    Arbitrary<Integer> gerarPrimos() {
        return Arbitraries.integers()
                .between(2, 10_000)
                .filter(MathFunctions::isPrime);
    }

    @Property
    boolean mediaSempreEntreMinEMax(
            @ForAll @Size(min = 1, max = 100) int[] numbers) {

        double average = MathFunctions.calculateAverage(numbers);

        int min = Arrays.stream(numbers).min().getAsInt();
        int max = Arrays.stream(numbers).max().getAsInt();

        return average >= min && average <= max;
    }

    @Provide
    Arbitrary<Integer> gerarPares() {
        return Arbitraries.integers()
                .between(0, MAX_RANGE_MULTIPLY_BY_TWO)
                .map(n -> n * 2);
    }
}
