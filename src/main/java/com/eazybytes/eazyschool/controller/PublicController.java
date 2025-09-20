package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("public")
public class PublicController {

    @RequestMapping(value = "/register",method = GET)
    public String displayRegisterPage(Model model)  {
        model.addAttribute("person",new Person());
        return "register.html";
    }
}
