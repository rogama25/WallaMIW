package es.uniovi.miw.ws.listproducts.webservices;
import es.uniovi.miw.ws.listproducts.entities.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IListProductsWS {
    @WebMethod
    public Product[] listProducts();
}
