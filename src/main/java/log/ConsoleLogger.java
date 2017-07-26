package log;
import org.slf4j.LoggerFactory;
/**
 * Created by camp-cms on 24.07.2017.
 */
public class ConsoleLogger extends RestLogger{
    public ConsoleLogger(){
        super.logger = LoggerFactory.getLogger("ConsoleLogger");
    }

}
