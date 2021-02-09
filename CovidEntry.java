
/**
 * Class for covid entry objects which are used in CovidDatabase class
 *
 * @author Austin Ellsworth
 * @version November 30, 2020
 */
import java.text.DecimalFormat;

public class CovidEntry implements Comparable
{
   
    private String state;
    private int month, day, dailyDeaths, dailyInfections, totalDeaths, totalInfections;

    /**
     * Constructor for objects of class CovidEntry
     */
    public CovidEntry(String st, int m, int d, int di, int dd, int ti, int td)
    {
        //instance variables
        state = st;
        month = m;
        day = d;
        dailyInfections = di;
        dailyDeaths = dd;
        totalInfections = ti;
        totalDeaths = td;
    }

    /**
     * Methods below are all simple setters and gettters
     */
    public int getMonth()
    {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getState() {
        return state;
    }

    public int getDailyInfections() {
        return dailyInfections;
    }

    public int getDailyDeaths() {
        return dailyDeaths;
    }

    public int getTotalInfections() {
        return totalInfections;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }
    
    public int compareTo(Object other){
        CovidEntry c = (CovidEntry) other;
        return c.dailyDeaths - dailyDeaths;
     }


    public String toString() {   
        DecimalFormat formatter = new DecimalFormat("#,###");

        return (state + " " + month + "/" + day + " " + (formatter.format(dailyInfections)) + " infections, " + 
        (formatter.format(dailyDeaths)) + " deaths");
        
    }

}
