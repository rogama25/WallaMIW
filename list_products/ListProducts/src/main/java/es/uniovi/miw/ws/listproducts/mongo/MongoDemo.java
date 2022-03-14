package es.uniovi.miw.ws.listproducts.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import es.uniovi.miw.ws.listproducts.entities.Product;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDemo {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection productCollection;
    public MongoDemo(){
        client = MongoClients.create("mongodb://wallamiw:wallamiw@mongo:27017");
        db = client.getDatabase("WallaMIW");
        productCollection = db.getCollection("Products");
    }


    public Product[] getProducts(){
        ObjectMapper o = new ObjectMapper();
        o.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Product producto=null;
        List<Product> products = new ArrayList<>();
        MongoCursor<Document> result = productCollection.find().iterator();

        while(result.hasNext()){
            Document doc = result.next();

            try{
                producto =o.readValue(doc.toJson(),Product.class);
                producto.setId(doc.getObjectId("_id").toString());
                products.add(producto);

            }catch(JsonProcessingException e){
                //No hacemos nada
            }
        }

        return products.toArray(new Product[0]);
    }

    public MongoCollection getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(MongoCollection productCollection) {
        this.productCollection = productCollection;
    }

    public MongoClient getClient() {
        return client;
    }

    public void setClient(MongoClient client) {
        this.client = client;
    }

    public MongoDatabase getDb() {
        return db;
    }

    public void setDb(MongoDatabase db) {
        this.db = db;
    }
}
