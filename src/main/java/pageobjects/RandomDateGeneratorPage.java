package pageobjects;

import core.BrowserCore;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;


/**
 * Created by zsuleiman on 06/06/2020.
 */
public class RandomDateGeneratorPage extends BrowserCore {

    private static final String APP_URL = "https://codebeautify.org/generate-random-date";

    @FindBy(css = "[data-index='count']")
    private WebElement howManyDatesToGenerate;

    @FindBy(css = "[data-index='format']")
    private WebElement dateOutPutFormat;

    @FindBy(css = "[data-index='custom-format']")
    private WebElement customDateFormat;

    @FindBy(css = "[data-index='start']")
    private WebElement startDate;

    @FindBy(css = "[data-index='end']")
    private WebElement endDate;

    @FindBy(css ="textarea.data.randomjson")
    private WebElement output;

    @FindBy(css = "button.generatejson")
    private WebElement generateRandomDatesButton;

    private List<String> dates ;

    public RandomDateGeneratorPage() {

        super(aDriver);

        PageFactory.initElements(aDriver, this);

    }


    public void gotoApp() {

        aDriver.navigate().to(APP_URL);

    }

    public String getPageTitle() {


        return aDriver.getTitle();
    }

    public void setNumberOfDatesToGenerate(String number) {

        howManyDatesToGenerate.clear();
        howManyDatesToGenerate.sendKeys(number);
    }

    public void setOutputFormat(String outputFormat) {

        new Select(dateOutPutFormat).selectByVisibleText(outputFormat);

    }


    public void setCustomDateFormat(String format) {


        customDateFormat.clear();
        customDateFormat.sendKeys(format);

    }

    public void generateRandomDate() {

        generateRandomDatesButton.click();
    }

    public void setStartDate(String date){

        startDate.clear();
        startDate.sendKeys(date);
    }

    public void setEndDate(String date){

        endDate.clear();
        endDate.sendKeys(date);
    }

    public List<String> output() {

        return dates= Arrays.asList(output.getAttribute("value").trim().split("\n"));

    }


}
