package sk.backend.skiSchool.services;

import org.springframework.stereotype.Component;

import sk.backend.skiSchool.services.messages.EventMessage;

import org.springframework.context.ApplicationListener;


@Component
public class NotificationEngine implements ApplicationListener<EventMessage> {

    @Override
    public void onApplicationEvent(EventMessage event) {

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
        System.out.println("Sending email to " + event.getEmail() + " with message: " + event.getMessage());
    }
}
