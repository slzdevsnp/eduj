package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AsyncApiIntegrationTest {

	private final ExecutorService executorService = Executors.newFixedThreadPool(1);

	@Test
	public void async() {
		// <1>
		log.debug("async test entered");
		var integers = Flux.<Integer>create(emitter -> this.launch(emitter, 5));
		// <2>
		StepVerifier.create(integers.doFinally(signalType -> this.executorService.shutdown())).expectNextCount(5)//
				.verifyComplete();
	}

	// <3>
	private void launch(FluxSink<Integer> integerFluxSink, int count) { //FluxSink is a consumer
		this.executorService.submit(() -> {
			var integer = new AtomicInteger();
			Assertions.assertNotNull(integerFluxSink);
			while (integer.get() < count) {
				var random = Math.random();
				integerFluxSink.next(integer.incrementAndGet());// <4> //emitting new element
				this.sleep((long) (random * 1_000));
				log.debug("in executor service sleeping {} ms", random * 1_000);
			}
			integerFluxSink.complete(); // <5>
		});
	}

	private void sleep(long s) {
		try {
			Thread.sleep(s);
		} //
		catch (Exception e) {
			log.error("something's wrong!", e);
		}
	}

}
