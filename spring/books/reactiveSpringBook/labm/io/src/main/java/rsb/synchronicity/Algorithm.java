package rsb.synchronicity;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Component
class Algorithm {

	@SneakyThrows
	public BigInteger compute(int num) {
		var result = new AtomicReference<BigInteger>();
		var factorial = factorial(num); // <1>
		iterate(factorial, result::set);// <2>
		return result.get();
	}

	private static void iterate(BigInteger i, Consumer<BigInteger> consumer) {
		for (var bi = BigInteger.ZERO; bi.compareTo(i) < 0; bi = bi.add(BigInteger.ONE)) {
			consumer.accept(bi);
		}
	}

	private static BigInteger factorial(int num) {
		var result = BigInteger.ONE;
		for (int i = 2; i <= num; i++)
			result = result.multiply(BigInteger.valueOf(i));
		return result;
	}

}