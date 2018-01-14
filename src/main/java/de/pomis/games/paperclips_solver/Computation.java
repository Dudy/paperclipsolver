package de.pomis.games.paperclips_solver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Computation extends SimpleAction {
    
    private static final Logger LOG = Logger.getLogger(Computation.class.getName());

    private static final int MIN_PROCESSORS = 5;

    private final WebElement addProcessor;
    private final WebElement addMemory;
    private final WebElement processorIndicator;
    private final WebElement memoryIndicator;

    public Computation(WebDriver webDriver) {
        super(webDriver);
        
        this.addProcessor = getButton(Button.ADD_PROCESSOR);
        this.addMemory = getButton(Button.ADD_MEMORY);
        
        this.processorIndicator = getIndicator(Indicator.PROCESSORS);
        this.memoryIndicator = getIndicator(Indicator.MEMORY);
    }

    @Override
    public void run() {
        while (running) {
            boolean computationAvailable = processorIndicator.getText().length() > 0;

            if (computationAvailable) {
                long processors = longValue(processorIndicator);
                long memory = longValue(memoryIndicator);

                if (addProcessor.isEnabled()) {
                    if (2 * processors < memory || processors < MIN_PROCESSORS) {
                        addProcessor.click();
                        LOG.log(Level.INFO, "we have {0} processors and {1} memory, so buy one processor", new Object[] {processors, memory});
                    } else {
                        addMemory.click();
                        LOG.log(Level.INFO, "we have {0} processors and {1} memory, so buy one memory", new Object[] {processors, memory});
                    }
                }
            }
            
            waitASecond();
        }
    }

}
