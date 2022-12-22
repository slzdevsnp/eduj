package basiclog4j;
import org.apache.log4j.Logger;
public class StudentDemo {
    static Logger logger = Logger.getLogger(StudentDemo.class);
    public static void main(String[] args) {

        logger.info(StudentDemo.class.getName() + " run start.");
        logger.trace("this is a tracing message");
        logger.debug("This is debug message");
        logger.info("This is info message");
        logger.warn("This is warn message");
        logger.fatal("This is fatal message");
        logger.error("This is error message");
        System.out.println("Logic executed successfully....");
        System.out.println("check messages in logfile /tmp/logging.log");
        System.out.println("change the log level in log4j.properties"
                + " from INFO to TRACE. Then rerun the program.");
    }
}
