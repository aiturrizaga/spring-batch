package pe.financieraoh.batch.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import pe.financieraoh.batch.model.Product;

import java.util.List;

@Slf4j
public class ProductWriter implements ItemWriter<Product> {
    @Override
    public void write(List<? extends Product> list) throws Exception {
        for (Product product : list) {
            log.info("Producto procesado: {}", product);
        }
    }
}
