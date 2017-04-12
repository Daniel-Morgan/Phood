package com.doughepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by pjdoughe on 4/12/17.
 */
@Controller
public class HelpController {


    @GetMapping("/help")
    public String showHelp(Model model) {
        return "help";
    }

}
