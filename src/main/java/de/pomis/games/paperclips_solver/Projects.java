package de.pomis.games.paperclips_solver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Projects extends SimpleAction {

    private static final Logger LOG = Logger.getLogger(Projects.class.getName());

    private static final String PREFIX = "projectButton";
    private static final int[] ORDER = new int[]{
         3, 42,  1,  6, 13,
        14, 15, 11,  4,  7,
        12,  8,  5, 16, 26,
         9, 34, 17, 19, 50,
        51, 22, 10, 20, 21 };
    
    // TODO: after project 26, the BuyWire thread may be killed

    private int index = 0;

    public Projects(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void run() {
        while (running) {
            List<WebElement> projectListTop = webDriver.findElements(By.cssSelector("#projectListTop *"));

            if (projectListTop.size() > 0) {
                WebElement currentProject = getCurrentProject();

                if (currentProject != null) {
                    if (currentProject.isEnabled()) {
                        currentProject.click();
                        index++;
                        LOG.log(Level.INFO, "current project {0} finished, increased index to {1}, next project is {2}", new Object[] { ORDER[index - 1], index, ORDER[index] });
                    }
                }
            } else {
                LOG.log(Level.FINE, "no projects available");
            }

            waitASecond();
        }
    }

    private WebElement getCurrentProject() {
        WebElement currentProject;
        String id = PREFIX + ORDER[index];

        try {
            currentProject = webDriver.findElement(By.id(id));
        } catch (WebDriverException e) {
            currentProject = null;
        }
        
        LOG.log(Level.FINE, "current project (index {0}) chosen by id {1}: {2}", new Object[] { index, id, currentProject });

        return currentProject;
    }

}
