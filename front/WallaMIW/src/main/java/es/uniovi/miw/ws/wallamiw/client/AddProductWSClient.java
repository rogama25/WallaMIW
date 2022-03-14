package es.uniovi.miw.ws.wallamiw.client;

import es.uniovi.miw.ws.wallamiw.webservices.Product;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddProductWSClient {

    public static int add(String title, String description, String price, String category, String image, String stock) throws IOException, InterruptedException {


        String uri = "http://addproducts:8080/addService-0.0.1-SNAPSHOT/api/v1/products/";
        String data = "{\"title\":\"" + title + "\"," +
                 " \"description\":\"" + description + "\"," +
                "\"price\":" + price + "," +
                "\"category\":\"" + category + "\"," +
                "\"image\":\"" + image + "\"," +
                "\"favorites\":" + 0 + "," +
                "\"stock\":" + stock +"}";
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        return response.statusCode();


    }
}
