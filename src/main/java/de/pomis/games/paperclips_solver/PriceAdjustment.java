package de.pomis.games.paperclips_solver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PriceAdjustment extends SimpleAction {
    
    private static final int CHECK_INTERVALL = 1000;
    
    private final WebElement lower;
    private final WebElement raise;
    private final WebElement indicator;
    private long lastMeasurement;

    public PriceAdjustment(WebDriver webDriver) {
        this.lower = Button.LOWER_PRICE.getElement(webDriver);
        this.raise = Button.RAISE_PRICE.getElement(webDriver);
        this.indicator = Indicator.CLIPS.getElement(webDriver);
        this.running = true;
        this.lastMeasurement = 0;
    }

    @Override
    public void run() {
        while (running) {
            long currentMeasurement = longValue(indicator);
            if (currentMeasurement > lastMeasurement) {
                lower.click();
            } else {
                raise.click();
            }
            lastMeasurement = currentMeasurement;
            
            waitASecond();
        }
    }

}
