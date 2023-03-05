package rsb.synchronicity;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.function.Supplier;

@Slf4j
record SyncRunner(AlgorithmClient algorithm, int max) implements Runnable {

	@Override
	public void run() {
		Timer.before("calculate");
		var results = ((Supplier<BigInteger>) () -> algorithm.calculate(this.max)).get();
		Timer.after("calculate");
		Timer.result("calculate", results);
	}
}