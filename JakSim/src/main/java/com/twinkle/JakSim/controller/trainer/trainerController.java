package com.twinkle.JakSim.controller.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class trainerController {

    @GetMapping("/trainer/register")
    public String trainerRegisterForm(){

        return "content/trainer/trainerRegister";
    }
}
