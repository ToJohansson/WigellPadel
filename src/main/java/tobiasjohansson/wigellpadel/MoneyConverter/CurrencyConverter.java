package tobiasjohansson.wigellpadel.MoneyConverter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CurrencyConverter {

    public double convertSEKToEUR(double sekAmount) {
        String base = "SEK";
        String targetCurrency = "EUR";

        String apiKey = "ecdca9b996eb01a3ef67b4e2";

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + base + "/" + targetCurrency + "/" + sekAmount);

            // skicka GET request
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // sök efter conversion_result inuti JSON response
                int index = jsonResponse.indexOf("\"conversion_result\":");

                if (index != -1) {

                    // Ta ut den del av texten
                    String valueSubstring = jsonResponse.substring(index + 20);

                    // Ta bort eventuella efterföljande icke-numeriska tecken
                    valueSubstring = valueSubstring.replaceAll("[^0-9.]", "");

                    // parse värdet i valueSubString till double
                    try {
                        double conversionResult = Double.parseDouble(valueSubstring);
                        return conversionResult;
                    } catch (NumberFormatException e) {
                        System.err.println("Failed to parse conversion_result as a double");
                    }
                } else {
                    System.err.println("JSON response does not contain 'conversion_result'");
                }
            } else {
                System.err.println("API request failed with status code " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1.0; // Return -1.0 in case of an error
    }
}
