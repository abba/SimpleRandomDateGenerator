package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.util.concurrent.TimeUnit;

/**
 * Created by zsuleiman on 06/06/2020.
 */
public class BrowserCore {

    public static WebDriver aDriver;
    private static String currentDir = System.getProperty("user.dir");


    public BrowserCore(WebDriver aDrive){
        this.aDriver= aDrive;
    }



    public static void setSystemsProperties( String driverPath) {

        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", currentDir + driverPath + ".exe");
        } else {
            System.setProperty("webdriver.chrome.driver", currentDir + driverPath);
        }

    }

    public static WebDriver initialiseDBrowser(){

        setSystemsProperties("/drivers/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1400,2100");
        options.addArguments("--enable-javascript");
        aDriver= new ChromeDriver(options);
        aDriver.manage().window().maximize();
        aDriver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);

        return aDriver;
    }


    public static void destroyBrowser(){
        try{

            aDriver.close();
            if(aDriver!=null){

                aDriver= null;
            }

        }

        catch (UnreachableBrowserException e){

            System.out.println("Browser already destroyed");
        }
    }


}
