package de.pomis.games.paperclips_solver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Computation extends SimpleAction {

    private static final int MIN_PROCESSORS = 5;

    private final WebElement addProcessor;
    private final WebElement addMemory;
    private final WebElement processorIndicator;
    private final WebElement memoryIndicator;

    public Computation(WebDriver webDriver) {
        this.addProcessor = Button.ADD_PROCESSOR.getElement(webDriver);
        this.addMemory = Button.ADD_MEMORY.getElement(webDriver);
        this.processorIndicator = Indicator.PROCESSORS.getElement(webDriver);
        this.memoryIndicator = Indicator.MEMORY.getElement(webDriver);
    }

    @Override
    public void run() {
        while (running) {
            boolean computationAvailable = processorIndicator.getText().length() > 0;

            if (computationAvailable) {
                long processors = longValue(processorIndicator);
                long memory = longValue(memoryIndicator);

                if (2 * processors < memory || processors < MIN_PROCESSORS) {
                    addProcessor.click();
                } else {
                    addMemory.click();
                }
            }
            
            waitASecond();
        }
    }

}
