package es.uniovi.miw.ws.voteproducts.webservices;

import es.uniovi.miw.ws.voteproducts.entities.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IVoteProductsWS {
    @WebMethod
    public Product addFavorite(String id);
}
