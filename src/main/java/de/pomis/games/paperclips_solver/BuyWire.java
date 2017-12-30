package de.pomis.games.paperclips_solver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyWire extends SimpleAction {
    
    private static final int CHECK_INTERVALL = 1000;
    
    private final WebElement button;
    private final WebElement indicator;

    public BuyWire(WebDriver webDriver) {
        this.button = Button.BUY_WIRE.getElement(webDriver);
        this.indicator = Indicator.WIRES.getElement(webDriver);
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            if (longValue(indicator) < 1000) {
                button.click();
            }
            
            waitASecond();
        }
    }

}
