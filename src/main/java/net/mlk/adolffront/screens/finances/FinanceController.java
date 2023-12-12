package net.mlk.adolffront.screens.finances;

import net.mlk.adolffront.http.AdolfServer;

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
        try {
            AdolfServer.createFinance(finance);
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
            return null;
        }
        return finance;
    }

    public void makeTransaction(Transaction.Type type, String value, String description) {
        if (value == null) {
            this.screen.setErrorText("Сумма не может быть нулевой.");
            return;
        }
        double sum = Double.parseDouble(value);
        try {
            Finance finance = this.screen.getFinance();
            if (type == Transaction.Type.ADD) {
                finance.setRemain(finance.getRemain() + sum);
            } else {
                finance.setRemain(finance.getRemain() - sum);
            }
            Transaction transaction = new Transaction(finance, type, sum, description);
            AdolfServer.makeTransaction(transaction);
        } catch (IOException ex) {
            this.screen.setErrorText("Ошибка сети.");
        }
    }

}
