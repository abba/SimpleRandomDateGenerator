package pageobjects;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

/**
 * Created by zsuleiman on 06/06/2020.
 */
public class SimpleDateValidator extends RandomDateGeneratorPage   {

    private SimpleDateFormat dateFormat;
    private Set<String> dateOutputs = new HashSet<>();
    private String customDatesRegex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
    private Pattern pattern;
    private Matcher matcher;


    public boolean addOutput(final String newString) {
        return this.dateOutputs.add(newString);
    }
    public Set<String> getDates() {
        return Collections.unmodifiableSet(this.dateOutputs);
    }


    public Set<String> getValidDatesList(List<String> dates) {

        pattern = Pattern.compile(customDatesRegex);

        dates.forEach(date -> {

            matcher = pattern.matcher(date);
            if(matcher.matches()) {
                addOutput(date);
                System.out.println(date);
            }

        });

        return getDates();

    }

   public Boolean isValidDate(String aDate, String formatOfDate){

        boolean isValid = false;

        dateFormat = new SimpleDateFormat(formatOfDate);
        dateFormat.setLenient(true);

        try{
            Date date = dateFormat.parse(aDate);
            isValid = true;


        }catch (ParseException e){
            System.out.println(e.getMessage());
            isValid=false;
        }
    return isValid;
    }



}
