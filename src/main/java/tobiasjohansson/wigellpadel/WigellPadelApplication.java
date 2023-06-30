package tobiasjohansson.wigellpadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {
    /**
     *JUST NU ATT GÖRA! I POSTMAN skicka in ett objekt av booking id som ska deleteas som en request body
     *
     * TODO:
     * loggning LOG4J!
     * forex exchange för pengar
     */
    public static void main(String[] args) {
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
