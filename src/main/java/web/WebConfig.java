package web;

import java.util.HashMap;
import java.util.Map;

public class WebConfig {

    public static final Map<String, Map<String, Object>> OPTIONS = new HashMap<String, Map<String, Object>>() {{
        put("API", new HashMap<String, Object>() {{
            put("server.port", 8081);
        }});
        put("WebSocket", new HashMap<String, Object>() {{
            put("server.port", 8082);
        }});
    }};
}
