package de.kleemann.hybrid_chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication(scanBasePackages = {"de.kleemann.hybrid_chess"})
public class HybridChessApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(HybridChessApplication.class, args);
        System.err.println("[HybridChess] Backend successfully started.");
    }

    @RequestMapping("/")
    public String greeting() {
        return "Backend successfully started.";
    }

}