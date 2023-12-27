package com.kodilla.commerceApp.product;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ProductService {

    ProductStatus productStatus;
    private Set<MainViewProductGrind.Product> products;
    private static ProductService productService;

    private ProductService() {
        this.products= exampleData();
    }

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public void save(MainViewProductGrind.Product product) {
        this.products.add(product);
    }

    public void delete(MainViewProductGrind.Product product) {
        this.products.remove(product);
    }



    public Set<MainViewProductGrind.Product> getProducts() {
        return new HashSet<>(products);
    }

    public void addProduct(MainViewProductGrind.Product product) {
        this.products.add(product);
    }

    public Set <MainViewProductGrind.Product> findByNameProduct(String name){
        return products.stream().filter(product->product.getName().contains(name))

                .collect(Collectors.toSet());
    }

    private Set<MainViewProductGrind.Product> exampleData() {
        Set<MainViewProductGrind.Product> products = new HashSet<>();
        products.add(new MainViewProductGrind.Product("Rura stalowa szwowa", 9655625,  1234, 15,ProductStatus.RESERVATION));
        products.add(new MainViewProductGrind.Product("Rura stalowa szwowa", 4156416,  365, 78,ProductStatus.IN_STOCK));
        products.add(new MainViewProductGrind.Product("Rura stalowa szwowa", 9655625,  1234, 15,ProductStatus.SOLD));
        products.add(new MainViewProductGrind.Product("Rura stalowa bezszwowa2", 1324345,  9273.23,12, ProductStatus.SOLD));
        products.add(new MainViewProductGrind.Product("Rura stalowa kotłowa", 53255,  334, 100,ProductStatus.SOLD));
        products.add(new MainViewProductGrind.Product("Rura stalowa bezszwowa1", 6723245,  121,25, ProductStatus.RESERVATION));
        return products;
    }
}

