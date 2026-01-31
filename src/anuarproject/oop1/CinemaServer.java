package anuarproject.oop1;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CinemaServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/films", new FilmHandler());
        server.createContext("/api/login", new LoginHandler());
        server.createContext("/api/register", new RegisterHandler());
        server.createContext("/", t -> {
            try {
                byte[] response = Files.readAllBytes(Paths.get("src/web/index.html"));
                t.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                t.sendResponseHeaders(200, response.length);
                t.getResponseBody().write(response);
            } catch (IOException e) {
                String error = "HTML file not found in web/index.html";
                t.sendResponseHeaders(404, error.length());
                t.getResponseBody().write(error.getBytes());
            } finally {
                t.getResponseBody().close();
            }
        });

        server.setExecutor(null);
        System.out.println("Server started at http://localhost:8080/");
        server.start();
    }
}