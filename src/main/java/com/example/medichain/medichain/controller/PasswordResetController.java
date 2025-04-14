package com.example.medichain.medichain.controller;

import com.example.medichain.medichain.model.ResetEmailRequest;
import com.example.medichain.medichain.model.WelcomeEmailRequest;
import com.example.medichain.medichain.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "https://medichain.pages.dev",
        "http://localhost:3000"
})

public class PasswordResetController {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendResetEmail")
    public ResponseEntity<String> sendResetEmail(@RequestBody ResetEmailRequest request) {
        System.out.println("Received reset email request for: " + request.getEmail());
        System.out.println("Token from contract: " + request.getToken());

        String subject = "Password Reset Request";
        String body = request.getToken();

        emailSenderService.sendEmail(request.getEmail(), subject, body);

        return ResponseEntity.ok("Reset email sent successfully");
    }

    @PostMapping("/sendWelcomeEmail")
    public ResponseEntity<String> sendWelcomeEmail(@RequestBody WelcomeEmailRequest request) {
        emailSenderService.sendWelcomeEmail(
                request.getEmail(),
                request.getName(),
                request.getRole(),
                request.getUserId()
        );
        return ResponseEntity.ok("Welcome email sent successfully");
    }

}
