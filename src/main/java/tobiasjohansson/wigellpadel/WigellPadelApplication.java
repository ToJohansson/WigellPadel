package tobiasjohansson.wigellpadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {
    /**
     * TODO: felhantering vid bokning
     * ATT GÖRA:
     * färdigställ bokningen i customerservice det ska ej gå att dubbelboka en plan
     * Något som kollar att id som kommer in till metoden existerar
     */
    public static void main(String[] args) {
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
