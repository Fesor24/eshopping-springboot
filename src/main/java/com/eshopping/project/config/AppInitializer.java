package com.eshopping.project.config;

import com.eshopping.project.entities.Category;
import com.eshopping.project.entities.Product;
import com.eshopping.project.repositories.ICategoryRepository;
import com.eshopping.project.repositories.IProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppInitializer {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IProductRepository productRepository;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            String relPath = "\\src\\main\\java\\com\\eshopping\\project\\config";
            //String categoryJsonPath = Paths.get("").toAbsolutePath().toString() + relPath + "\\category.json";
            String categoryJsonPath = System.getProperty("user.dir") + relPath +  "\\category.json";

            File file = new File(categoryJsonPath);

            if(!file.exists()){
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            List<Category> categories = mapper.readValue(file,
                    new TypeReference<List<Category>>() {});

            for(Category category: categories){
                List<Product> categoryProducts = new ArrayList<Product>();

                for(Product product: category.getProducts()){
                    product.setCategory(category);
                    categoryProducts.add(product);
                    System.out.println(category.getName() + " product item: " + product.getName());
                }

                category.getProducts().addAll(categoryProducts);
            }

            this.categoryRepository.saveAll(categories);
        };
    }
}
