package com.example.medichain.medichain.controller;

import com.example.medichain.medichain.model.ResetEmailRequest;
import com.example.medichain.medichain.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("https://medichain.pages.dev")// Allow your frontend origin
public class PasswordResetController {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendResetEmail")
    public ResponseEntity<String> sendResetEmail(@RequestBody ResetEmailRequest request) {
        System.out.println("Received reset email request for: " + request.getEmail());
        System.out.println("Token from contract: " + request.getToken());

        // Construct the email content
        String subject = "Password Reset Request";
        String body = request.getToken();

        // Send the email using the EmailSenderService
        emailSenderService.sendEmail(request.getEmail(), subject, body);

        return ResponseEntity.ok("Reset email sent successfully");
    }
}
