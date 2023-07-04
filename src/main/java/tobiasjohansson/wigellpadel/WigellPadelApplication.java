package tobiasjohansson.wigellpadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {
    /**
     *JUST NU ATT GÖRA! I POSTMAN skicka in ett objekt av booking id som ska deleteas som en request body
     * går bara att göra delete på id 1?????
     *
     * TODO:
     * loggning LOG4J!
     * forex exchange för pengar
     * felmeddelande när man söker sin bookningslista och användare inte existerar
     */
    public static void main(String[] args) {
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
