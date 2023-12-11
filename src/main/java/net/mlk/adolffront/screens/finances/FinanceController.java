package net.mlk.adolffront.screens.finances;

import net.mlk.adolffront.http.AdolfServer;
import net.mlk.adolffront.http.MultiPartRequest;

import java.io.IOException;

public class FinanceController {
    private final FinanceScreen screen;

    public FinanceController(FinanceScreen screen) {
        this.screen = screen;
    }

    public void getFinance() {
        try {
            MultiPartRequest.Response response = AdolfServer.getFinance();
        } catch (IOException ex) {

        }
    }

}
