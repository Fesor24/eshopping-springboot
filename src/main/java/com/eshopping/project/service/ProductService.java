package com.eshopping.project.service;

import com.eshopping.project.entities.Product;
import com.eshopping.project.models.response.product.GetProductResponse;
import com.eshopping.project.repositories.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseEntityService<GetProductResponse, Long, Product, IProductRepository>
implements IProductService {

    @Autowired
    public ProductService(ModelMapper mapper, IProductRepository repository) {
        super(mapper, GetProductResponse.class, Product.class, repository);
    }
}
