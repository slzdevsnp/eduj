package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicBoolean;
@Slf4j
public class TransformTest {

	@Test
	public void transform() {
		var finished = new AtomicBoolean();
		finished.set(false);
		var letters = Flux//
				.just("A", "B", "C")
//				.transform(stringFlux -> stringFlux.doFinally(signal -> finished.set(true)));// <1>
				.transform(stringFlux -> stringFlux.doFinally(signal -> {
                    log.debug("inside lmbda"); //inside lambda finally after stream processing
					finished.set(true);
				}));
		StepVerifier.create(letters).expectNextCount(3).verifyComplete();
		Assertions.assertTrue(finished.get(), "the finished Boolean must be true.");
		Assertions.assertTrue(finished.get());
	}

}
