package de.pomis.games.paperclips_solver;

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

}
