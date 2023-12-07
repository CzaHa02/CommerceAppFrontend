package com.kodilla.books.order;
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

public class OrderForm extends FormLayout {

    private MainViewOrderGrind mainViewOrderGrind;

    private OrderService service = OrderService.getInstance();

    private Binder<MainViewOrderGrind.Order> binder = new Binder(MainViewOrderGrind.Order.class);

    private Button save = new Button("save");

    private Button delete = new Button("delete");

    Locale finnishLocale = new Locale("fi", "FI");

    DatePicker datePicker = new DatePicker("Select a date:");

    private TextField orderName= new TextField("order name");


   private TextField productCode = new TextField("code of product");

   private TextField customer = new TextField("customer");

   private TextField description = new TextField("description");

   private TextField quantity = new TextField("quantity");

   public OrderForm (MainViewOrderGrind mainViewOrderGrind){
       HorizontalLayout buttons = new HorizontalLayout(save,delete);
       save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
       datePicker.setLocale(finnishLocale);
       setSizeFull();

       add(orderName,datePicker,productCode,customer,description,quantity,buttons);
       binder.bindInstanceFields(this);
       this.mainViewOrderGrind=mainViewOrderGrind;

       save.addClickListener(event -> save());
       delete.addClickListener(event -> delete());
       setSizeFull();
   }

    public void setOrder(MainViewOrderGrind.Order order) {
        binder.setBean(order);
        setSizeFull();
        if (order == null) {
            setVisible(false);
        } else {
            setSizeFull();
            setVisible(true);
            orderName.focus();
        }
    }

    private void save() {
        MainViewOrderGrind.Order order = binder.getBean();
        order.setDate(datePicker.getValue());
        service.save(order);

        mainViewOrderGrind.refresh();
        setOrder(null);
    }



    private void delete() {
        MainViewOrderGrind.Order order = binder.getBean();
        service.delete(order);
        mainViewOrderGrind.refresh();
        setOrder(null);
    }



}



