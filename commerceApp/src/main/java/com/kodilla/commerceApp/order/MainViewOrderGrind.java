package com.kodilla.commerceApp.order;

import com.kodilla.commerceApp.overallView.HomepageMainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDate;
import java.util.Objects;
@PermitAll
@Route("Orders")
public class MainViewOrderGrind extends VerticalLayout {
    private Button back = new Button("Go back");
private Button addNewOrder = new Button("Add new order");

private TextField filter = new TextField();



private OrderForm form = new OrderForm(this);

private OrderService orderService = OrderService.getInstance();

private Grid<Order>orderGrid = new Grid<>(Order.class);


public MainViewOrderGrind(){
    filter.setPlaceholder("Filter by order name...");
    filter.setClearButtonVisible(true);
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(event -> update());
    orderGrid.setColumns("orderName","date", "productCode", "customer", "description", "quantity");

    addNewOrder.addClickListener(event -> {
        orderGrid.asSingleSelect().clear();
    form.setOrder(new Order());



});
HorizontalLayout toolbar = new HorizontalLayout(filter,addNewOrder, back);
HorizontalLayout mainContent = new HorizontalLayout(orderGrid, form);

    HorizontalLayout header = new HorizontalLayout();
    H1 title = new H1( "ORDERS" );
    header.add(title);
    add(header);


mainContent.setSizeFull();
orderGrid.setSizeFull();
add(toolbar, mainContent);
form.setOrder(null);
setSizeFull();

orderGrid.setSizeFull();
    back.addClickListener(event -> {
        UI.getCurrent().navigate(HomepageMainView.class);
    });
refresh();

orderGrid.asSingleSelect().addValueChangeListener(event -> form.setOrder(orderGrid.asSingleSelect().getValue()));

    orderGrid.asSingleSelect().addValueChangeListener(event -> {
        form.setOrder(orderGrid.asSingleSelect().getValue());
    });
}

    public static class Order {

    private String orderName;
        private LocalDate date;
        private int productCode;

        private String customer;

        private String description;

        private int quantity;




        public Order(LocalDate datePicker, int productCode, String customer, String description, int quantity, String orderName) {
            this.date = datePicker;
            this.productCode = productCode;
            this.customer = customer;
            this.description = description;
            this.quantity = quantity;
            this.orderName = orderName;
        }
public Order(){}

        public void setCustomer(String customer) {
            this.customer = customer;
        }
        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getProductCode() {
            return productCode;
        }

        public void setProductCode(int productCode) {
            this.productCode = productCode;
        }

        public String getCustomer() {
            return customer;
        }



        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return productCode == order.productCode && quantity == order.quantity && Objects.equals(orderName, order.orderName) && Objects.equals(date, order.date) && Objects.equals(customer, order.customer) && Objects.equals(description, order.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(orderName, date, productCode, customer, description, quantity);
        }
    }

    private void update() {
        orderGrid.setItems(orderService.findByNameOrder(filter.getValue()));
    }

    public void refresh() {
        orderGrid.setItems(orderService.getOrders());
    }


}
