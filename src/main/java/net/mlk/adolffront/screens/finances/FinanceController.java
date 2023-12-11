package net.mlk.adolffront.screens.finances;

import net.mlk.adolffront.http.AdolfServer;
import net.mlk.adolffront.http.MultiPartRequest;
import net.mlk.jmson.Json;
import net.mlk.jmson.utils.JsonConverter;

import java.io.IOException;
import java.time.LocalDate;

public class FinanceController {
    private final FinanceScreen screen;

    public FinanceController(FinanceScreen screen) {
        this.screen = screen;
    }

    public Finance getFinance() {
        try {
            return AdolfServer.getFinance();
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
            return null;
        }
    }

    public Finance createFinance(double salary, LocalDate salaryDate, double remain) {
        Finance finance = new Finance(salary, remain, salaryDate);
        return finance;
    }

}
