package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class SimpleAction implements Runnable {
    
    protected boolean running = true;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    protected long longValue(WebElement indicator) {
        return Long.parseLong(indicator.getText().replace(",", ""));
    }

    protected void waitASecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuyAutoclipper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
