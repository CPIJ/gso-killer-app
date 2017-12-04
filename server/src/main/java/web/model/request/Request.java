package web.model.request;

import java.lang.reflect.Field;

public abstract class Request {

    public boolean isComplete() throws IllegalAccessException {
        for(Field field : getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (field.get(this) == null || field.get(this).equals("")) {
                return false;
            }
        }

        return true;
    }
}
