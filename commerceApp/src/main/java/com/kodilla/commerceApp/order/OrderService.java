package com.kodilla.commerceApp.order;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService  {
    private Set<MainViewOrderGrind.Order> orders;
    private static OrderService orderService;

    private OrderService() {
        this.orders= exampleData();
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService= new OrderService();
        }
        return orderService;
    }

    public void save(MainViewOrderGrind.Order order) {
        this.orders.add(order);
    }

    public void delete(MainViewOrderGrind.Order order) {
        this.orders.remove(order);
    }



    public Set<MainViewOrderGrind.Order> getOrders() {
        return new HashSet<>(orders);
    }

    public void addOrder(MainViewOrderGrind.Order order) {
        this.orders.add(order);
    }

    public Set <MainViewOrderGrind.Order> findByNameOrder(String name){
        return orders.stream().filter(order->order.getOrderName().contains(name))

                .collect(Collectors.toSet());
    }

    private Set<MainViewOrderGrind.Order> exampleData() {
        Set<MainViewOrderGrind.Order> orders = new HashSet<>();
        orders.add(new MainViewOrderGrind.Order(LocalDate.now(), 12,"sda","sd",111,"name"));
        orders.add(new MainViewOrderGrind.Order(LocalDate.of(2024,12,12), 458775,"Pawlex","Rura stalowa xxx",568,"Order for Pawlex"));
        orders.add(new MainViewOrderGrind.Order(LocalDate.of(2025,6,16), 698574,"Romex","Rura stalowa yyy",123,"Order for Romex"));
        orders.add(new MainViewOrderGrind.Order(LocalDate.of(2024,2,28), 314526,"Stalgast","Rura stalowa zzz",213,"Order for Stalgast"));
        orders.add(new MainViewOrderGrind.Order(LocalDate.of(2024,1,1), 789254,"Kotlon","Płaszcz stalowy",569,"Order for Kotlon"));


        return orders;
    }
}

