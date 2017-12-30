package de.pomis.games.paperclips_solver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainClass {
    
    private static final String URL = "http://localhost:8383/index.html";
    //private static final String URL = "http://www.decisionproblem.com/paperclips/index2.html";
    
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/podolak/code/tools/selenium/chromedriver");
        MainClass mainClass = new MainClass();
        mainClass.test01();
    }
    
    public void test01() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(URL);
        
        (new Thread(new MakePaperclip(webDriver))).start();
        (new Thread(new BuyWire(webDriver))).start();
        (new Thread(new PriceAdjustment(webDriver))).start();
        (new Thread(new BuyAutoclipper(webDriver))).start();
        (new Thread(new Marketing(webDriver))).start();
        (new Thread(new Computation(webDriver))).start();
        (new Thread(new Projects(webDriver))).start();
        
    }

}
