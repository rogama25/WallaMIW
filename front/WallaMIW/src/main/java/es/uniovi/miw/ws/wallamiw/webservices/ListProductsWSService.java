
package es.uniovi.miw.ws.wallamiw.webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ListProductsWSService", targetNamespace = "http://webservices.listproducts.ws.miw.uniovi.es/", wsdlLocation = "http://listproducts:8080/listProducts-1.0-SNAPSHOT/list_products?wsdl")
public class ListProductsWSService
    extends Service
{

    private final static URL LISTPRODUCTSWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException LISTPRODUCTSWSSERVICE_EXCEPTION;
    private final static QName LISTPRODUCTSWSSERVICE_QNAME = new QName("http://webservices.listproducts.ws.miw.uniovi.es/", "ListProductsWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://listproducts:8080/listProducts-1.0-SNAPSHOT/list_products?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LISTPRODUCTSWSSERVICE_WSDL_LOCATION = url;
        LISTPRODUCTSWSSERVICE_EXCEPTION = e;
    }

    public ListProductsWSService() {
        super(__getWsdlLocation(), LISTPRODUCTSWSSERVICE_QNAME);
    }

    public ListProductsWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LISTPRODUCTSWSSERVICE_QNAME, features);
    }

    public ListProductsWSService(URL wsdlLocation) {
        super(wsdlLocation, LISTPRODUCTSWSSERVICE_QNAME);
    }

    public ListProductsWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LISTPRODUCTSWSSERVICE_QNAME, features);
    }

    public ListProductsWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ListProductsWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IListProductsWS
     */
    @WebEndpoint(name = "ListProductsWSPort")
    public IListProductsWS getListProductsWSPort() {
        return super.getPort(new QName("http://webservices.listproducts.ws.miw.uniovi.es/", "ListProductsWSPort"), IListProductsWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IListProductsWS
     */
    @WebEndpoint(name = "ListProductsWSPort")
    public IListProductsWS getListProductsWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.listproducts.ws.miw.uniovi.es/", "ListProductsWSPort"), IListProductsWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LISTPRODUCTSWSSERVICE_EXCEPTION!= null) {
            throw LISTPRODUCTSWSSERVICE_EXCEPTION;
        }
        return LISTPRODUCTSWSSERVICE_WSDL_LOCATION;
    }

}
