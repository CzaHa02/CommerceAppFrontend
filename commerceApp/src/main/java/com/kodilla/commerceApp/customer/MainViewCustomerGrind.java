package com.kodilla.commerceApp.customer;


import com.kodilla.commerceApp.OverallView.HomepageMainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


import java.util.Objects;
@PermitAll
@Route("Customers")
public class MainViewCustomerGrind extends VerticalLayout {
    private Button back = new Button("Go back");
    private Button addNewCustomer = new Button("Add new customer");

    private TextField filter = new TextField();

    private CustomerForm form = new CustomerForm(this);

    private CustomerService customerService = CustomerService.getInstance();

    private Grid<Customer> customerGrid = new Grid<>(Customer.class);


    public MainViewCustomerGrind() {
        filter.setPlaceholder("Filter by customer name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> update());
<<<<<<< HEAD:commerceApp/src/main/java/com/kodilla/commerceApp/customer/MainViewCustomerGrind.java
        customerGrid.setColumns("customerName", "customerSurname", "NIP", "mail", "nameOfCustomerCompany");
=======
        customerGrid.setColumns("customerName", "customerSurname", "nip", "email", "nameOfCustomerCompany");
>>>>>>> 91782a1b393e1c01b6e9df29cba6ec6610f3deba:commerceApp/src/main/java/com/kodilla/books/customer/MainViewCustomerGrind.java

        addNewCustomer.addClickListener(event -> {
            customerGrid.asSingleSelect().clear();
            form.setCustomer(new Customer());


        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewCustomer,back);
        HorizontalLayout mainContent = new HorizontalLayout(customerGrid, form);

        HorizontalLayout header = new HorizontalLayout();
        H1 title = new H1( "CUSTOMERS" );
        header.add(title);
        add(header);

        mainContent.setSizeFull();
        customerGrid.setSizeFull();
        add(toolbar, mainContent);
       form.setCustomer(null);
        setSizeFull();
        customerGrid.setSizeFull();
        back.addClickListener(event -> {
            UI.getCurrent().navigate(HomepageMainView.class);
        });
        refresh();

        customerGrid.asSingleSelect().addValueChangeListener(event -> form.setCustomer(customerGrid.asSingleSelect().getValue()));

        customerGrid.asSingleSelect().addValueChangeListener(event -> {
            form.setCustomer(customerGrid.asSingleSelect().getValue());
        });
    }

    public static class Customer {

        private String customerName;
        private String customerSurname;

<<<<<<< HEAD:commerceApp/src/main/java/com/kodilla/commerceApp/customer/MainViewCustomerGrind.java
        private int NIP;
        private String mail;

        private String nameOfCustomerCompany;



        public Customer(){}


        public Customer(String customerName, String customerSurname, int NIP, String mail, String nameOfCustomerCompany) {
            this.customerName = customerName;
            this.customerSurname = customerSurname;
            this.NIP = NIP;
            this.mail = mail;
=======
        private int nip;
        private String email;

        private String nameOfCustomerCompany;

        public Customer(String customerName, String customerSurname, int nip,String email, String nameOfCustomerCompany) {
            this.customerName = customerName;
            this.customerSurname = customerSurname;
            this.nip = nip;
            this.email = email;
>>>>>>> 91782a1b393e1c01b6e9df29cba6ec6610f3deba:commerceApp/src/main/java/com/kodilla/books/customer/MainViewCustomerGrind.java
            this.nameOfCustomerCompany = nameOfCustomerCompany;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerSurname() {
            return customerSurname;
        }

        public void setCustomerSurname(String customerSurname) {
            this.customerSurname = customerSurname;
        }

        public int getNip() {
            return nip;
        }

        public void setNip(int nip) {
            this.nip = nip;
        }

<<<<<<< HEAD:commerceApp/src/main/java/com/kodilla/commerceApp/customer/MainViewCustomerGrind.java
        public String getMail() {
            return mail;
        }


        public void setMail(String mail) {
            this.mail = mail;
=======
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
           this.email = email;
>>>>>>> 91782a1b393e1c01b6e9df29cba6ec6610f3deba:commerceApp/src/main/java/com/kodilla/books/customer/MainViewCustomerGrind.java
        }

        public String getNameOfCustomerCompany() {
            return nameOfCustomerCompany;
        }

        public void setNameOfCustomerCompany(String nameOfCustomerCompany) {
            this.nameOfCustomerCompany = nameOfCustomerCompany;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
<<<<<<< HEAD:commerceApp/src/main/java/com/kodilla/commerceApp/customer/MainViewCustomerGrind.java
            return NIP == customer.NIP && Objects.equals(customerName, customer.customerName) && Objects.equals(customerSurname, customer.customerSurname) && Objects.equals(mail, customer.mail) && Objects.equals(nameOfCustomerCompany, customer.nameOfCustomerCompany);
=======
            return nip == customer.nip && Objects.equals(customerName, customer.customerName) && Objects.equals(customerSurname, customer.customerSurname) && Objects.equals(email, customer.email) && Objects.equals(nameOfCustomerCompany, customer.nameOfCustomerCompany);
>>>>>>> 91782a1b393e1c01b6e9df29cba6ec6610f3deba:commerceApp/src/main/java/com/kodilla/books/customer/MainViewCustomerGrind.java
        }

        @Override
        public int hashCode() {
<<<<<<< HEAD:commerceApp/src/main/java/com/kodilla/commerceApp/customer/MainViewCustomerGrind.java
            return Objects.hash(customerName, customerSurname, NIP, mail, nameOfCustomerCompany);
=======
            return Objects.hash(customerName, customerSurname, nip, email, nameOfCustomerCompany);
>>>>>>> 91782a1b393e1c01b6e9df29cba6ec6610f3deba:commerceApp/src/main/java/com/kodilla/books/customer/MainViewCustomerGrind.java
        }



    }

    private void update() {
        customerGrid.setItems(customerService.findByNameCustomer(filter.getValue()));
    }

    public void refresh() {
        customerGrid.setItems(customerService.getCustomers());
    }
}
