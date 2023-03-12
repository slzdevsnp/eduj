package rsb.synchronicity;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Slf4j
record AsyncRunner(AlgorithmClient algorithm, int max) implements Runnable {

	@Override
	public void run() {
		// <1>
		executeCompletableFuture("calculateWithAsync", () -> algorithm.calculateWithAsync(max));
		// <2>
		executeCompletableFuture("calculateWithCompletableFuture", () -> algorithm.calculateWithCompletableFuture(max));
	}

	private static void executeCompletableFuture(String func,
			Supplier<CompletableFuture<BigInteger>> completableFuture) {
		Timer.before(func);
		completableFuture.get().whenComplete((r, t) -> Timer.result(func, r));
		Timer.after(func);
	}
}