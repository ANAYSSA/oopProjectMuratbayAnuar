package anuarproject.oop1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        try {
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            String username = body.contains("user=") ? body.split("user=")[1].split("&")[0] : "";
            String password = body.contains("pass=") ? body.split("pass=")[1].split("&")[0] : "";

            User user = DBManager.getInstance().login(username, password);

            if (user != null) {
                String role = (user instanceof Admin) ? "ADMIN" : "VIEWER";
                double balance = user.getBalance();
                String json = String.format(java.util.Locale.US,
                        "{\"success\":true,\"role\":\"%s\",\"name\":\"%s\",\"balance\":%.2f}",
                        role, username, balance);

                sendJsonResponse(exchange, json, 200);
            }
        } catch (Exception e) {
            sendJsonResponse(exchange, "{\"error\":\"Server error\"}", 500);
        }
    }
    private void sendJsonResponse(HttpExchange exchange, String json, int status) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}