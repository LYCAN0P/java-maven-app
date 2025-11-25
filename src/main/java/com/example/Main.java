package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        System.out.println("HTTP server started on port 8080");
        server.start();
    }
    static class RootHandler implements HttpHandler {
        public void handle(HttpExchange t) {
            try {
                String response = "<h1>Simple Java App</h1><p>Hello from Docker + Jenkins!</p>";
                t.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
