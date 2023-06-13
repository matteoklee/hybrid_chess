package de.kleemann.hybrid_chess.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/hello") // Der Endpunkt für die eingehenden Nachrichten vom Frontend
    @SendTo("/topic/greetings") // Der Endpunkt, an den die Nachrichten an das Frontend gesendet werden
    public Greeting greeting(String message) throws Exception {
        Thread.sleep(1000); // Führt eine asynchrone Verarbeitung durch (kann entfernt werden)
        System.err.println("Hello, " + message + "!");
        return new Greeting("Hello, " + message + "!"); //message.getName()
    }

    @MessageMapping("/chess") // Der Endpunkt für die eingehenden Nachrichten vom Frontend
    @SendTo("/topic/chess") // Der Endpunkt, an den die Nachrichten an das Frontend gesendet werden
    public Greeting greetingChess(String message) throws Exception {
        Thread.sleep(1000); // Führt eine asynchrone Verarbeitung durch (kann entfernt werden)
        System.err.println("Hello Chess, " + message + "!");
        return new Greeting("Hello Chess, " + message + "!"); //message.getName()
    }
}

class HelloMessage {
    private String name;

    public HelloMessage() {}

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Greeting {
    private String content;

    public Greeting() {}

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}