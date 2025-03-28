package com.example.medichain.medichain.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, String token) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            // Build an elegant HTML email with styled token display.
            String htmlMsg = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "  <meta charset='UTF-8'>" +
                    "  <title>" + subject + "</title>" +
                    "  <style>" +
                    "    body { background: #f4f4f4; margin: 0; padding: 0; font-family: 'Arial', sans-serif; }" +
                    "    .container { max-width: 600px; margin: 30px auto; background: #ffffff; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); overflow: hidden; padding: 20px; }" +
                    "    .header { background: #673ab7; padding: 20px; text-align: center; color: #ffffff; font-size: 22px; font-weight: bold; }" +
                    "    .content { padding: 20px; color: #333333; font-size: 16px; }" +
                    "    .token-container { background: #e8eaf6; border-left: 5px solid #3f51b5; padding: 10px 15px; margin: 20px 0; text-align: center; border-radius: 5px; }" +
                    "    .token-header { font-size: 14px; color: #3f51b5; font-weight: bold; text-transform: uppercase; }" +
                    "    .token { font-size: 22px; font-weight: bold; color: #000000; letter-spacing: 1px; padding: 10px; }" +
                    "    .button { display: inline-block; padding: 12px 20px; margin-top: 20px; background: #3f51b5; color: #ffffff !important; text-decoration: none; border-radius: 5px; font-weight: bold; }" +
                    "    .footer { background: #f5f5f5; padding: 15px; text-align: center; font-size: 12px; color: #777777; margin-top: 20px; }" +
                    "  </style>" +
                    "</head>" +
                    "<body>" +
                    "  <div class='container'>" +
                    "    <div class='header'>Password Reset Request</div>" +
                    "    <div class='content'>" +
                    "      <p>Hello,</p>" +
                    "      <p>We received a request to reset your password. Use the token below to proceed.</p>" +
                    "      <div class='token-container'>" +
                    "        <div class='token-header'>Your Reset Token</div>" +
                    "        <div class='token'>" + token + "</div>" +
                    "      </div>" +
                    "      <p>If you did not request this password reset, please ignore this email.</p>" +
                    "    </div>" +
                    "    <div class='footer'>" +
                    "      &copy; 2025 MediChain. All rights reserved." +
                    "    </div>" +
                    "  </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlMsg, true); // true indicates HTML content
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setFrom("arpanbhattacharya5356@gmail.com"); // Ensure this is a valid sender address

            javaMailSender.send(mimeMessage);
            System.out.println("HTML email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.out.println("Error while sending email: " + e.getMessage());
        }
    }
}
