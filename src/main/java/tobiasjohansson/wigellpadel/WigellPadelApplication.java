package tobiasjohansson.wigellpadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {
    /**
     * TODO: ge admin åtkomst till sina endpoints och user till sina
     * forex exchange för pengar
     * SECURITY BASIC - verkar funka
     *
     * felmeddelande när man söker sin bokningslista och användare inte existerar
     * felsök med postman (gör det sist)
     */
    public static void main(String[] args) {
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
