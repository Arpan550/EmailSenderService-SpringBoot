package com.example.medichain.medichain.service;

import jakarta.mail.internet.InternetAddress;
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
                    "<html lang='en'>" +
                    "<head>" +
                    "  <meta charset='UTF-8'>" +
                    "  <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                    "  <title>" + subject + "</title>" +
                    "  <style>" +
                    "    body, html { margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; background-color: #f2f2f2; color: #000; line-height: 1.6; }" +
                    "    .email-container { max-width: 620px; margin: auto; background: rgba(255,255,255,0.85); backdrop-filter: blur(15px); border-radius: 20px; box-shadow: 0 8px 32px rgba(0,0,0,0.1); border: 1px solid rgba(255,255,255,0.2); overflow: hidden; }" +
                    "    .email-header { padding: 35px 40px 20px; text-align: center; position: relative; }" +
                    "    .header-decoration { height: 5px; background: linear-gradient(90deg, #222, #555); }" +
                    "    .logo { font-size: 26px; font-weight: 700; margin: 10px 0; position: relative; }" +
                    "    .logo::after { content: ''; width: 40px; height: 2px; background: #000; position: absolute; bottom: -8px; left: 50%; transform: translateX(-50%); }" +
                    "    .email-content { padding: 15px 50px 40px; }" +
                    "    h1 { font-size: 28px; font-weight: 600; text-align: center; color: #000; }" +
                    "    p { font-size: 16px; color: #333; }" +
                    "    .token-container { background: rgba(240,240,240,0.6); padding: 25px; margin: 35px 0; border-radius: 16px; border: 1px solid rgba(0,0,0,0.08); text-align: center; }" +
                    "    .token-label { font-size: 14px; color: #555; margin-bottom: 10px; text-transform: uppercase; font-weight: 600; }" +
                    "    .token { font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 18px; color: #000; background: rgba(255,255,255,0.65); padding: 15px; border-radius: 12px; border: 1px solid rgba(0,0,0,0.05); word-break: break-all; }" +
                    "    .copy-hint { font-size: 12px; color: #777; margin-top: 10px; }" +
                    "    .action-button { display: inline-block; background: #000; color: #fff; padding: 14px 30px; border-radius: 12px; text-decoration: none; font-weight: 600; margin-top: 30px; }" +
                    "    .action-button:hover { background: #222; }" +
                    "    .security-notice { background: rgba(240,240,240,0.6); padding: 15px 20px; margin: 30px 0; font-size: 14px; color: #555; border-left: 4px solid #000; border-radius: 12px; }" +
                    "    .email-footer { text-align: center; padding: 30px 40px; font-size: 13px; color: #777; border-top: 1px solid rgba(0,0,0,0.05); background: rgba(245,245,245,0.7); }" +
                    "    .footer-links a { color: #777; margin: 0 10px; text-decoration: none; }" +
                    "    .footer-logo { font-weight: 600; font-size: 16px; margin-bottom: 10px; }" +
                    "    .social-links { margin: 20px 0; }" +
                    "    .social-icon { display: inline-block; width: 20px; height: 20px; margin: 0 8px; background-color: #ccc; border-radius: 50%; }" +
                    "    @media screen and (max-width: 600px) { .email-content { padding: 25px; } h1 { font-size: 22px; } .token { font-size: 15px; } }" +
                    "  </style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='email-container'>" +
                    "  <div class='email-header'>" +
                    "    <div class='header-decoration'></div>" +
                    "    <div class='logo'>MediChain</div>" +
                    "  </div>" +
                    "  <div class='email-content'>" +
                    "    <h1>Reset Your Password</h1>" +
                    "    <p>We received a request to reset your password. For your security, we've generated a unique token that you can use to complete the password reset process.</p>" +
                    "    <div class='token-container'>" +
                    "      <div class='token-label'>Your Secure Token</div>" +
                    "      <div class='token'>" + token + "</div>" +
                    "      <div class='copy-hint'>Please copy this token manually into the reset page.</div>" +
                    "    </div>" +
                    "    <div class='security-notice'>" +
                    "      <span>If you did not request a password reset, please ignore this email or contact our support team immediately.</span>" +
                    "    </div>" +
                    "    <p>To complete the password reset process, copy the token above and enter it on the password reset page when prompted. You will then be able to set a new password for your account.</p>" +
                    "  </div>" +
                    "  <div class='email-footer'>" +
                    "  <div class='footer-logo' style='color: #000;'>MediChain</div>" +
                    "    <div class='footer-links'>" +
                    "      <a href='#'>Privacy Policy</a> | " +
                    "      <a href='#'>Terms of Service</a> | " +
                    "      <a href='#'>Help Center</a>" +
                    "    </div>" +
                    "    <div class='social-links'>" +
                    "    </div>" +
                    "    <div class='copyright'>© 2025 MediChain. All rights reserved.</div>" +
                    "  </div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";




            helper.setText(htmlMsg, true); // true indicates HTML content
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setFrom(new InternetAddress("noreply.medichain@gmail.com", "MediChain"));

            javaMailSender.send(mimeMessage);
            System.out.println("HTML email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.out.println("Error while sending email: " + e.getMessage());
        }
    }

    public void sendWelcomeEmail(String userEmail, String userName, String userRole, String userId) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String subject = "Welcome to MediChain 🎉";

            String htmlMsg = "<!DOCTYPE html>" +
                    "<html lang='en'>" +
                    "<head>" +
                    "  <meta charset='UTF-8'>" +
                    "  <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                    "  <title>" + subject + "</title>" +
                    "  <style>" +
                    "    body, html { margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; background-color: #f2f2f2; color: #000; line-height: 1.6; }" +
                    "    .email-container { max-width: 620px; margin: auto; background: rgba(255,255,255,0.85); backdrop-filter: blur(15px); border-radius: 20px; box-shadow: 0 8px 32px rgba(0,0,0,0.1); border: 1px solid rgba(255,255,255,0.2); overflow: hidden; }" +
                    "    .email-header { padding: 35px 40px 20px; text-align: center; position: relative; }" +
                    "    .header-decoration { height: 5px; background: linear-gradient(90deg, #222, #555); }" +
                    "    .logo { font-size: 26px; font-weight: 700; margin: 10px 0; position: relative; }" +
                    "    .logo::after { content: ''; width: 40px; height: 2px; background: #000; position: absolute; bottom: -8px; left: 50%; transform: translateX(-50%); }" +
                    "    .email-content { padding: 15px 50px 40px; }" +
                    "    h1 { font-size: 28px; font-weight: 600; text-align: center; color: #000; }" +
                    "    p { font-size: 16px; color: #333; }" +
                    "    .welcome-container { background: rgba(240,240,240,0.6); padding: 25px; margin: 35px 0; border-radius: 16px; border: 1px solid rgba(0,0,0,0.08); text-align: center; }" +
                    "    .welcome-message { font-size: 18px; color: #000; font-weight: 500; margin-bottom: 15px; }" +
                    "    .account-info { font-family: SFMono-Regular, Menlo, Monaco, Consolas, 'Courier New', monospace; font-size: 16px; color: #333; background: rgba(255,255,255,0.65); padding: 15px; border-radius: 12px; border: 1px solid rgba(0,0,0,0.05); text-align: left; margin: 15px 0; }" +
                    "    .info-label { font-weight: 600; display: inline-block; width: 100px; }" +
                    "    .features-list { margin: 25px 0; text-align: left; }" +
                    "    .feature-item { display: flex; align-items: center; margin-bottom: 15px; }" +
                    "    .feature-icon { width: 30px; height: 30px; background-color: rgba(0,0,0,0.05); border-radius: 50%; margin-right: 15px; display: flex; align-items: center; justify-content: center; }" +
                    "    .feature-text { flex: 1; }" +
                    "    .security-notice { background: rgba(240,240,240,0.6); padding: 15px 20px; margin: 30px 0; font-size: 14px; color: #555; border-left: 4px solid #000; border-radius: 12px; }" +
                    "    .email-footer { text-align: center; padding: 30px 40px; font-size: 13px; color: #777; border-top: 1px solid rgba(0,0,0,0.05); background: rgba(245,245,245,0.7); }" +
                    "    .footer-links a { color: #777; margin: 0 10px; text-decoration: none; }" +
                    "    .footer-logo { font-weight: 600; font-size: 16px; margin-bottom: 10px; }" +
                    "    .social-links { margin: 20px 0; }" +
                    "    .social-icon { display: inline-block; width: 20px; height: 20px; margin: 0 8px; background-color: #ccc; border-radius: 50%; }" +
                    "    @media screen and (max-width: 600px) { .email-content { padding: 25px; } h1 { font-size: 22px; } }" +
                    "  </style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='email-container'>" +
                    "  <div class='email-header'>" +
                    "    <div class='header-decoration'></div>" +
                    "    <div class='logo'>MediChain</div>" +
                    "  </div>" +
                    "  <div class='email-content'>" +
                    "    <h1>Welcome to MediChain!</h1>" +
                    "    <p>Thank you for registering with MediChain. We're excited to have you on board! Your account has been successfully created and is now ready to use.</p>" +
                    "    <div class='welcome-container'>" +
                    "      <div class='welcome-message'>Hello, " + userName + "!</div>" +
                    "      <div class='account-info'>" +
                    "        <div><span class='info-label'>Role:</span> " + userRole + "</div>" +
                    "        <div><span class='info-label'>Email:</span> " + userEmail + "</div>" +
                    "        <div><span class='info-label'>User ID:</span> " + userId + "</div>" +
                    "      </div>" +
                    "    </div>" +
                    "    <p>With your MediChain account, you can now access our blockchain-based medicine tracking platform, designed to ensure transparency and security throughout the pharmaceutical supply chain.</p>" +
                    "    <div class='features-list'>" +
                    "      <div class='feature-item'>" +
                    "        <div class='feature-icon'>✓</div>" +
                    "        <div class='feature-text'>Track medicines from manufacturer to pharmacy</div>" +
                    "      </div>" +
                    "      <div class='feature-item'>" +
                    "        <div class='feature-icon'>✓</div>" +
                    "        <div class='feature-text'>Verify authenticity and prevent counterfeit products</div>" +
                    "      </div>" +
                    "      <div class='feature-item'>" +
                    "        <div class='feature-icon'>✓</div>" +
                    "        <div class='feature-text'>Manage inventory and transaction history</div>" +
                    "      </div>" +
                    "    </div>" +
                    "    <div class='security-notice'>" +
                    "      <span>For your security, please keep your login credentials confidential and ensure you log out when accessing MediChain from public devices.</span>" +
                    "    </div>" +
                    "  </div>" +
                    "  <div class='email-footer'>" +
                    "    <div class='footer-logo' style='color: #000;'>MediChain</div>" +
                    "    <div class='footer-links'>" +
                    "      <a href='#'>Privacy Policy</a> | " +
                    "      <a href='#'>Terms of Service</a> | " +
                    "      <a href='#'>Help Center</a>" +
                    "    </div>" +
                    "    <div class='social-links'>" +
                    "    </div>" +
                    "    <div class='copyright'>© 2025 MediChain. All rights reserved.</div>" +
                    "  </div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlMsg, true);
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setFrom(new InternetAddress("noreply.medichain@gmail.com", "MediChain"));

            javaMailSender.send(mimeMessage);
            System.out.println("🎉 Welcome email sent to " + userEmail);

        } catch (Exception e) {
            System.err.println("❌ Error sending welcome email: " + e.getMessage());
        }
    }


}
