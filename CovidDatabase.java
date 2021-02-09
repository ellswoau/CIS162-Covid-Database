
/**
 * CIS 162 - Project 3 "Covid Database"
 *
 * @author Austin Ellsworth
 * @version 11-30-2020
 */
import java.util.*;
import java.io.*;

public class CovidDatabase
{
    private ArrayList<CovidEntry> entryArray;
    private static final int SAFE = 5;

    /**
     * Constructor for objects of class CovidDatabase
     */
    public CovidDatabase()
    {
        entryArray = new ArrayList<CovidEntry>();
    }

    /***************************************************************
     * Method to read covid database file, which uses a loop to add
     * the data to an array list
     ***************************************************************/ 
    public void readCovidData(String filename){
        Scanner inFS = null; 
        FileInputStream fileByteStream = null;
        String st;
        int mo, da, di, dd, ti, td;

        try {
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            for (int i = 0; i < 7; i++) {
                inFS.next();
            }

            // continue while there is more data to read
            while(inFS.hasNext()) {
                st = inFS.next();
                mo = inFS.nextInt();
                da = inFS.nextInt();
                di = inFS.nextInt();
                dd = inFS.nextInt();
                ti = inFS.nextInt();
                td = inFS.nextInt();

                entryArray.add( new CovidEntry(st, mo, da, di, dd ,ti, td)); 

            }
            //entryArray.add( new CovidEntry("mi", 2, 4, 10, 10, 12, 12));
            fileByteStream.close();

            // error while reading the file                      
        }catch(IOException error1) {
            System.out.println("Oops! Error related to: " + filename);
        }        
    }

    
    /***************************************************************
     * Helper method to count records
     ***************************************************************/ 
    public int countRecords() {
        return entryArray.size();     
    }

    
    /***************************************************************
     * Helper method to get total deaths, loops through daily deaths 
     * and adds them up
     ***************************************************************/ 
    public int getTotalDeaths() {
        int total = 0;
        for (CovidEntry entry : entryArray) {
            total += entry.getDailyDeaths();
        }
        return total;
    }

    
    /***************************************************************
     * Helper method to get total number of infections, loops through
     * daily infections and adds them up
     ***************************************************************/ 
    public int getTotalInfections() {
        int total = 0;
        for (CovidEntry entry : entryArray) {
            total += entry.getDailyInfections();
        }
        return total;
    }

    
    /***************************************************************
     * Helper method to count total deaths on specific date
     ***************************************************************/ 
    public int countTotalDeaths(int m, int d) {
        int total = 0;
        for (CovidEntry entry : entryArray) {
            if (entry.getMonth() == m && entry.getDay() == d) {
                total += entry.getDailyDeaths();
            }
        }
        return total;
    }

    
    /***************************************************************
     * Helper method to count total infecitons on specific date
     ***************************************************************/ 
    public int countTotalInfections(int m, int d) {
        int total = 0;
        for (CovidEntry entry : entryArray) {
            if (entry.getMonth() == m && entry.getDay() == d) {
                total += entry.getDailyInfections();
            }
        }
        return total;
    }

    
    /***************************************************************
     * Helper method to loop through daily deaths and return the 
     * entry with the greatest number, searches by state
     ***************************************************************/ 
    public CovidEntry peakDailyDeaths(String st) {
        int dailyDeathsResult = 0;
        CovidEntry result = null;
        for (CovidEntry entry : entryArray) {
            if (entry.getState().equals((st).toUpperCase())) {
                if (entry.getDailyDeaths() > dailyDeathsResult) {
                    dailyDeathsResult = entry.getDailyDeaths();
                    result = entry;
                }
            }
        }
        return result;
    }

    
    /***************************************************************
     * Helper method returns peak daily deaths using a specific
     * day instead of a state
     ***************************************************************/ 
    public CovidEntry peakDailyDeaths(int m, int d) {
        int dailyDeathsResult = 0;
        CovidEntry result = null;
        for (CovidEntry entry : entryArray) {
            if (entry.getMonth() == m && entry.getDay() == d) {
                if (entry.getDailyDeaths() > dailyDeathsResult) {
                    dailyDeathsResult = entry.getDailyDeaths();
                    result = entry;
                }
            }
        }
        return result;
    }

    
    /***************************************************************
     * Helper method rto return an array of all daily deaths on
     * a specific month and day
     ***************************************************************/ 
    public ArrayList <CovidEntry> getDailyDeaths(int m, int d) {
        ArrayList<CovidEntry> arrayResult;
        arrayResult = new ArrayList<CovidEntry>();
        for (CovidEntry entry : entryArray) {
            if (entry.getMonth() == m && entry.getDay() == d) {
                arrayResult.add(entry);
            }
        }
        return arrayResult;     
    }

    
    /***************************************************************
     * Helper method returns covid entry with the most total deaths
     ***************************************************************/ 
    public CovidEntry mostTotalDeaths() {
        int totalDeathsResult = 0;
        CovidEntry result = null;
        for (CovidEntry entry : entryArray) {

            if (entry.getTotalDeaths() > totalDeathsResult) {
                totalDeathsResult = entry.getTotalDeaths();
                result = entry;
            }

        }
        return result;
    }

    
    /***************************************************************
     * Helper method to return an array list of all elements on a 
     * specific month and day, that also have a number of daily
     * infections that are greater or equal to the minimum
     ***************************************************************/ 
    public ArrayList <CovidEntry> listMinimumDailyInfections(int m, int d, int min) {
        ArrayList<CovidEntry> arrayResult;
        arrayResult = new ArrayList<CovidEntry>();
        for (CovidEntry entry : entryArray) {
            if (entry.getMonth() == m && entry.getDay() == d && entry.getDailyInfections() >= min) {
                arrayResult.add(entry);
            }
        }
        return arrayResult;     
    }

    
    /***************************************************************
     * Helper method creates new array list for the specific state
     * it is searching through, and then returns the first 5 entries
     * that have decreasing daily infections
     ***************************************************************/ 
    public ArrayList<CovidEntry> safeToOpen(String st) {
        ArrayList<CovidEntry> arrayResult;
        ArrayList<CovidEntry> arrayForState;
        arrayForState = new ArrayList<CovidEntry>();
        arrayResult = new ArrayList<CovidEntry>();

        //temporarily create new array online containing elements from specified state so the following 
        //nested loop doesn't have to search through original, much larger arraylist.
        for (CovidEntry entry : entryArray) {
            if (entry.getState().equals(st)) {
                arrayForState.add(entry);
            }
        }

        for (int i = 5; i < arrayForState.size(); i++) {
            //not elegant but looks like it works, consider revising loop after project completed
            if (arrayForState.get(i).getDailyInfections() < arrayForState.get(i-1).getDailyInfections()) {
                if (arrayForState.get(i-1).getDailyInfections() < arrayForState.get(i-2).getDailyInfections()) {
                    if (arrayForState.get(i-2).getDailyInfections() < arrayForState.get(i-3).getDailyInfections()) {
                        if (arrayForState.get(i-3).getDailyInfections() < arrayForState.get(i-4).getDailyInfections()) {

                            arrayResult.add(arrayForState.get(i-4));
                            arrayResult.add(arrayForState.get(i-3));
                            arrayResult.add(arrayForState.get(i-2));
                            arrayResult.add(arrayForState.get(i-1));
                            arrayResult.add(arrayForState.get(i));
                            break;

                        }
                    }
                }
            }

        }

        if (arrayResult.size() == 0) {
            return null;
        }
        else {
            return arrayResult;
        }
    }

    
    /***************************************************************
     * Helper method to return an array of the top ten deaths on a 
     * specific month and day
     ***************************************************************/ 
    public ArrayList <CovidEntry> topTenDeaths(int m, int d) {
        ArrayList<CovidEntry> arrayDayResult;
        ArrayList<CovidEntry> arrayFinalResult;

        arrayDayResult = new ArrayList<CovidEntry>();
        arrayFinalResult = new ArrayList<CovidEntry>();

        for (CovidEntry entry : entryArray) {
            if (entry.getMonth() == m && entry.getDay() == d) {
                arrayDayResult.add(entry);
            }
        }
        //System.out.println(arrayDayResult);

        Collections.sort(arrayDayResult);
        //only copy to finally array if the first array isn't empty
        if (! arrayDayResult.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                arrayFinalResult.add(arrayDayResult.get(i));

            }
        }

        return arrayFinalResult;
    }
}
