package pe.financieraoh.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.financieraoh.batch.client.ProductClient;
import pe.financieraoh.batch.model.Product;
import pe.financieraoh.batch.process.ProductProcess;
import pe.financieraoh.batch.reader.ProductReader;
import pe.financieraoh.batch.writer.ProductWriter;


@Configuration
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private final ProductClient productClient;

    public SpringBatchConfig(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Bean
    public ItemReader<Product> productItemReader() {
        return new ProductReader(productClient);
    }

    @Bean
    public ItemProcessor<Product, Product> productItemProcessor() {
        return new ProductProcess();
    }

    @Bean
    public ItemWriter<Product> productItemWriter() {
        return new ProductWriter();
    }


    @Bean
    public Step productStep() {
        return stepBuilderFactory.get("productStep")
                .<Product, Product>chunk(100)
                .reader(productItemReader())
                .processor(productItemProcessor())
                .writer(productItemWriter())
                .build();
    }

    @Bean
    public Job productJob() {
        return jobBuilderFactory.get("productJob")
                .incrementer(new RunIdIncrementer())
                .flow(productStep())
                .end()
                .build();
    }
}
