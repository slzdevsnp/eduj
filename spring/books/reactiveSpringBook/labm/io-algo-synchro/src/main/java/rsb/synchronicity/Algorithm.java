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
		// while bi starting at zero is lower then input Ii=factorial, increment  bi by 1
		// This take time over big number param i
		// NB! the of compute will be correct if  <=0 comparison will be used
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