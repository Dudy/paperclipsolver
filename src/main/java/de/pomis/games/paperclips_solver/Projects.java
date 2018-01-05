package de.pomis.games.paperclips_solver;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Projects extends SimpleAction {

    private static final String PREFIX = "projectButton";
    private static final int[] ORDER = new int[]{3, 42, 1, 6, 50};

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
                    System.out.println(currentProject.isDisplayed());
                    System.out.println(currentProject.isEnabled());
                    System.out.println(currentProject.isSelected());
                    System.out.println(currentProject.getCssValue("display"));
                    System.out.println(currentProject.getAttribute("disabled"));
                    System.out.println("------------------------------------------");
                    currentProject.click();
                }
            }

            waitASecond();
        }
    }

    private WebElement getCurrentProject() {
        WebElement currentProject;

        try {
            currentProject = webDriver.findElement(By.id(PREFIX + ORDER[index]));
        } catch (WebDriverException e) {
            currentProject = null;
        }

        return currentProject;
    }

}
