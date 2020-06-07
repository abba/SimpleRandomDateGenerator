import core.BrowserCore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.RandomDateGeneratorPage;
import pageobjects.SimpleDateValidator;

import static core.BrowserCore.initialiseDBrowser;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zsuleiman on 06/06/2020.
 */
public class RandomDateGeneratorTest {

    RandomDateGeneratorPage TestPage;
    SimpleDateValidator simpleDateValidator = new SimpleDateValidator();


    @Before
    public void setup() {

        initialiseDBrowser();
        TestPage = new RandomDateGeneratorPage();
        TestPage.gotoApp();
        assertThat(TestPage.getPageTitle(), containsString("Random date generator"));

    }


    @After
    public void tearDown() {

        BrowserCore.destroyBrowser();

    }


    @Test
    public void validCustomDateTest() {


        TestPage.setNumberOfDatesToGenerate("1");
        TestPage.setOutputFormat("Custom date format");
        TestPage.setCustomDateFormat("DD-MM-YYYY");

        assertThat(simpleDateValidator.getValidDatesList(TestPage.output()), hasItems());


    }

    @Test
    public void invalidCustomDateTest() {

        TestPage.setNumberOfDatesToGenerate("2");
        TestPage.setOutputFormat("Custom date format");
        TestPage.setCustomDateFormat("1DD-M111M-YYYY2");

        assertThat(simpleDateValidator.getValidDatesList(TestPage.output()).isEmpty(), is(true));

    }

    @Test
    public void numberOfDatesGeneratedTest() {

        TestPage.setNumberOfDatesToGenerate("5");
        TestPage.setOutputFormat("YYYY-MM-DD hh:mm:ss");
        TestPage.generateRandomDate();

        assertThat((long) TestPage.output().size(), is(5L));


    }


    @Test
    public void YYYYMMDDhhmmssFormatTest() {

        TestPage.setNumberOfDatesToGenerate("1");
        TestPage.setOutputFormat("YYYY-MM-DD hh:mm:ss");
        TestPage.setStartDate("2020-01-01 00:00:0");
        TestPage.setEndDate("2020-01-01 00:00:0");

        assertThat(simpleDateValidator.isValidDate(TestPage.output().get(0)
                , "YYYY-MM-DD hh:mm:ss"), is(true));

    }

    @Test
    public void YYYYDDMMhhmmssFormatTest() {

        TestPage.setNumberOfDatesToGenerate("1");
        TestPage.setOutputFormat("YYYY-MM-DD hh:mm:ss");
        TestPage.setStartDate("2020-01-01 00:00:00");
        TestPage.setEndDate("2020-01-01 00:00:00");
        TestPage.generateRandomDate();

        assertThat(simpleDateValidator.isValidDate(TestPage.output().get(0), "YYYY-MM-DD hh:mm:ss"), is(true));


    }

    @Test
    public void MMDDYYYYhhmmssFormatTest() {

        TestPage.setNumberOfDatesToGenerate("1");
        TestPage.setOutputFormat("MM-DD-YYYY hh:mm:ss");
        TestPage.setStartDate("01-01-2020 00:00:00");
        TestPage.setEndDate("01-01-2020 00:00:00");
        TestPage.generateRandomDate();

        assertThat(simpleDateValidator.isValidDate(TestPage.output().get(0), "MM-DD-YYYY hh:mm:ss"), is(true));


    }

    @Test
    public void monthsWith30DaysTest() {

        TestPage.setNumberOfDatesToGenerate("1");
        TestPage.setOutputFormat("MM-DD-YYYY hh:mm:ss");
        TestPage.setStartDate("06-31-2020 00:00:00");
        TestPage.setEndDate("06-31-2020 00:00:00");
        TestPage.generateRandomDate();

        assertThat(TestPage.output().toString(), containsString("07-01-2020 00:00:00"));

    }

    @Test
    public void validLeadYearDateTest() {

        TestPage.setNumberOfDatesToGenerate("1");
        TestPage.setOutputFormat("MM-DD-YYYY hh:mm:ss");
        TestPage.setStartDate("02-29-2020 00:00:00");
        TestPage.setEndDate("02-29-2020 00:00:00");
        TestPage.generateRandomDate();

        assertThat(TestPage.output().toString(), containsString("02-29-2020 00:00:00"));

    }

    @Test
    public void invalidLeadYearDateTest() {

        TestPage.setNumberOfDatesToGenerate("1");
        TestPage.setOutputFormat("MM-DD-YYYY hh:mm:ss");
        TestPage.setStartDate("02-29-2019 00:00:00");
        TestPage.setEndDate("02-29-2019 00:00:00");
        TestPage.generateRandomDate();

        assertThat(TestPage.output().toString(), containsString("03-01-2019 00:00:00"));
    }

}
