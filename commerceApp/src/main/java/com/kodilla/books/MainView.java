package com.kodilla.books;

import com.kodilla.books.customer.MainViewCustomerGrind;
import com.kodilla.books.order.MainViewOrderGrind;
import com.kodilla.books.product.MainViewProductGrind;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
@PageTitle("Commerce application")

@Push
public class MainView extends VerticalLayout implements AppShellConfigurator {

    public MainView() {
        Tabs tabs = new Tabs(

                createTab("Go to products", MainViewProductGrind.class),
                createTab("Go to orders", MainViewOrderGrind.class),
                createTab("Go to customers", MainViewCustomerGrind.class)

        );

        HorizontalLayout header = new HorizontalLayout();
        H1 title = new H1( "HOMEPAGE" );
        header.add(title);

        tabs.setAutoselect(false);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setWidthFull();

        add(header);
        add(tabs);
        tabs.setAutoselect(false);
    }

    private static Tab createTab(String text, Class<?> navigationTarget) {
        return new Tab(new RouterLink(text, (Class<? extends Component>) navigationTarget));
    }


}

