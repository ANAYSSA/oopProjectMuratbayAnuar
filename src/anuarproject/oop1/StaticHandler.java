package anuarproject.oop1;

import com.sun.net.httpserver.HttpExchange;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StaticHandler implements BaseHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        File file = new File("src/web/index.html");

        if (file.exists()) {

            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, file.length());

            try (FileInputStream fs = new FileInputStream(file);
                 OutputStream os = exchange.getResponseBody()) {

                fs.transferTo(os);
            }
        } else {
            sendJsonResponse(exchange, "{\"error\": \"404 Not Found\"}", 404);
        }
    }
}