/**
 * CIS 162 - Project 3 "Covid Database" *test/debug*
 *
 * @author Austin Ellsworth
 * @version 11-30-2020
 */

public class CovidDatabaseTest {

    public static void main () {
        System.out.println ("Testing starts");
        CovidDatabase db = new CovidDatabase() ;
        db.readCovidData("covid_data.csv");

        // check number of records, total infections, and total deaths
        assert db.countRecords() == 10346 : "database should have 10,346"; 
        assert db.getTotalDeaths() == 196696 : "Total deaths should be: 196,696"; 
        assert db.getTotalInfections() ==  7032090 : "infections should be: 7,032,090"; 

        // check peak daily deaths for 5/5
        CovidEntry mostDeaths = db.peakDailyDeaths(5, 5);
        assert mostDeaths.getState().equals("PA") : "State with most deaths for 5/5 is PA";
        assert mostDeaths.getDailyDeaths() ==  554 : "Deaths for 5/5 is PA: 554";

        // test other methods
        //just a collection of println's i created while working on the main class
        db.readCovidData("covid_data.csv");
        System.out.println("count records: " + db.countRecords());
        System.out.println("get total deaths: " + db.getTotalDeaths());
        System.out.println("get total infections: " + db.getTotalInfections());
        System.out.println("count total deaths on 6-20: " + db.countTotalDeaths(6, 20));
        System.out.println("count total infections on 6-20: " + db.countTotalInfections(6, 20));
        System.out.println("peak daily deaths in Michigan: " + db.peakDailyDeaths("MI"));
        System.out.println("get daily deaths on 5-5: " + db.getDailyDeaths(5, 5));
        System.out.println("peak daily deaths on 4-19: " + db.peakDailyDeaths(4, 19));
        System.out.println("most total deaths: " + db.mostTotalDeaths());
        System.out.println("list min daily infections on 4-19, min 500: " + db.listMinimumDailyInfections(4, 19, 500));
        System.out.println("safe to open MI: " + db.safeToOpen("MI"));
        
        System.out.println ("Testing ends");
    }
}
