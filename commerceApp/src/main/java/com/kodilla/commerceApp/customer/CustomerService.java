package com.kodilla.commerceApp.customer;



import com.vaadin.flow.router.Route;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
@Route
public class CustomerService  {
    private Set<MainViewCustomerGrind.Customer> customers;
    private static CustomerService customerService;

    private CustomerService() {
        this.customers= exampleData();
    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService= new CustomerService();
        }
        return customerService;
    }

    public void save(MainViewCustomerGrind.Customer customer) {
        this.customers.add(customer);
    }

    public void delete(MainViewCustomerGrind.Customer customer) {
        this.customers.remove(customer);
    }



    public Set<MainViewCustomerGrind.Customer> getCustomers() {
        return new HashSet<>(customers);
    }

    public void addCustomer(MainViewCustomerGrind.Customer order) {
        this.customers.add(order);
    }

    public Set <MainViewCustomerGrind.Customer> findByNameCustomer(String name){
        return customers.stream().filter(order->order.getCustomerName().contains(name))

                .collect(Collectors.toSet());
    }

    private Set<MainViewCustomerGrind.Customer> exampleData() {
        Set<MainViewCustomerGrind.Customer> customers = new HashSet<>();
       customers.add(new MainViewCustomerGrind.Customer("dsad", "sdsad",1111,"sd","sda"));
               customers.add(new MainViewCustomerGrind.Customer("dsad", "sdsad",1111,"sd","sda"));



        return customers;
    }
}

