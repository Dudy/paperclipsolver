package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyAutoclipper extends SimpleAction {
    
    private static final int CHECK_INTERVALL = 1000;
    private static final int MAX_AMOUNT = 100;
    private static final int MIN_WIRE_AMOUNT = 1000;
    
    private final WebElement button;
    private final WebElement indicator;
    private final WebElement wires;

    public BuyAutoclipper(WebDriver webDriver) {
        this.button = Button.AUTOCLIPPER.getElement(webDriver);
        this.indicator = Indicator.AUTOCLIPPERS.getElement(webDriver);
        this.wires = Indicator.WIRES.getElement(webDriver);
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            boolean autoClipperAvailable = indicator.getText().length() > 0;
            if (autoClipperAvailable) {
                boolean enoughWires = Integer.parseInt(wires.getText().replace(",", "")) > MIN_WIRE_AMOUNT;
                if (enoughWires) {
                    boolean autoClipperNotFull = longValue(indicator) <= MAX_AMOUNT;
                    
                    if (autoClipperNotFull) {
                        button.click();
                    } else {
                        running = false;
                    }
                }
            }
            
            try {
                Thread.sleep(CHECK_INTERVALL);
            } catch (InterruptedException ex) {
                Logger.getLogger(BuyAutoclipper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
