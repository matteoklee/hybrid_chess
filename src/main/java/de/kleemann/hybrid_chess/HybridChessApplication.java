package de.kleemann.hybrid_chess;

import de.kleemann.hybrid_chess.socket.WebSocketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication(scanBasePackages = {"de.kleemann.hybrid_chess"})
public class HybridChessApplication implements WebMvcConfigurer {

    private static WebSocketController webSocketController;

    public HybridChessApplication(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HybridChessApplication.class, args);
        System.out.println("[HybridChess] Backend successfully started.");
        Thread.sleep(10000);
        webSocketController.greetingChess("HYBRID_CHESS");
    }

    @RequestMapping("/")
    public String greeting() {
        return "Backend successfully started.";
    }

}