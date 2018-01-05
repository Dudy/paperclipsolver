package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyWire extends SimpleAction {

    private static final Logger LOG = Logger.getLogger(BuyWire.class.getName());
    
    private static final int MAX_AMOUNT = 1000;
    
    private final WebElement button;
    private final WebElement indicator;
    private final WebElement funds;
    private final WebElement wireCost;

    public BuyWire(WebDriver webDriver) {
        this.button = Button.BUY_WIRE.getElement(webDriver);
        this.indicator = Indicator.WIRES.getElement(webDriver);
        this.funds = Indicator.FUNDS.getElement(webDriver);
        this.wireCost = Indicator.WIRE_COST.getElement(webDriver);
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            long numOfWires = longValue(indicator);
            LOG.log(Level.FINE, "number of wires: {0}", numOfWires);
            
            if (numOfWires < MAX_AMOUNT) {
                if (button.isEnabled()) {
                    LOG.log(Level.INFO, "got {0} wires, which is less than {1} wires, and got {2} which is more than the needed {3}, so buy one more spool", new Object[]{numOfWires, MAX_AMOUNT, formatCurrency(longValue(funds)), formatCurrency(longValue(wireCost))});
                    button.click();
                } else {
                    LOG.log(Level.FINER, "${0} is not enough money to buy a spool of wire that costs ${1}", new Object[]{formatCurrency(longValue(funds)), formatCurrency(longValue(wireCost))});
                }
            } else {
                LOG.log(Level.FINER, "still have {0}, which is more than {1}, do not rebuy", new Object[]{numOfWires, MAX_AMOUNT});
            }
            
            waitASecond();
        }
    }

}
