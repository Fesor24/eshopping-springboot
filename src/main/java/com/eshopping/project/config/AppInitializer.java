package com.eshopping.project.config;

import com.eshopping.project.entities.Category;
import com.eshopping.project.repositories.ICategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class AppInitializer {

    @Autowired
    private ICategoryRepository categoryRepository;

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
                System.out.println(category.getName());
            }

            this.categoryRepository.saveAll(categories);
        };
    }
}
