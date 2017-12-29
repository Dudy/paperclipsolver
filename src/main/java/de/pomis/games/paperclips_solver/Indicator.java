package de.pomis.games.paperclips_solver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum Indicator {

    WIRES("wire"),
    FUNDS("funds"),
    CLIPS("unsoldClips"),
    AUTOCLIPPERS("clipmakerLevel2"),
    PROCESSORS("processors"),
    MEMORY("memory");
    
    private final String id;

    private Indicator(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    public WebElement getElement(WebDriver webDriver) {
        return webDriver.findElement(By.id(id));
    }
    
}
