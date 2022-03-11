package es.uniovi.miw.ws.wallamiw.client;

import es.uniovi.miw.ws.wallamiw.webservices.IListProductsWS;
import es.uniovi.miw.ws.wallamiw.webservices.ListProductsWSService;
import es.uniovi.miw.ws.wallamiw.webservices.Product;

import java.util.List;

public class ListProductsWSClient {
   public static List<Product> getListaProductos(){
        ListProductsWSService service = new ListProductsWSService();
        IListProductsWS listProductsWS = service.getListProductsWSPort();
        return listProductsWS.listProducts();
    }
}
