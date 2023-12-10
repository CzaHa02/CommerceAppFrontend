package com.kodilla.books.customer;


import com.kodilla.books.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


import java.util.Objects;
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
        customerGrid.setColumns("customerName", "customerSurname", "nip", "email", "nameOfCustomerCompany");

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
            UI.getCurrent().navigate(MainView.class);
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

        private int nip;
        private String email;

        private String nameOfCustomerCompany;

        public Customer(String customerName, String customerSurname, int nip,String email, String nameOfCustomerCompany) {
            this.customerName = customerName;
            this.customerSurname = customerSurname;
            this.nip = nip;
            this.email = email;
            this.nameOfCustomerCompany = nameOfCustomerCompany;
        }

        public Customer(){}

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
           this.email = email;
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
            return nip == customer.nip && Objects.equals(customerName, customer.customerName) && Objects.equals(customerSurname, customer.customerSurname) && Objects.equals(email, customer.email) && Objects.equals(nameOfCustomerCompany, customer.nameOfCustomerCompany);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerName, customerSurname, nip, email, nameOfCustomerCompany);
        }



    }

    private void update() {
        customerGrid.setItems(customerService.findByNameCustomer(filter.getValue()));
    }

    public void refresh() {
        customerGrid.setItems(customerService.getCustomers());
    }
}
