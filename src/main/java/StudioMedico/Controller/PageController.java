/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Chahir Chalouati
 */
@Controller
public class PageController {

    /**
     * getting login page
     *
     * @param model
     * @return login Page
     */
    @GetMapping("/")
    public String loginPage(Model model) {
        return "login";
    }

    /**
     * getting login page
     *
     * @param model
     * @return login Page
     */
    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    /**
     * getting user page
     *
     * @param model
     * @return user Page
     */
    @GetMapping("/user")
    public String userPage(Model model) {
        return "user";
    }

    /**
     * getting doctor page
     *
     * @param model
     * @return doctor page
     */
    @GetMapping("/doctor")
    public String doctorPage(Model model) {

        return "doctor";
    }

}
