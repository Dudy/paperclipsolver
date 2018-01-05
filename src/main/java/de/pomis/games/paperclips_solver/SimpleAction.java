package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class SimpleAction implements Runnable {

    private static final Logger LOG = Logger.getLogger(SimpleAction.class.getName());
    
    protected WebDriver webDriver;
    protected JavascriptExecutor javascriptExecutor;
    protected boolean running = true;

    public SimpleAction(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.javascriptExecutor = (JavascriptExecutor)webDriver;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    protected long longValue(WebElement indicator) {
        LOG.log(Level.FINER, "scan indicator {0}", indicator);
        
        long value = 0;
        String indicatorText = indicator.getText().replace(",", "");
        
        LOG.log(Level.FINER, "indicator text: {0}", indicatorText);
        
        if (!indicatorText.isEmpty()) {
            value = Long.parseLong(indicatorText);
        }
        
        LOG.log(Level.FINE, "indicator value: {0}", value);
        
        return value;
    }
    
    protected long readCurrency(WebElement indicator) {
        LOG.log(Level.FINER, "scan indicator {0}", indicator);
        
        long value = 0;
        String indicatorText = indicator.getText().replace(",", "");
        
        LOG.log(Level.FINER, "indicator text: {0}", indicatorText);
        
        if (!indicatorText.isEmpty()) {
            String[] indicatorTextParts = indicatorText.split("\\.");

            value = Long.parseLong(indicatorTextParts[0]) * 100;

            if (indicatorTextParts.length > 1) {
                value += Long.parseLong(indicatorTextParts[1]);
            }
        }
        
        LOG.log(Level.FINE, "indicator value: {0}", value);
        
        return value;
    }
    
    protected String formatCurrency(long amount) {
        long cent = amount % 100;
        long dollar = (amount - cent) / 100;
        
        return "$" + dollar + "." + cent;
    }

    protected void waitASecond() {
        try {
            LOG.fine("sleep for a second");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuyAutoclipper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected final WebElement getButton(Button button) {
        return webDriver.findElement(By.id(button.getId()));
    }
    
    protected final WebElement getIndicator(Indicator indicator) {
        return webDriver.findElement(By.id(indicator.getId()));
    }
    
}
