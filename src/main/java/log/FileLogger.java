package log;
import org.slf4j.LoggerFactory;

/**
 * Created by camp-cms on 24.07.2017.
 */

public class FileLogger  extends RestLogger {

    public FileLogger(){
        super(LoggerFactory.getLogger("FileLogger"));
    }
}
