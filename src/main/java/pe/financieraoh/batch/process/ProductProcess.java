package pe.financieraoh.batch.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import pe.financieraoh.batch.model.Product;

import java.math.BigDecimal;

@Slf4j
public class ProductProcess implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        log.info("El producto {} se esta procesando", item.getTitle());
        item.setPrice(item.getPrice().multiply(BigDecimal.TEN));
        return item;
    }
}
