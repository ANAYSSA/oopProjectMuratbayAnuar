package anuarproject.oop1;

import com.sun.net.httpserver.HttpExchange;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
public class FilmHandler implements BaseHandler { // Наследуем наш интерфейс
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        try {
            List<Film> films = DBManager.getInstance().getAllFilms();

            String json = "[" + films.stream()
                    .map(f -> String.format("{\"title\":\"%s\",\"price\":%.2f,\"genre\":\"%s\"}",
                            f.getTitle(), f.getPrice(), f.getGenre()))
                    .collect(Collectors.joining(",")) + "]";

            sendJsonResponse(exchange, json, 200);
        } catch (Exception e) {
            sendJsonResponse(exchange, "{\"error\":\"" + e.getMessage() + "\"}", 500);
        }
    }
}