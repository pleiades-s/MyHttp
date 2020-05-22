package edu.skku.myhttpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;

public class Main {

    public static abstract class AbstractHttpHandler implements HttpHandler {

        abstract String getPath();

        public abstract void handle(HttpExchange httpExchange) throws IOException;

    }

    public static class EchoHandler extends AbstractHttpHandler{

        public String getPath(){
            return "/echo";
        }

        public void handle(HttpExchange httpExchange) throws IOException {
            String requestURL = httpExchange.getRequestURI().toString();

//            String read = new String(readBytes,  StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request URL: " + requestURL);
            httpExchange.sendResponseHeaders(200, requestURL.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(requestURL.getBytes()); // echo
            os.flush();
        }


    }


    public static void main(String [] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        List<AbstractHttpHandler> handlers = List.of(
                new EchoHandler()
        );

        for (AbstractHttpHandler handler: handlers) {
            server.createContext(handler.getPath(), handler);
        }

        server.setExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        server.start();
    }
}
