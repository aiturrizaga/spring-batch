package pe.financieraoh.batch.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import pe.financieraoh.batch.client.ProductClient;
import pe.financieraoh.batch.model.Product;

import java.util.Iterator;

@Slf4j
public class ProductReader implements ItemReader<Product> {

    private final ProductClient productClient;
    private Iterator<Product> productIterator;

    public ProductReader(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public Product read() {
        if (productIterator == null) {
            log.info("Product reader: Obteniendo los productos");
            productIterator = productClient.getAll().iterator();
        }

        if (productIterator.hasNext()) {
            return productIterator.next();
        } else {
            productIterator = null;
            return null;
        }
    }
}
