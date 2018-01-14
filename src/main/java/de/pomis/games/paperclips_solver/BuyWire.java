package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyWire extends SimpleAction {

    private static final Logger LOG = Logger.getLogger(BuyWire.class.getName());

    private int minWireAmountIncThreshold = 10000;    
    private int minWireAmount = 1000;
    
    private final WebElement button;
    private final WebElement indicator;
    private final WebElement clips;
    private final WebElement funds;
    private final WebElement wireCost;

    public BuyWire(WebDriver webDriver) {
        super(webDriver);
        
        this.button = getButton(Button.BUY_WIRE);
        
        this.indicator = getIndicator(Indicator.WIRES);
        this.clips  = getIndicator(Indicator.CLIPS);
        this.funds = getIndicator(Indicator.FUNDS);
        this.wireCost = getIndicator(Indicator.WIRE_COST);
        
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            long numOfWires = longValue(indicator);
            LOG.log(Level.FINE, "number of wires: {0}", numOfWires);
            
            if (numOfWires < minWireAmount) {
                if (button.isEnabled()) {
                    button.click();
                    LOG.log(Level.INFO, "got {0} wires, which is less than {1} wires, and got {2} which is more than the needed {3}, so buy one more spool", new Object[]{numOfWires, minWireAmount, formatCurrency(readCurrency(funds)), formatCurrency(readCurrency(wireCost))});
                } else {
                    LOG.log(Level.FINER, "{0} is not enough money to buy a spool of wire that costs {1}", new Object[]{formatCurrency(readCurrency(funds)), formatCurrency(readCurrency(wireCost))});
                    LOG.log(Level.SEVERE, "{0} is not enough money to buy a spool of wire that costs {1}", new Object[]{formatCurrency(readCurrency(funds)), formatCurrency(readCurrency(wireCost))});
                }
            } else {
                LOG.log(Level.FINER, "still have {0}, which is more than {1}, do not rebuy", new Object[]{numOfWires, minWireAmount});
                LOG.log(Level.SEVERE, "still have {0}, which is more than {1}, do not rebuy", new Object[]{numOfWires, minWireAmount});
            }
            
            if (longValue(clips) > minWireAmountIncThreshold) {
                minWireAmount *= 2;
                minWireAmountIncThreshold *= 10;
                
                LOG.log(Level.FINE, "minWireAmount raised to {0}", minWireAmount);
                LOG.log(Level.FINE, "minWireAmountIncThreshold raised to {0}", minWireAmountIncThreshold);
                LOG.log(Level.SEVERE, "minWireAmount raised to {0}", minWireAmount);
                LOG.log(Level.SEVERE, "minWireAmountIncThreshold raised to {0}", minWireAmountIncThreshold);
            }
            
            waitASecond();
        }
    }

}
