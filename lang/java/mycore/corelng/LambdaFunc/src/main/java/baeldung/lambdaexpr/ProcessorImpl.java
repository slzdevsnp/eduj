package baeldung.lambdaexpr;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ProcessorImpl implements Processor {

        public String processWithCallable(Callable<String> c) throws Exception {
            return c.call();
        }

        public String processWithSupplier(Supplier<String> s) {
            return s.get();
        }

    }
