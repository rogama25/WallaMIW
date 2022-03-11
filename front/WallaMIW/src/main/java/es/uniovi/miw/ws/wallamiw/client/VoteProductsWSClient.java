package es.uniovi.miw.ws.wallamiw.client;

import es.uniovi.miw.ws.wallamiw.webservices.IVoteProductsWS;
import es.uniovi.miw.ws.wallamiw.webservices.Product;
import es.uniovi.miw.ws.wallamiw.webservices.VoteProductsWSService;

public class VoteProductsWSClient {

    public static Product addFavoriteProduct(String id){
        VoteProductsWSService service = new VoteProductsWSService();
        IVoteProductsWS voteProductsWS = service.getVoteProductsWSPort();
        return voteProductsWS.addFavorite(id);
    }
}
