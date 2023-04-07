package com.epam.security.controller;

import com.epam.security.auth.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final LoginAttemptService loginAttemptService;

    @PostMapping("/info")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getInfo() {
        return "MVC application";
    }


    @GetMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminInfo() {
        return "Admin info";
    }

    @GetMapping("/about")
    @ResponseBody
    public String getAbout() {
        return "about something";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping(value = "/logout-success")
    public String getLogoutPage(Model model) {
        return "logout";
    }

    @GetMapping(value = "/blocked-users")
    @ResponseBody
    public List<String> getBlockedUsers(Model model) {
        return loginAttemptService.getBlockedUsers();
    }


}
