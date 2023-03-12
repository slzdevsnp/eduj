package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

// todo boot3
@Slf4j
public class EmitterProcessorTest {

	@Test
	public void emitterProcessor() {
		var processor = Sinks.many().multicast().<String>onBackpressureBuffer();// <1>
		produce(processor);
		consume(processor.asFlux());
	}

	// <2>
	private void produce(Sinks.Many<String> sink) {
		log.debug("in producer");
		for (var i = 0; i < 3; i++) {
			sink.tryEmitNext((i + 1) + "");
			//sink.tryEmitNext(String.valueOf(i + 1)); //also works
		}
		sink.tryEmitComplete();
	}

	// <3>
	private void consume(Flux<String> publisher) {
		log.debug("in consumer");
		StepVerifier //
				.create(publisher)//
				.expectNext("1")//
				.expectNext("2")//
				.expectNext("3")//
				.verifyComplete();
	}

}
