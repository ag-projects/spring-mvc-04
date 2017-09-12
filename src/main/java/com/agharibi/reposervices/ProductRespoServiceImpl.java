package com.agharibi.reposervices;


import com.agharibi.commands.ProductForm;
import com.agharibi.converters.ProductFormToProduct;
import com.agharibi.domain.Product;
import com.agharibi.repositories.ProductRepository;
import com.agharibi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class ProductRespoServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;

    @Override
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);

    }

    @Override
    public Product saveOrUpdate(ProductForm productForm) {
        return saveOrUpdate(productFormToProduct.convert(productForm));
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }
}

