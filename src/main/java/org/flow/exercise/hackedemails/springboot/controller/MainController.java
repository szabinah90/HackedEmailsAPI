package org.flow.exercise.hackedemails.springboot.controller;

import org.flow.exercise.hackedemails.springboot.dao.HackedEmailsDAO;
import org.flow.exercise.hackedemails.springboot.model.EmailObject;
import org.flow.exercise.hackedemails.springboot.model.PwnResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/leaked-emails")
public class MainController {

    private HackedEmailsDAO hackedEmailsDAO;

    public MainController(HackedEmailsDAO hackedEmailsDAO) {
        this.hackedEmailsDAO = hackedEmailsDAO;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("email", new EmailObject());
        return "home";
    }

    @PostMapping
    public String checkEmail(@ModelAttribute EmailObject emailObject, Model model) {
        PwnResponse object = hackedEmailsDAO.checkEmail(emailObject.getAddress());
        model.addAttribute("pwnresponse", object);
        return "result";
    }
}
