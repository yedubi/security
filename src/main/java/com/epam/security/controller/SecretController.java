package com.epam.security.controller;

import com.epam.security.secret.Secret;
import com.epam.security.secret.SecretServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/secret")
@RequiredArgsConstructor
public class SecretController {

    private final SecretServices secretServices;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showForm(Model model) {
        model.addAttribute("secret", new Secret());
        return "form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String submitForm(@ModelAttribute("secret") Secret secret, Model model) {
        String link = generateUniqueLink();
        secretServices.save(new Secret(link, secret.getText(), false));
        model.addAttribute("link", link);
        return "result";
    }

    @GetMapping("/{link}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> viewSecret(@PathVariable String link) {
        Secret secret = secretServices.getSecret(link);
        if (secret == null) {
            return ResponseEntity.notFound().build();
        }
        secret.setViewed(true);
        secretServices.save(secret);
        String text = secret.getText();
        secretServices.delete(secret);
        return ResponseEntity.ok(text);
    }

    private String generateUniqueLink() {
        return UUID.randomUUID().toString(); // generate a random UUID
    }


}
