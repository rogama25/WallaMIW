package es.uniovi.webservices.addService.controllers;

import es.uniovi.webservices.addService.model.Product;
import es.uniovi.webservices.addService.repositories.ProductRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@EnableMongoRepositories
public class ProductsController {

    private final ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository){
        this.productRepository = productRepository;

    }
    
    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


    @PostConstruct
    public void getProducts(){

        productRepository.deleteBooksByCategory(false);
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    //Se obtiene la respuesta con todos los productos

        ResponseEntity<ArrayList> response = restTemplate.exchange("https://fakestoreapi.com/products", HttpMethod.GET,entity,ArrayList.class);
        ArrayList<Map> list = response.getBody();
        Product product;
        for (int i=0;i<list.size();i++){
            product = new Product();
            product.setTitle((String) list.get(i).get("title"));
            product.setPrice(((Number)list.get(i).get("price")).doubleValue());
            product.setDescription((String) list.get(i).get("description"));
            product.setCategory((String) list.get(i).get("category"));
            product.setImage((String) list.get(i).get("image"));
            product.setManual(false);
            product.setStock(10);
            product.setFavorites(0);
            productRepository.save(product);
        }

    }



    @PostMapping
    public ResponseEntity<?> postProduct( @RequestBody Product product) {

        product.setManual(true);
        productRepository.save(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }
}
