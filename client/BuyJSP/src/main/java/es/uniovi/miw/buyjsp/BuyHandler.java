package es.uniovi.miw.buyjsp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuyHandler {
    public int buy(int id, int amount) throws Exception {
        String uri = "http://localhost:84/products/" + id + "/buy";
        String data = "{\"amount\":" + amount + "}";
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
