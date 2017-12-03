import rmi.RmiStartup;
import org.springframework.context.annotation.ComponentScan;
import shared.util.Log;
import web.api.ApiStartup;
import web.websocket.WebSocketStartup;

import java.lang.reflect.InvocationTargetException;

@ComponentScan("authentication.web")
public class Application {

    private static Class<?>[] startupClasses = new Class[]{
            RmiStartup.class,
            ApiStartup.class,
            WebSocketStartup.class
    };

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for (Class<?> startupClass : startupClasses) {
            Log.debug(startupClass.getName(), "running main...");

            startupClass
                    .getMethod("main", String[].class)
                    .invoke(null, (Object) args);

            Log.debug(startupClass.getName(), "has started successfully...");
        }

        Log.success("\nProgram started successfully!");

    }
}

