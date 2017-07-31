package log;
import org.slf4j.Logger;
/**
 * Created by camp-cms on 26.07.2017.
 */
import org.slf4j.Logger;
public class RestLogger {
    protected Logger logger;

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
                System.out.println("Logger can not find key");
                return false;
        }
        return true;
    }
}
