package com.kodilla.commerceApp.product;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
public class ProductForm extends FormLayout  {
    private MainViewProductGrind mainView;
    private ProductService service = ProductService.getInstance();
    private Binder<MainViewProductGrind.Product> binder = new Binder<MainViewProductGrind.Product>(MainViewProductGrind.Product.class);
    private Button save = new Button("save");
    private Button delete = new Button("delete");



    private TextField name = new TextField("name");
     private TextField code = new TextField("code");

 private TextField quantity = new TextField("quantity");
 private TextField price = new TextField("price");

 private ComboBox<ProductStatus> status = new ComboBox<>("status");


    public ProductForm(MainViewProductGrind mainView) {
        status.setItems(ProductStatus.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        setSizeFull();
        add(name, code, quantity, price, status,buttons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

        setSizeFull();
    }


    public void setProduct(MainViewProductGrind.Product product) {
        binder.setBean(product);
        setSizeFull();
        if (product == null) {
            setVisible(false);
        } else {
            setSizeFull();
            setVisible(true);
            name.focus();
        }
    }


    private void save() {
        MainViewProductGrind.Product product = binder.getBean();
        service.save(product);
        mainView.refresh();
       setProduct(null);
    }

    private void delete() {
        MainViewProductGrind.Product product = binder.getBean();
        service.delete(product);
        mainView.refresh();
        setProduct(null);
    }



}


