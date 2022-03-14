package es.uniovi.miw.ws.voteproducts.webservices;

import es.uniovi.miw.ws.voteproducts.entities.Product;
import es.uniovi.miw.ws.voteproducts.mongo.MongoDemo;

import javax.jws.WebService;
import java.util.NoSuchElementException;

@WebService(endpointInterface =
        "es.uniovi.miw.ws.voteproducts.webservices.IVoteProductsWS")
public class VoteProductsWS implements IVoteProductsWS{

    private MongoDemo mongoDemo = new MongoDemo();

    @Override
    public Product addFavorite(String id) {
        return mongoDemo.updateFavorites(id);
    }
}
