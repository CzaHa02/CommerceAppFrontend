package com.kodilla.commerceApp.OverallView;

import com.kodilla.commerceApp.Security.SecurityService;
import com.kodilla.commerceApp.customer.MainViewCustomerGrind;
import com.kodilla.commerceApp.order.MainViewOrderGrind;
import com.kodilla.commerceApp.product.MainViewProductGrind;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@PermitAll
@Route("")
@PageTitle("Commerce application")
@Push
public class HomepageMainView extends VerticalLayout implements AppShellConfigurator {

    private SecurityService securityService;
    private final Button logOut = new Button("Log out", e -> {
        securityService.logout();
        UI.getCurrent().navigate(LoginView.class);
    });

    public HomepageMainView(@Autowired SecurityService securityService) {
        this.securityService = securityService;

        Tabs tabs = new Tabs(
                createTab("Go to products", MainViewProductGrind.class),
                createTab("Go to orders", MainViewOrderGrind.class),
                createTab("Go to customers", MainViewCustomerGrind.class),
                createTab("Login menu", LoginView.class)
        );

        HorizontalLayout header = new HorizontalLayout();
        H1 title = new H1("HOMEPAGE");
        header.add(title);

        tabs.setAutoselect(false);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setWidthFull();

        add(header, tabs, logOut);
    }

    private static Tab createTab(String text, Class<?> navigationTarget) {
        return new Tab(new RouterLink(text, (Class<? extends Component>) navigationTarget));
    }
}