package com.redhat.rhsso.rest;

import com.redhat.rhsso.dao.CustomerDAO;
import com.redhat.rhsso.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class WebController {

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/admin")
    public String admin(Principal principal, Model model) {
        if (principal == null) {
            return "not-authenticated";
        }
        model.addAttribute("username", principal.getName());

        return "admin";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        addCustomers();

        Iterable<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);

        if (principal == null) {
            return "not-authenticated";
        }
        model.addAttribute("username", principal.getName());

        return "customers";
    }

    // add customers for demonstration
    public void addCustomers() {
        Customer c1 = new Customer();
        c1.setAddress("1111 foo blvd");
        c1.setName("Foo Industries");
        c1.setServiceRendered("Important services");
        customerDAO.save(c1);

        Customer c2 = new Customer();
        c2.setAddress("2222 bar street");
        c2.setName("Bar LLP");
        c2.setServiceRendered("Important services");
        customerDAO.save(c2);

        Customer c3 = new Customer();
        c3.setAddress("33 main street");
        c3.setName("Big LLC");
        c3.setServiceRendered("Important services");
        customerDAO.save(c3);
    }
}
