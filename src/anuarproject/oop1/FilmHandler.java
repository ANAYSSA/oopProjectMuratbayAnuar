package anuarproject.oop1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class FilmHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String json = "";
        try {

            List<Film> films = DBManager.getInstance().getAllFilms();


            json = "[";
            for (int i = 0; i < films.size(); i++) {
                Film f = films.get(i);
                json += "{\"title\":\"" + f.getTitle() + "\", \"price\":" + f.getPrice() + ", \"genre\":\"" + f.getGenre() + "\"}";
                if (i < films.size() - 1) json += ",";
            }
            json += "]";


            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, json.getBytes().length);

        } catch (Exception e) {

            json = "{\"error\":\"Database connection failed\"}";
            t.sendResponseHeaders(500, json.getBytes().length);
        }

        OutputStream os = t.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }
}