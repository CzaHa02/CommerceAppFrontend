package com.kodilla.books.customer;

import com.kodilla.books.product.MainViewProductGrind;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import java.awt.*;
import java.util.Locale;

public class CustomerForm extends FormLayout {

    private MainViewCustomerGrind mainViewCustomerGrind;

    private CustomerService service = CustomerService.getInstance();

    private Binder<MainViewCustomerGrind.Customer> binder = new Binder(MainViewCustomerGrind.Customer.class);

    private Button save = new Button("save");

    private Button delete = new Button("delete");


    private TextField customerName= new TextField("Name of customer");


    private TextField customerSurname= new TextField("Surname of customer");

    private TextField NIP = new TextField("NIP number");

    private TextField Email = new TextField("Email");

    private TextField companyName= new TextField("Name of customer company");

    public CustomerForm (MainViewCustomerGrind mainViewOrderGrind){
        HorizontalLayout buttons = new HorizontalLayout(save,delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        setSizeFull();

        add(customerName,customerSurname,NIP,Email,companyName,buttons);
        binder.bindInstanceFields(this);
        binder.forField(Email).bind("Email");
        this.mainViewCustomerGrind=mainViewOrderGrind;

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        setSizeFull();
    }

    public void setCustomer(MainViewCustomerGrind.Customer customer) {
        binder.setBean(customer);
        setSizeFull();
        if (customer == null) {
            setVisible(false);
        } else {
            setSizeFull();
            setVisible(true);
            customerName.focus();
        }
    }

    private void save() {
        MainViewCustomerGrind.Customer customer = binder.getBean();
        service.save(customer);
        mainViewCustomerGrind.refresh();
        setCustomer(null);
    }



    private void delete() {
        MainViewCustomerGrind.Customer customer = binder.getBean();
        service.delete(customer);
        mainViewCustomerGrind.refresh();
        setCustomer(null);
    }




}



