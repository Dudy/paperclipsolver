package de.pomis.games.paperclips_solver;

import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MakePaperclip extends SimpleAction {

    private static final Logger LOG = Logger.getLogger(MakePaperclip.class.getName());
    
    private final WebElement button;

    public MakePaperclip(WebDriver webDriver) {
        super(webDriver);
        
        this.button = getButton(Button.MAKE_PAPERCLIP);
    }

    @Override
    public void run() {
        while (running) {
            button.click();
        }
    }

}
