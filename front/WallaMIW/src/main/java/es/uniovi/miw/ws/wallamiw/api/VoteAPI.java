package es.uniovi.miw.ws.wallamiw.api;

import es.uniovi.miw.ws.wallamiw.client.VoteProductsWSClient;

public class VoteAPI {
    public static int addFav(String id) {
        return VoteProductsWSClient.addFavoriteProduct(id).getFavorites();
    }
}
