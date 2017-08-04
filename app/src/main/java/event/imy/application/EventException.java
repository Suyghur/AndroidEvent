package event.imy.application;

/**
 * Created by 4399-蒋明伟 on 2017/8/3.
 */

public class EventException extends RuntimeException {
    public EventException() {
        super();
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventException(Throwable cause) {
        super(cause);
    }

}
