package es.uniovi.miw.ws.listproducts.webservices;

import es.uniovi.miw.ws.listproducts.entities.Product;
import es.uniovi.miw.ws.listproducts.mongo.MongoDemo;

import javax.jws.WebService;

@WebService(endpointInterface =
        "es.uniovi.miw.ws.listproducts.webservices.IListProductsWS")
public class ListProductsWS implements IListProductsWS{

    private MongoDemo mongoDemo = new MongoDemo();

    @Override
    public Product[] listProducts() {
        Product[] productos = (Product[]) mongoDemo.getProducts();
        return productos;

    }
}
