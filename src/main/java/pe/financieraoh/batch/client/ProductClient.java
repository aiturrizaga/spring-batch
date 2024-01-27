package pe.financieraoh.batch.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pe.financieraoh.batch.model.Product;

import java.util.List;

@FeignClient(value = "product-client", url = "https://fakestoreapi.com")
public interface ProductClient {

    @GetMapping("/products")
    List<Product> getAll();
}
