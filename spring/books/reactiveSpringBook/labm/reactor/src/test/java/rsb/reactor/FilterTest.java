package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
public class FilterTest {

	@Test
	public void filter() {
		int maxCount = 1000;
		var range = Flux.range(0, maxCount).take(10); //limit upper boundary by 10
		var filter = range.filter(i -> i % 2 == 0); // filter logic, only even numbers
		StepVerifier.create(filter).expectNext(0, 2, 4,6,8).verifyComplete();
	}

}
