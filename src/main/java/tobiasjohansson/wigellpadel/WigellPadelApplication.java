package tobiasjohansson.wigellpadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {
    /**
     * TODO:
     * loggning LOG4J!
     * forex exchange för pengar
     *
     * felmeddelande när man söker sin bookningslista och användare inte existerar
     * felsök med postman (gör det sist)
     */
    public static void main(String[] args) {
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
