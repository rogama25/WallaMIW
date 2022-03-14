package es.uniovi.webservices.addService.repositories;

import es.uniovi.webservices.addService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product,String> {
    @Query(value = "{manual : ?0}", delete = true)
    Long deleteBooksByCategory(boolean manual);
}
