package de.pomis.games.paperclips_solver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Marketing extends SimpleAction {
    
    private final WebElement button;

    public Marketing(WebDriver webDriver) {
        this.button = Button.MARKETING.getElement(webDriver);
    }

    @Override
    public void run() {
        while (running) {
            button.click();
        }
    }

}
