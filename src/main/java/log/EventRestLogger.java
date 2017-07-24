package log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by camp-cms on 24.07.2017.
 */
public class EventRestLogger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean loggToConsole(char key, String sentence){
        switch(key){
            case 'd': logger.debug(sentence);
                    break;
            case 'i': logger.info(sentence);
                    break;
            case 'w': logger.warn(sentence);
                    break;
            case 'e': logger.error(sentence);
                    break;
            default: System.out.println("Logger can not find key");
                return false;
        }
        return true;
    }

}
