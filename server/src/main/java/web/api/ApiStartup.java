package web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import web.WebConfig;
import web.websocket.WebSocketStartup;

@SpringBootApplication
public class ApiStartup {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ApiStartup.class)
                .properties(WebConfig.OPTIONS.get("API"))
                .run(args);
    }
}
