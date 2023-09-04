package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.MoneyConverter.CurrencyConverter;

@Service
public class CurrencyConversionService {

    @Autowired
    private CurrencyConverter currencyConverter;

    public double convertSEKToEUR(double sekAmount) {
        return currencyConverter.convertSEKToEUR(sekAmount);
    }
}

