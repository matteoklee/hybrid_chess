package de.kleemann.hybrid_chess.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /*@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(String message) throws Exception {
        Thread.sleep(1000);
        System.err.println("Hello, " + message + "!");
        return new Greeting("Hello, " + message + "!");
    }*/

    /*
    @Scheduled(fixedDelay = 5000)
    public void scheduleGreetingChess() throws Exception {
        greetingChess("BACKEND DEBUG SOCKET"); // Methode ohne Parameter aufrufen
    }*/

    //@Scheduled(fixedDelay = 3000)
    public void sendMessageToFrontend() {
        String message = "BACKEND DEBUG TEST";
        messagingTemplate.convertAndSend("/topic/chess", message);
    }


    /*@MessageMapping("/chess")
    @SendTo("/topic/chess")
    public Greeting greetingChess(String message) throws Exception {
        Thread.sleep(1000);
        System.err.println("Hello Chess, " + message + "!");
        return new Greeting("Hello Chess, " + message + "!");
    }*/

    /*@MessageMapping("/chessInfo")
    public Greeting getChessInfo(String message) throws Exception {
        Thread.sleep(1000);
        System.err.println("FRONTEND GOT INFO FROM BACKEND:, " + message + "!");
        return new Greeting("RONTEND GOT INFO FROM BACKEND:, " + message + "!");
    }*/

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