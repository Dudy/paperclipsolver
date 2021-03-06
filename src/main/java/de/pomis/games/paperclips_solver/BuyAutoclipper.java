package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyAutoclipper extends SimpleAction {

    private static final Logger LOG = Logger.getLogger(BuyAutoclipper.class.getName());
    
    private static final int MAX_AMOUNT = 100;
    private static final float MAX_AUTOCLIPPER_PERCENTAGE_OF_MARKETING = 0.25f;
    
    private int minWireAccountIncThreshold = 10000;
    private int minWireAmount = 1000;
    
    private final WebElement button;
    private final WebElement indicator;
    private final WebElement clips;
    private final WebElement wires;
    private final WebElement funds;
    private final WebElement marketingCost;
    private final WebElement autoclipperCost;

    public BuyAutoclipper(WebDriver webDriver) {
        super(webDriver);
        
        this.button = getButton(Button.AUTOCLIPPER);
        
        this.indicator = getIndicator(Indicator.AUTOCLIPPERS);
        this.clips  = getIndicator(Indicator.CLIPS);
        this.wires = getIndicator(Indicator.WIRES);
        this.funds = getIndicator(Indicator.FUNDS);
        this.marketingCost = getIndicator(Indicator.MARKETING_COST);
        this.autoclipperCost = getIndicator(Indicator.AUTOCLIPPER_COST);
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            boolean autoClipperAvailable = indicator.isDisplayed();
            
            if (autoClipperAvailable) {
                LOG.fine("AutoClipper is available");
                
                long numOfWires = longValue(wires);
                LOG.log(Level.FINE, "number of wires: {0}", numOfWires);
                
                boolean enoughWires = numOfWires > minWireAmount;
                
                if (enoughWires) {
                    LOG.fine("enough wires available");
                    
                    long numOfAutoClippers = longValue(indicator);
                    LOG.log(Level.FINE, "number of autoclippers: {0}", numOfAutoClippers);
                    
                    boolean autoClipperNotFull = numOfAutoClippers <= MAX_AMOUNT;
                    
                    if (autoClipperNotFull) {
                        LOG.log(Level.FINE, "got {0} autoclippers, which is less than {1}", new Object[] {numOfAutoClippers, MAX_AMOUNT});
                        
                        long autoclipperCostAmount = readCurrency(autoclipperCost);
                        long marketingCostAmount = readCurrency(marketingCost);
                        float ratio = (float)autoclipperCostAmount / (float)marketingCostAmount;
                        
                        LOG.log(Level.FINE, "autoclipper cost: {0}, marketing cost: {1}, ratio: {2}", new Object[] { autoclipperCostAmount, marketingCostAmount, ratio });
                        
                        if (ratio < MAX_AUTOCLIPPER_PERCENTAGE_OF_MARKETING) {
                            if (button.isEnabled()) {
                                button.click();
                                LOG.log(Level.INFO, "buy one autoclipper, now you got {0}", new Object[] { longValue(indicator) });
                            } else {
                                LOG.log(Level.INFO, "like to buy one autoclipper, but only got {0} (need {1})", new Object[] { formatCurrency(readCurrency(funds)), formatCurrency(autoclipperCostAmount) });
                            }
                        } else {
                            LOG.log(Level.FINE, "don't get a new autoclipper now, save money for raising marketing");
                        }
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
            
            if (longValue(clips) > minWireAccountIncThreshold) {
                minWireAmount *= 2;
                minWireAccountIncThreshold *= 10;
                
                LOG.log(Level.FINE, "minWireAmount raised to {0}", minWireAmount);
                LOG.log(Level.FINE, "minWireAccountIncThreshold raised to {0}", minWireAccountIncThreshold);
            }
            
            waitASecond();
        }
    }

}
