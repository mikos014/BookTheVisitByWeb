package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;
import pl.edu.wat.bookthevisit.entities.VisitEntity;
import pl.edu.wat.bookthevisit.repositories.DoctorsRepository;
import pl.edu.wat.bookthevisit.repositories.VisitsRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService
{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final VisitsRepository visitsRepository;
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine, VisitsRepository visitsRepository, DoctorsRepository doctorsRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.visitsRepository = visitsRepository;
        this.doctorsRepository = doctorsRepository;
    }

    @Override
    public void sendEmail(Integer id)
    {
        VisitEntity visitEntity = visitsRepository.findAllByIdVisit(id);
        DoctorEntity doctorEntity = doctorsRepository.findAllByIdDoctor(visitEntity.getDoctor().getIdDoctor());
        String mailTitle = "Data: " + visitEntity.getDate() + " o godzinie " + visitEntity.getTime() + " doktor "
                        + doctorEntity.getName() + " " + doctorEntity.getSurname();

        Context context = new Context();
        context.setVariable("header", "Book The Visit");
        context.setVariable("title", mailTitle);
        context.setVariable("description", "Proszę nie odpowiadać na tego maila. Został wygenerowany automatyczny.");
        String body = templateEngine.process("template", context);

        String to = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        String title = "Przypomnienie o wizycie";

        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("no-reply@bookthevisit.pl");
            helper.setFrom("no-reply@bookthevisit.pl");
            helper.setSubject(title);
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

}
