package tobiasjohansson.wigellpadel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {
    /**
     *
     * forex exchange för pengar : API KEY???
     * Få totalt pris för dina bokningar? eller den enskilde bokningens totala pris?
     *
     * felsök med postman (gör det sist)
     */
    public static void main(String[] args) {
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
