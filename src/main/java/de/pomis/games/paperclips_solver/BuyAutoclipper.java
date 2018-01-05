package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyAutoclipper extends SimpleAction {

    private static final Logger LOG = Logger.getLogger(BuyAutoclipper.class.getName());
    
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
                LOG.fine("AutoClipper is available");
                
                long numOfWires = longValue(wires);
                LOG.log(Level.FINE, "number of wires: {0}", numOfWires);
                
                boolean enoughWires = numOfWires > MIN_WIRE_AMOUNT;
                
                if (enoughWires) {
                    LOG.fine("enough wires available");
                    
                    long numOfAutoClippers = longValue(indicator);
                    LOG.log(Level.FINE, "number of autoclippers: {0}", numOfAutoClippers);
                    
                    boolean autoClipperNotFull = numOfAutoClippers <= MAX_AMOUNT;
                    
                    if (autoClipperNotFull) {
                        LOG.log(Level.INFO, "less than {0} autoclippers, buy one more", MAX_AMOUNT);
                        button.click();
                    } else {
                        LOG.info("reached maximum number of autoclippers, stop this thread");
                        running = false;
                    }
                } else {
                    LOG.fine("not enough wires");
                }
            } else {
                LOG.fine("AutoClipper not available");
            }
            
            waitASecond();
        }
    }

}
