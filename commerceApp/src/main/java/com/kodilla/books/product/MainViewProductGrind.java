package com.kodilla.books.product;

import com.kodilla.books.MainView;
import com.kodilla.books.product.ProductForm;
import com.kodilla.books.product.ProductService;
import com.kodilla.books.product.ProductStatus;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.Objects;


@Route("Products")

public class MainViewProductGrind extends VerticalLayout {

    private Button back = new Button("Go back");
    private Button addNewProduct = new Button("Add new product");
    private TextField filter = new TextField();
    private ProductForm form = new ProductForm(this);
    private ProductService productService = ProductService.getInstance();
    private Grid<Product> productGrid = new Grid<>(Product.class);

    public MainViewProductGrind() {
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        productGrid.setColumns("name", "code", "quantity", "price", "status");

        addNewProduct.addClickListener(e -> {
            productGrid.asSingleSelect().clear();
            form.setProduct(new Product());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewProduct,back);
        HorizontalLayout mainContent = new HorizontalLayout(productGrid, form);

        HorizontalLayout header = new HorizontalLayout();
        H1 title = new H1( "PRODUCTS" );
        header.add(title);
        add(header);

        mainContent.setSizeFull();
        productGrid.setSizeFull();

        add(toolbar, mainContent);
        form.setProduct(null);
        back.addClickListener(event -> {
            UI.getCurrent().navigate(MainView.class);
        });
        mainContent.setSizeFull();
        setSizeFull();
        productGrid.setSizeFull();
        refresh();

        productGrid.asSingleSelect().addValueChangeListener(event -> form.setProduct(productGrid.asSingleSelect().getValue()));

        productGrid.asSingleSelect().addValueChangeListener(event -> {
            form.setProduct(productGrid.asSingleSelect().getValue());
        });
    }


    public static class Product {


        private String name;
        private int code;

        private int quantity;
        private double price;

        private ProductStatus status;

        public Product() {
        }

        public Product(String name, int code, double price, int quantity, ProductStatus status) {
            this.name = name;
            this.code = code;
            this.price = price;
            this.status = status;
            this.quantity = quantity;

        }


        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public ProductStatus getStatus() {
            return status;
        }

        public void setStatus(ProductStatus status) {
            this.status = status;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return code == product.code && quantity == product.quantity && Double.compare(price, product.price) == 0 && Objects.equals(name, product.name) && Objects.equals(status, product.status);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, code, quantity, price, status);
        }
    }


    private void update() {
        productGrid.setItems(productService.findByNameProduct(filter.getValue()));
    }

    public void refresh() {
        productGrid.setItems(productService.getProducts());
    }
}

