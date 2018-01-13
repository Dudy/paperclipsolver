package de.pomis.games.paperclips_solver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PriceAdjustment extends SimpleAction {
    
    private final WebElement lower;
    private final WebElement raise;
    private final WebElement indicator;
    private final WebElement clips;
    private long lastMeasurement;

    public PriceAdjustment(WebDriver webDriver) {
        super(webDriver);
        
        this.lower = getButton(Button.LOWER_PRICE);
        this.raise = getButton(Button.RAISE_PRICE);
        
        this.indicator = getIndicator(Indicator.UNSOLD_CLIPS);
        this.clips = getIndicator(Indicator.CLIPS);
        
        this.running = true;
        this.lastMeasurement = 0;
    }

    @Override
    public void run() {
        while (running) {
            long currentMeasurement = longValue(indicator);
            long currentClips = longValue(clips);
            
            if (    currentMeasurement * 10 > currentClips &&
                    currentMeasurement > lastMeasurement) {
                lower.click();
            } else {
                raise.click();
            }
            lastMeasurement = currentMeasurement;
            
            waitASecond();
        }
    }

}
