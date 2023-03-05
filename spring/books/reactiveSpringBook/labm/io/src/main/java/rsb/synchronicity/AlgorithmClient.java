package rsb.synchronicity;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
class AlgorithmClient {

	private final Executor executor;

	private final Algorithm algorithm;

	// <1>
	public BigInteger calculate(int n) {
		return this.algorithm.compute(n);
	}

	// <2>
	public CompletableFuture<BigInteger> calculateWithCompletableFuture(int n) {
		var cf = new CompletableFuture<BigInteger>();
		this.executor.execute(() -> cf.complete(this.algorithm.compute(n)));
		return cf;
	}

	// <3>
	@Async
	public CompletableFuture<BigInteger> calculateWithAsync(int n) {
		return CompletableFuture.completedFuture(this.algorithm.compute(n));
	}

}