package edu.skku.myhttpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Main {

    public static abstract class AbstractHttpHandler implements HttpHandler {

        abstract String getPath();

        public abstract void handle(HttpExchange httpExchange) throws IOException;

    }


    public static class FunnyStoryHandler extends AbstractHttpHandler{

        @Override
        String getPath() {
            return "/funny";
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            if ("GET".equals(httpExchange.getRequestMethod())){
                handleGetRequest(httpExchange);
            }

            else if ("POST".equals(httpExchange.getRequestMethod())){
                handlePOSTRequest(httpExchange);
            }
        }

        public void handleGetRequest(HttpExchange httpExchange) throws IOException{
            String response = genJson();
            System.out.println(response);

            String requestURL = httpExchange.getRequestURI().toString();
//            String read = new String(readBytes,  StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request URL: " + requestURL);
            httpExchange.sendResponseHeaders(200, response.length());
            byte[] output = response.getBytes("utf-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(output);
            os.flush();
            os.close();

        }
        public void handlePOSTRequest(HttpExchange httpExchange) throws IOException{
            // Get body
            byte[] read_byte = httpExchange.getRequestBody().readAllBytes();
            String request_body = new String(read_byte, StandardCharsets.UTF_8.name());

            JSONObject json_object = new JSONObject();
            String response = "";
            try {
                json_object = parseJson(request_body);
                response = "Thanks";
                httpExchange.sendResponseHeaders(200, response.length());

            }
            catch (ParseException e){
                System.out.println("ParseException " + e);
                response = "Bad request";
                httpExchange.sendResponseHeaders(400, response.length());
            }

            String title = (String) json_object.get("title");
            String content = (String) json_object.get("content");

            System.out.println("title " + title + " content "+ content);

            Story story = new Story(title, content);
            for (int i = 0; i < categories.size(); i++){

                if (categories.get(i).getTypeName().equals("funny")){

                    categories.get(i).getStories().add(story);
                }
            }


            byte[] output = response.getBytes("utf-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(output);
            os.flush();
            os.close();
        }

        private String genJson(){

            JSONObject json_object = new JSONObject();

            for (int i = 0; i < categories.size(); i++){

                if (categories.get(i).getTypeName().equals("funny")){

                    Story story = categories.get(i).getStory();
                    json_object.put("title", story.getTitle());
                    json_object.put("content", story.getContent());
                }
            }

            return json_object.toJSONString();
        }

        private JSONObject parseJson(String request) throws ParseException{

//            JSONObject object = new JSONObject(request);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(request);

            return json;
        }
    }
    public static class RomanticStoryHandler extends AbstractHttpHandler{

        @Override
        String getPath() {
            return "/romantic";
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            if ("GET".equals(httpExchange.getRequestMethod())){
                handleGetRequest(httpExchange);
            }

            else if ("POST".equals(httpExchange.getRequestMethod())){
                handlePOSTRequest(httpExchange);
            }
        }

        public void handleGetRequest(HttpExchange httpExchange) throws IOException{
            String response = genJson();
            System.out.println(response);
            String requestURL = httpExchange.getRequestURI().toString();
//            String read = new String(readBytes,  StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request URL: " + requestURL);
            httpExchange.sendResponseHeaders(200, response.length());
            byte[] output = response.getBytes("utf-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(output);
            os.flush();
            os.close();

        }
        public void handlePOSTRequest(HttpExchange httpExchange) throws IOException{


            // Get body
            byte[] read_byte = httpExchange.getRequestBody().readAllBytes();
            String request_body = new String(read_byte, StandardCharsets.UTF_8.name());

            JSONObject json_object = new JSONObject();
            String response = "";
            try {
                json_object = parseJson(request_body);
                response = "Thanks";
                httpExchange.sendResponseHeaders(200, response.length());

            }
            catch (ParseException e){
                System.out.println("ParseException " + e);
                response = "Bad request";
                httpExchange.sendResponseHeaders(400, response.length());
            }

            String title = (String) json_object.get("title");
            String content = (String) json_object.get("content");

            System.out.println("title " + title + " content "+ content);

            Story story = new Story(title, content);
            for (int i = 0; i < categories.size(); i++){

                if (categories.get(i).getTypeName().equals("funny")){

                    categories.get(i).getStories().add(story);
                }
            }


            byte[] output = response.getBytes("utf-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(output);
            os.flush();
            os.close();

        }

        private String genJson(){

            JSONObject json_object = new JSONObject();

            for (int i = 0; i < categories.size(); i++){

                if (categories.get(i).getTypeName().equals("romantic")){

                    Story story = categories.get(i).getStory();
                    json_object.put("title", story.getTitle());
                    json_object.put("content", story.getContent());
                }
            }

            return json_object.toJSONString();
        }
        private JSONObject parseJson(String request) throws ParseException{

//            JSONObject object = new JSONObject(request);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(request);

            return json;
        }
    }
    public static class ScaryStoryHandler extends AbstractHttpHandler{

        @Override
        String getPath() {
            return "/scary";
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            if ("GET".equals(httpExchange.getRequestMethod())){
                handleGetRequest(httpExchange);
            }

            else if ("POST".equals(httpExchange.getRequestMethod())){
                handlePOSTRequest(httpExchange);
            }
        }

        public void handleGetRequest(HttpExchange httpExchange) throws IOException{
            String response = genJson();
            System.out.println(response);
            String requestURL = httpExchange.getRequestURI().toString();
//            String read = new String(readBytsdafaes,  StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request URL: " + requestURL);
            httpExchange.sendResponseHeaders(200, response.length());
            byte[] output = response.getBytes("utf-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(output);
            os.flush();
            os.close();

        }
        public void handlePOSTRequest(HttpExchange httpExchange) throws IOException{

            // Get body
            byte[] read_byte = httpExchange.getRequestBody().readAllBytes();
            String request_body = new String(read_byte, StandardCharsets.UTF_8.name());

            JSONObject json_object = new JSONObject();
            String response = "";
            try {
                json_object = parseJson(request_body);
                response = "Thanks";
                httpExchange.sendResponseHeaders(200, response.length());

            }
            catch (ParseException e){
                System.out.println("ParseException " + e);
                response = "Bad request";
                httpExchange.sendResponseHeaders(400, response.length());
            }

            String title = (String) json_object.get("title");
            String content = (String) json_object.get("content");

            System.out.println("title " + title + " content "+ content);

            Story story = new Story(title, content);
            for (int i = 0; i < categories.size(); i++){

                if (categories.get(i).getTypeName().equals("funny")){

                    categories.get(i).getStories().add(story);
                }
            }


            byte[] output = response.getBytes("utf-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(output);
            os.flush();
            os.close();
        }

    private String genJson(){

            JSONObject json_object = new JSONObject();

            for (int i = 0; i < categories.size(); i++){

                if (categories.get(i).getTypeName().equals("scary")){

                    Story story = categories.get(i).getStory();
                    json_object.put("title", story.getTitle());
                    json_object.put("content", story.getContent());
                }
            }

            return json_object.toJSONString();
        }
        private JSONObject parseJson(String request) throws ParseException{

//            JSONObject object = new JSONObject(request);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(request);

            return json;
        }
    }

    public static class StoryInfoHandler extends AbstractHttpHandler{

        public String getPath(){return "/info";}

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            if ("GET".equals(httpExchange.getRequestMethod())){
                handleGetRequest(httpExchange);
            }
        }

        public void handleGetRequest(HttpExchange httpExchange) throws IOException{
            String response = genJson();
            System.out.println(response);
            String requestURL = httpExchange.getRequestURI().toString();
//            String read = new String(readBytes,  StandardCharsets.UTF_8.name());
            System.out.println("Request Method: " + httpExchange.getRequestMethod());
            System.out.println("Request URL: " + requestURL);
            httpExchange.sendResponseHeaders(200, response.length());
            byte[] output = response.getBytes("utf-8");
            OutputStream os = httpExchange.getResponseBody();
            os.write(output);
            os.flush();
            os.close();

        }

        private String genJson(){
            JSONObject json_object = new JSONObject();
            JSONArray json_array = new JSONArray();

            for (int i = 0; i < categories.size(); i++){

                JSONObject temp_obj = new JSONObject();

                temp_obj.put("genre", categories.get(i).getTypeName());
                temp_obj.put("count", categories.get(i).getStorySize());

                json_array.add(temp_obj);
            }

            json_object.put("storyinfo", json_array);

            return json_object.toJSONString();
        }
    }

    public static ArrayList<Category> loadStories() throws IOException {

        ArrayList<Category> categories = new ArrayList<>();


        File f = new File("./src/edu/skku/myhttpserver/Stories");
        String[] file_names = f.list();

        for (String file : file_names){
//
//            categories.add(new Category(file.split("\\.")[0]));

            String catogory_name = file.split("\\.")[0];
            ArrayList<Story> stories = new ArrayList<>();

            String path = "./src/edu/skku/myhttpserver/Stories/" + file;
            File story_file = new File(path);

            BufferedReader br = new BufferedReader(new FileReader(story_file));

            String line;
            String title_line="", content_line="";

            Boolean title = false, content = false;
            while((line = br.readLine()) != null){

                if (line.equals("title")){

                    if (title || content) {
                        stories.add(new Story(title_line, content_line));
                        title_line = "";
                        content_line = "";
                    }
                    title = true;
                    content = false;
                }

                else if (line.equals("content")){
                    title = false;
                    content = true;
                }

                else {
                    if (title) {
                        title_line = line;
                    }
                    if (content) {
                        content_line += line;
                    }
                }




            }
            stories.add(new Story(title_line, content_line));
            categories.add(new Category(catogory_name, stories));
        }

        return categories;
    }

    public static ArrayList<Category> categories = new ArrayList<>();


    public static void main(String [] args) throws IOException {

        categories = loadStories();

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        List<AbstractHttpHandler> handlers = List.of(
                new StoryInfoHandler(),
                new FunnyStoryHandler(),
                new ScaryStoryHandler(),
                new RomanticStoryHandler()
        );

        for (AbstractHttpHandler handler: handlers) {
            server.createContext(handler.getPath(), handler);
        }

        server.setExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        server.start();
    }
}
