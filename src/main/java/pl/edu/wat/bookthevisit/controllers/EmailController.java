package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.bookthevisit.services.EmailService;

@RestController
public class EmailController
{
    private final EmailService emailSender;


    @Autowired
    public EmailController(EmailService emailSender)
    {
        this.emailSender = emailSender;
    }

    @PostMapping("/api/sendMail/{id}")
    public void send(@PathVariable Integer id) {

        emailSender.sendEmail(id);
    }

}
