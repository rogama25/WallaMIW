package es.uniovi.miw.ws.voteproducts.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import es.uniovi.miw.ws.voteproducts.entities.Product;
import org.bson.types.ObjectId;
import org.bson.Document;

import java.util.NoSuchElementException;


public class MongoDemo {

    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection productCollection;
    public MongoDemo(){
        client = MongoClients.create("mongodb://wallamiw:wallamiw@mongo:27017");
        db = client.getDatabase("WallaMIW");
        productCollection = db.getCollection("Products");
    }

    public Product updateFavorites(String id)  {
        Document docInicial;
        Document docFinal;
        BasicDBObject whereQuery = new BasicDBObject();
        if (!ObjectId.isValid(id))
            throw new NoSuchElementException();
        whereQuery.put("_id", new ObjectId(id));

        MongoCursor<Document> documents = productCollection.find(whereQuery).iterator();
        if (documents.hasNext()){
            docInicial = documents.next();
        }else{
            throw new NoSuchElementException();
        }

        int favoritesInicial=docInicial.getInteger("favorites");
        productCollection.updateOne(new BasicDBObject("_id", new ObjectId(id)), new BasicDBObject("$set", new BasicDBObject("favorites", favoritesInicial+1)));

        documents = productCollection.find(whereQuery).iterator();
        if (documents.hasNext()){
            docFinal = documents.next();
        }else{
            throw new NoSuchElementException();
        }

        Product productoConvertido;
        try{
            ObjectMapper o = new ObjectMapper();
            o.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            productoConvertido =o.readValue(docFinal.toJson(),Product.class);
            productoConvertido.setId(docFinal.getObjectId("_id").toString());
        }catch(JsonProcessingException e){
            return null;
        }

        return productoConvertido;
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

    public MongoCollection getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(MongoCollection productCollection) {
        this.productCollection = productCollection;
    }
}
