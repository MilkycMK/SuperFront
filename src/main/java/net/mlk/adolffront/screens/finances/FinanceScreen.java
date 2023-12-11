package net.mlk.adolffront.screens.finances;

import net.mlk.adolffront.screens.menu.AbstractMenuElement;

public class FinanceScreen extends AbstractMenuElement {
    private final FinanceController controller;

    public FinanceScreen() {
        super("Финансики");
        this.controller = new FinanceController(this);
    }

    @Override
    public void drawScreen() {

    }

}
