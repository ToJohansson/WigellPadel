package tobiasjohansson.wigellpadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {
    /**
     *
     *
     * TODO: alla courts ska få egna slots vid programstart. så inte alla slots ändras när man vill bara ändra på en
     * TODO:testa göra classer som chatgpt ger some exempel
     * ATT GÖRA:
     * färdigställ bokningen i customerservice det ska ej gå att dubbelboka en plan
     * Något som kollar att id som kommer in till metoden existerar
     */
    public static void main(String[] args) {
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
