package es.uniovi.miw.ws.wallamiw.api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static es.uniovi.miw.ws.wallamiw.client.ListProductsWSClient.getListaProductos;

public class ListAPI {
    public static String getJsonList() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(getListaProductos());
        return json;
    }
}
