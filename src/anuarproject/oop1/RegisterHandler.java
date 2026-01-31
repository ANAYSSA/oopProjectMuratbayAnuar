package anuarproject.oop1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        if (!"POST".equals(t.getRequestMethod())) {
            t.sendResponseHeaders(405, -1);
            return;
        }

        try {
            String body = new String(t.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String user = body.contains("user=") ? body.split("user=")[1].split("&")[0] : "";
            String pass = body.contains("pass=") ? body.split("pass=")[1].split("&")[0] : "";
            String name = body.contains("name=") ? body.split("name=")[1].split("&")[0] : "";
            double balance = body.contains("balance=") ? Double.parseDouble(body.split("balance=")[1].split("&")[0]) : 0.0;
            DBManager.getInstance().registerViewer(user, pass, name, balance);

            String json = "{\"success\":true,\"message\":\"User registered\"}";
            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, json.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = t.getResponseBody()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            String error = "{\"success\":false,\"error\":\"" + e.getMessage() + "\"}";
            t.sendResponseHeaders(500, error.length());
            t.getResponseBody().write(error.getBytes());
            t.getResponseBody().close();
        }
    }
}