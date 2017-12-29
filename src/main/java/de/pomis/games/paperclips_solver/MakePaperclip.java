package de.pomis.games.paperclips_solver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MakePaperclip extends SimpleAction {
    
    private final WebElement button;

    public MakePaperclip(WebDriver webDriver) {
        this.button = Button.MAKE_PAPERCLIP.getElement(webDriver);
    }

    @Override
    public void run() {
        while (running) {
            button.click();
        }
    }

}
