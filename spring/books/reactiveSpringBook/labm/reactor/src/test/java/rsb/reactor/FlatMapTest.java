package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FlatMapTest {

	@Test
	public void flatMap() {

		var data = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
				.flatMap(e -> this.delayReplyFor(e.id, e.delay));

		CodeTimer.time( () -> {
			StepVerifier//
				.create(data)//
				.expectNext(3, 2, 1) // .concatMap will return stream of (1,2,3) i.e. preserves the order of elements
				.verifyComplete();
		}, TimeUnit.MILLISECONDS);

	}

	private Flux<Integer> delayReplyFor(Integer i, long delay) {
		return Flux.just(i).delayElements(Duration.ofMillis(delay));
	}

	private record Pair(int id, long delay) {
	}

}
