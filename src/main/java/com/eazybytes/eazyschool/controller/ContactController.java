package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@Slf4j
public class ContactController {

//    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
//    @RequestMapping(value = "/saveMsg", method = POST)
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
//        logger.info("Name :" + name);
//        logger.info("Mobile Number :" + mobileNum);
//        logger.info("Email :" + email);
//        logger.info("Subject :" + subject);
//        logger.info("Message :" + message);
//        return new ModelAndView("redirect:/contact");
//    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            return "contact.html";

        }
        contactService.saveMessageDetails(contact);
       // return new ModelAndView("redirect:/contact");
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactMsgs=contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView=new ModelAndView("messages");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg",method = GET)
    public String closeMsg(@RequestParam(name = "id") int contactId){
        contactService.updateMsgStatus(contactId);
        return "redirect:/displayMessages";
    }

}
