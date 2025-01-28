package com.example;

import com.sendgrid.*;

import java.io.IOException;

public class SendGridMailApp {

    private static final String FROM = "shivamrahangdale92@gmail.com";  // Sender email
    private static final String TO = "shivamrahangdale92@gmail.com";    // Recipient email
    private static final String SUBJECT = "Test Email from SendGrid";
    private static final String BODY = "This is a test email sent using SendGrid";

    public static void main(String[] args) {
        // SendGrid API Key
        String sendGridApiKey = "SENDGRID_API_KEY";  // Replace with your SendGrid API Key

        if (sendGridApiKey == null || sendGridApiKey.isEmpty()) {
            System.out.println("SendGrid API Key not properly set.");
            return;
        }

        try {
            sendEmail(sendGridApiKey);
            System.out.println("Email sent successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void sendEmail(String sendGridApiKey) throws IOException {
        Email from = new Email(FROM);
        Email to = new Email(TO);
        Content content = new Content("text/plain", BODY);
        Mail mail = new Mail(from, SUBJECT, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
            System.out.println("Response Headers: " + response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
