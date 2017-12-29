package de.pomis.games.paperclips_solver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum Button {

    MAKE_PAPERCLIP("btnMakePaperclip"),
    LOWER_PRICE("btnLowerPrice"),
    RAISE_PRICE("btnRaisePrice"),
    MARKETING("btnExpandMarketing"),
    BUY_WIRE("btnBuyWire"),
    AUTOCLIPPER("btnMakeClipper"),
    ADD_PROCESSOR("btnAddProc"),
    ADD_MEMORY("btnAddMem");
    
    private final String id;

    private Button(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    public WebElement getElement(WebDriver webDriver) {
        return webDriver.findElement(By.id(id));
    }
    
}
