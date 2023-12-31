package com.kodilla.commerceApp.customer;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

public class CustomerForm extends FormLayout {

    private MainViewCustomerGrind mainViewCustomerGrind;

    private CustomerService service = CustomerService.getInstance();

    private Binder<MainViewCustomerGrind.Customer> binder = new Binder(MainViewCustomerGrind.Customer.class);

    private Button save = new Button("save");

    private Button delete = new Button("delete");


    private TextField customerName= new TextField("customerName");


    private TextField customerSurname= new TextField("customerSurname");

    private TextField nip = new TextField("nip");


    private TextField mail = new TextField("Email of customer");

    private TextField nameOfCustomerCompany= new TextField("Name of customer company");

    private TextField email = new TextField("email");


    public CustomerForm (MainViewCustomerGrind mainViewOrderGrind){
        HorizontalLayout buttons = new HorizontalLayout(save,delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        setSizeFull();
        add(customerName,customerSurname,nip,email,nameOfCustomerCompany,buttons);
        binder.bindInstanceFields(this);
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



