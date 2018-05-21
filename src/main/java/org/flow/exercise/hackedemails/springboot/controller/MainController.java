package org.flow.exercise.hackedemails.springboot.controller;

import org.flow.exercise.hackedemails.springboot.dao.HackedEmailsDAO;
import org.flow.exercise.hackedemails.springboot.model.EmailObject;
import org.flow.exercise.hackedemails.springboot.model.HackedApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/hacked-emails")
public class MainController {

    private HackedEmailsDAO hackedEmailsDAO;

    public MainController(HackedEmailsDAO hackedEmailsDAO) {
        this.hackedEmailsDAO = hackedEmailsDAO;
    }

    @GetMapping(path = "/")
        public String home(Model model) {
        model.addAttribute("email", new EmailObject());
            return "home";
        }

    @PostMapping(path = "/")
    public String checkEmail(@ModelAttribute EmailObject emailObject, Model model) {
        HackedApiResponse object = hackedEmailsDAO.checkEmail(emailObject.getAddress());
        model.addAttribute("hackerresponse", object);
        return "result";
    }

    @GetMapping(path = "/{address}")
    public String getResults(@ModelAttribute EmailObject emailObject, Model model) {
        HackedApiResponse object = hackedEmailsDAO.getStoredResultsByEmail(emailObject.getAddress());
        model.addAttribute("hackerresponse", object);
        return "result";
    }

}
