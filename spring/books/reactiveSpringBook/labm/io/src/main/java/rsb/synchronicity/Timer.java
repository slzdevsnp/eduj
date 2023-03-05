package rsb.synchronicity;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
abstract class Timer {

	private static final Map<String, Instant> starts = new ConcurrentHashMap<>();

	static void before(String func) {
		var now = Instant.now();
		starts.put(func, now);
		log.info("before " + func);
	}

	static void after(String func) {
		log.info("after " + func);
	}

	static void result(String func, Object result) {
		var now = Instant.now();
		var beginning = starts.get(func);
		var duration = now.toEpochMilli() - beginning.toEpochMilli();
		var durationString = (duration < 1000) //
				? (duration + " milliseconds") //
				: ((duration / 1000) + "." + (duration % 1000) + " seconds");
		log.info("result of {} is {}. Task ran for {}", func, result, durationString);
	}

}
