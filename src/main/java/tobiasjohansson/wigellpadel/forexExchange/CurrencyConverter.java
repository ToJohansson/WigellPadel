package tobiasjohansson.wigellpadel.forexExchange;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CurrencyConverter {

    private static final String BASE_URL = "https://www.forexapi.com/api/v1/";
    private static final String API_KEY = "YOUR_API_KEY";

    private interface ForexAPI {
        @GET("rates")
        Call<ForexApiResponse> getExchangeRate(@Query("from") String fromCurrency,
                                               @Query("to") String toCurrency,
                                               @Query("api_key") String apiKey);
    }

    public static void convertSEKtoEUR(double amountInSek) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForexAPI api = retrofit.create(ForexAPI.class);
        Call<ForexApiResponse> call = api.getExchangeRate("SEK", "EUR", API_KEY);
        call.enqueue(new Callback<ForexApiResponse>() {
            @Override
            public void onResponse(Call<ForexApiResponse> call, Response<ForexApiResponse> response) {
                if (response.isSuccessful()) {
                    ForexApiResponse forexApiResponse = response.body();
                    double exchangeRate = forexApiResponse.getRate(); // Assuming the API response provides the exchange rate
                    double convertedAmount = amountInSek * exchangeRate;
                    System.out.println(amountInSek + " SEK = " + convertedAmount + " EUR");
                } else {
                    // Handle unsuccessful API response
                }
            }

            @Override
            public void onFailure(Call<ForexApiResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}

