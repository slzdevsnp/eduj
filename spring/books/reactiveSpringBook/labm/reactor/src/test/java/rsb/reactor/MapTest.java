package rsb.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class MapTest {

	@Test
	public void maps() {
		var data = Flux.just("a", "b", "c").map(String::toUpperCase); //applies a lambda to each item
		//var data = Flux.just("a", "b", "c").map(x -> x.toUpperCase()); //same

		StepVerifier.create(data).expectNext("A", "B", "C").verifyComplete();
	}

}
