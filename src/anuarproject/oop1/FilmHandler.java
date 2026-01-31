package anuarproject.oop1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FilmHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String method = t.getRequestMethod();
        String response = "";

        try {
            if ("GET".equals(method)) {
                List<Film> films = DBManager.getInstance().getAllFilms();
                response = buildJsonFromArray(films);
            }
            else if ("POST".equals(method)) {
                String body = new String(t.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String title = body.split("title=")[1].split("&")[0];
                double price = Double.parseDouble(body.split("price=")[1].split("&")[0]);
                String genre = body.split("genre=")[1].split("&")[0];

                DBManager.getInstance().Addfilm(new Film(title, price, Genre.valueOf(genre)));
                response = "{\"status\":\"success\",\"message\":\"Film added\"}";
            }
            else if ("PUT".equals(method)) {
                String body = new String(t.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String title = body.split("title=")[1].split("&")[0];
                double newPrice = Double.parseDouble(body.split("price=")[1].split("&")[0]);

                DBManager.getInstance().updateFilmPrice(title, newPrice);
                response = "{\"status\":\"success\",\"message\":\"Price updated\"}";
            }
            else if ("DELETE".equals(method)) {
                String query = t.getRequestURI().getQuery();
                String title = query.split("title=")[1];
                DBManager.getInstance().deleteFilm(title);
                response = "{\"status\":\"success\",\"message\":\"Film deleted\"}";
            }

            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        } catch (Exception e) {
            response = "{\"error\":\"" + e.getMessage() + "\"}";
            t.sendResponseHeaders(500, response.length());
        }

        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }

    private String buildJsonFromArray(List<Film> films) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < films.size(); i++) {
            Film f = films.get(i);
            sb.append("{");
            sb.append("\"title\":\"").append(f.getTitle()).append("\",");
            sb.append("\"price\":").append(f.getPrice()).append(","); // Добавили запятую
            sb.append("\"genre\":\"").append(f.getGenre().name()).append("\""); // Добавили жанр
            sb.append("}");
            if (i < films.size() - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }
}