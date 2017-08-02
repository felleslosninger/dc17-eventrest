package log;
import org.slf4j.Logger;
/**
 * Created by camp-cms on 01.08.2017.
 */
public class RestLogger {
    protected final Logger logger;

    public RestLogger(Logger l){
        logger = l;
    }

    public boolean logg(char key, String sentence) {
        switch (key) {
            case 'd':
                logger.debug(sentence);
                break;
            case 'i':
                logger.info(sentence);
                break;
            case 'w':
                logger.warn(sentence);
                break;
            case 'e':
                logger.error(sentence);
                break;
            default:
                return false;
        }
        return true;
    }
}
