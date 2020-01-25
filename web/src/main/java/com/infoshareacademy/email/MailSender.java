package com.infoshareacademy.email;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MailSender {
    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    private void createMail(Email from, String subject, Email to, Content content) throws IOException {
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("sendgrid.env"));
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        logger.info("Response status: {}", response.getStatusCode());
        logger.info("Response body:  {}", response.getBody());
        logger.info("Response header: {}", response.getHeaders());

    }

    public void approveReservation(String userEmail) throws IOException {

        Email from = new Email("LibraryFPO@fourplusone.com");
        String subject = "Rezerwacja.";
        Email to = new Email(userEmail);
        Content content = new Content("text/plain", "Książka została zarezerwowana!\n jeżeli nie zostanie odebrana w ciągu 48 godzin rezerwacja będzie anulowana");
        createMail(from, subject, to, content);


            }
    public void reservationRejected(String userEmail) throws IOException {

        Email from = new Email("LibraryFPO@fourplusone.com");
        String subject = "Rezerwacja.";
        Email to = new Email(userEmail);
        Content content = new Content("text/plain", "Rezerwacja książki odwołana z przyczyn technicznych . Przepraszamy ");
        createMail(from, subject, to, content);


            }
        }

