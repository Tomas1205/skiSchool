package sk.backend.skiSchool.services.messages;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class EventMessage extends ApplicationEvent {

    private String message;
    private String email;

    public EventMessage(Object source, String message, String email) {
        super(source);
        this.message = message;
        this.email = email;
    }

    
}
