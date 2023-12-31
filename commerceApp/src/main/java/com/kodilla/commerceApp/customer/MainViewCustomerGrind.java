package com.kodilla.commerceApp.customer;



import com.kodilla.commerceApp.overallView.HomepageMainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
        customerGrid.setColumns("customerName", "customerSurname", "NIP", "mail", "ameOfCustomerCompany");

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

        private int NIP;
        private String mail;

        private String nameOfCustomerCompany;



        public Customer(){}

        public Customer(String customerName, String customerSurname, int NIP, String mail, String nameOfCustomerCompany) {
            this.customerName = customerName;
            this.customerSurname = customerSurname;
            this.NIP = NIP;
            this.mail = mail;
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

        public int getNIP() {
            return NIP;
        }

        public void setNIP(int NIP) {
            this.NIP = NIP;
        }

        public String getMail() {
            return mail;
        }

        public void setEmail(String mail) {
            this.mail = mail;
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
            return NIP == customer.NIP && Objects.equals(customerName, customer.customerName) && Objects.equals(customerSurname, customer.customerSurname) && Objects.equals(mail, customer.mail) && Objects.equals(nameOfCustomerCompany, customer.nameOfCustomerCompany);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerName, customerSurname, NIP, mail, nameOfCustomerCompany);
        }



    }

    private void update() {
        customerGrid.setItems(customerService.findByNameCustomer(filter.getValue()));
    }

    public void refresh() {
        customerGrid.setItems(customerService.getCustomers());
    }
}
