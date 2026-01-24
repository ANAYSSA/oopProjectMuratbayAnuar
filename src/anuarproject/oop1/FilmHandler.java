package anuarproject.oop1;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
public class FilmHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                List<Film> films = DBManager.getInstance().getAllFilms();
                StringBuilder json = new StringBuilder("[");
                for (int i = 0; i < films.size(); i++) {
                    Film f = films.get(i);
                    json.append("{")
                            .append("\"title\":\"").append(f.getTitle()).append("\",")
                            .append("\"price\":").append(f.getPrice()).append(",")
                            .append("\"genre\":\"").append(f.getGenre()).append("\"")
                            .append("}");
                    if (i < films.size() - 1) json.append(",");
                }
                json.append("]");

                String response = json.toString();

                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                String error = "Internal Server Error";
                exchange.sendResponseHeaders(500, error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
            }
        } else {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
    }
}