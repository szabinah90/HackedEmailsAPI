package org.flow.exercise.hackedemails.springboot.controller;

import org.flow.exercise.hackedemails.springboot.dao.HackedEmailsDAO;
import org.flow.exercise.hackedemails.springboot.model.EmailObject;
import org.flow.exercise.hackedemails.springboot.model.PwnResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        PwnResponse object = null;
        try {
            object = hackedEmailsDAO.checkEmail(emailObject.getAddress());
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                return "notFound";
            } else {
                e.printStackTrace();
            }
        }
        model.addAttribute("pwnresponse", object);
        return "result";
    }
}
