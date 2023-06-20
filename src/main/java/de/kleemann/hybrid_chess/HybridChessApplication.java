package de.kleemann.hybrid_chess;

import de.kleemann.hybrid_chess.hardware.HardwareController;
import de.kleemann.hybrid_chess.hardware.HardwareControllerListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication(scanBasePackages = {"de.kleemann.hybrid_chess"})
public class HybridChessApplication implements WebMvcConfigurer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HybridChessApplication.class, args);

        HardwareController hardwareController = HardwareController.getInstance();
        hardwareController.addListener(new HardwareControllerListener());

        System.out.println("[HybridChess] Backend successfully started.");

        /*for(int i = 0; i < 200; i++) {
            WebSocketController.greetingChess("HYBRID_CHESS");
            Thread.sleep(3000);
        }*/

    }

    @RequestMapping("/")
    public String greeting() {
        return "Backend successfully started.";
    }

}