
package es.uniovi.miw.ws.wallamiw.webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.uniovi.miw.ws.wallamiw.webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddFavorite_QNAME = new QName("http://webservices.voteproducts.ws.miw.uniovi.es/", "addFavorite");
    private final static QName _AddFavoriteResponse_QNAME = new QName("http://webservices.voteproducts.ws.miw.uniovi.es/", "addFavoriteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.uniovi.miw.ws.wallamiw.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddFavorite }
     * 
     */
    public AddFavorite createAddFavorite() {
        return new AddFavorite();
    }

    /**
     * Create an instance of {@link AddFavoriteResponse }
     * 
     */
    public AddFavoriteResponse createAddFavoriteResponse() {
        return new AddFavoriteResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFavorite }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddFavorite }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.voteproducts.ws.miw.uniovi.es/", name = "addFavorite")
    public JAXBElement<AddFavorite> createAddFavorite(AddFavorite value) {
        return new JAXBElement<AddFavorite>(_AddFavorite_QNAME, AddFavorite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFavoriteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddFavoriteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.voteproducts.ws.miw.uniovi.es/", name = "addFavoriteResponse")
    public JAXBElement<AddFavoriteResponse> createAddFavoriteResponse(AddFavoriteResponse value) {
        return new JAXBElement<AddFavoriteResponse>(_AddFavoriteResponse_QNAME, AddFavoriteResponse.class, null, value);
    }

}
