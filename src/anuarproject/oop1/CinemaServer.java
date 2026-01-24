package anuarproject.oop1;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class CinemaServer {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/films", new FilmHandler());
        server.createContext("/", new StaticHandler());
        server.setExecutor(null);
        System.out.println("http://localhost:8080/api/films");
        server.start();
    }
}