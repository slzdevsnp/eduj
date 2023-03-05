package rsb.synchronicity;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.function.Supplier;

@Slf4j
record SyncRunner(AlgorithmClient algorithm, int max) implements Runnable {

	@Override
	public void run() {
		Timer.before("calculateSync");
		var results = ((Supplier<BigInteger>) () -> algorithm.calculateSync(this.max)).get();
		Timer.after("calculateSync");
		Timer.result("calculateSync", results);
	}
}