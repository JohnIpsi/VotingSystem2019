import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReadFileBulletin {

    ArrayList<String> stringListCandidates = new ArrayList<>(); //here we save the lines of the file for candidates
    String string_total_votes = null;   //here we save the line of the file for total votes
    ArrayList<String> stringListIntervals = new ArrayList<>(); //here we save the lines of the file for date intervals

    ArrayList<Integer> intlistTotalVotes = new ArrayList<>();
    ArrayList<Candidate> candidateList = new ArrayList<>();
    ArrayList<DateInterval> dateIntervalList = new ArrayList<>();

    GregorianCalendar system_start_date = new GregorianCalendar();
    ArrayList<GregorianCalendar> intervals_date_list = new ArrayList<>();


    public void openFile(String path) {
        int counter = 0;
        File f = null;
        BufferedReader reader = null;
        String line;

        try {
            f = new File(path);
        } catch (NullPointerException e) {  //we check if the is such file
            System.err.println("File not found.");
        }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }

        try {
            line = reader.readLine();   //we start reading the file and save each line to a string
            counter++;
            while (!line.trim().equalsIgnoreCase("END_NAMES")) {  //END_NAMES is the keyword to stop reading the candidates
                if (line != null) {
                    stringListCandidates.add(line);  //here we add the line of the text file we just read if it's valid
                    counter++;
                    line = reader.readLine();   //we go next line
                }
            }

            while (!line.trim().equalsIgnoreCase("END_TOTAL_VOTES")) { //keyword to stop reading total votes
                string_total_votes = line;
                counter++;
                line = reader.readLine();   //we go next line
            }
            line = reader.readLine();

            while (!line.trim().equalsIgnoreCase("END_DATES")) {  //keyword to stop reading intervals and the file
                stringListIntervals.add(line);
                counter++;
                line = reader.readLine();    //we go next line
            }

        } catch (IOException | NullPointerException e) {
            System.out.println("Line " + counter + ": Sudden end.");
        }



        GregorianCalendar date;

        int votes_of_interval = 0;  //here we save temporary the votes of one candidate at one interval
        String name_candidate = null;
        String surname_candidate = null;

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

        for (int i = 0; i < stringListIntervals.size(); i++) {
            if (stringListIntervals.get(i).trim().substring(0, 17).equalsIgnoreCase("Voting intervals:")) {
                try {
                    GregorianCalendar interval1 = new GregorianCalendar();
                    GregorianCalendar interval2 = new GregorianCalendar();
                    GregorianCalendar interval3 = new GregorianCalendar();
                    Date date2 = df.parse(stringListIntervals.get(i).substring(19, 29)); //we separate the first interval
                    interval1.setTime(date2);
                    intervals_date_list.add(interval1);  //we add the interval to the gregorian list
                    date2 = df.parse(stringListIntervals.get(i).substring(30, 40)); //we separate the second interval
                    interval2.setTime(date2);
                    intervals_date_list.add(interval2);  //we add the interval to the gregorian list
                    date2 = df.parse(stringListIntervals.get(i).substring(41, 51));  //we separate the third interval
                    interval3.setTime(date2);
                    intervals_date_list.add(interval3);  //we add the interval to the gregorian list
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (stringListIntervals.get(i).trim().substring(0, 18).equalsIgnoreCase("System start date:")) {
                try {
                    Date date1 = df.parse(stringListIntervals.get(i).substring(19, 29));  //here we separate the system start date from the string
                    system_start_date.setTime(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < stringListCandidates.size(); i++) {
            name_candidate = stringListCandidates.get(i).trim().substring(0, 9);  //here we separate the name of the candidate from the string
            surname_candidate = stringListCandidates.get(i).trim().substring(10, 20); //here we separate the candidate surname from the string

            for (int j = 0; j < intervals_date_list.size(); j++) {
                if (j == 0) {
                    String help = stringListCandidates.get(i).substring(21, 25);
                    help = help.replaceAll("\\s", "");
                    votes_of_interval = Integer.parseInt(help);
                    DateInterval dateInterval1 = new DateInterval(intervals_date_list.get(j), votes_of_interval);
                    dateIntervalList.add(dateInterval1);
                } else if (j == 1) {
                    String help = stringListCandidates.get(i).substring(26, 30);
                    help = help.replaceAll("\\s", "");
                    votes_of_interval = Integer.parseInt(help);
                    DateInterval dateInterval2 = new DateInterval(intervals_date_list.get(j), votes_of_interval);
                    dateIntervalList.add(dateInterval2);
                } else if (j == 2) {
                    String help = stringListCandidates.get(i).substring(31, 35);
                    help = help.replaceAll("\\s", "");
                    votes_of_interval = Integer.parseInt(help);
                    DateInterval dateInterval3 = new DateInterval(intervals_date_list.get(j), votes_of_interval);
                    dateIntervalList.add(dateInterval3);
                }
            }

            Candidate candidate = new Candidate(name_candidate, surname_candidate, dateIntervalList); //here we create a new candidate object
            candidateList.add(candidate); //here we add each candidate object to the candidate arraylist

        }

        intlistTotalVotes.add(Integer.parseInt(string_total_votes.substring(21, 26)));  //in this 3 lines we separate and save the total votes of each interval
        intlistTotalVotes.add(Integer.parseInt(string_total_votes.substring(27, 32)));
        intlistTotalVotes.add(Integer.parseInt(string_total_votes.substring(33, 37)));

    }
    public ArrayList<Candidate> getCandidate_list(){
        return candidateList;
    }

    public GregorianCalendar getSystemStartDate() {  //returns the system starting date in gregorian calendar format(we need getTime method to get the date)
        return system_start_date;
    }

    public ArrayList<GregorianCalendar> getIntervals_date_list() {
        return intervals_date_list;
    }

    public void test() {
        for (int i = 0; i < stringListCandidates.size(); i++) {
            System.out.println(i + " " + stringListCandidates.get(i));
        }
        System.out.println(" " + string_total_votes);

        for (int i = 0; i < stringListIntervals.size(); i++) {
            System.out.println(i + " " + stringListIntervals.get(i));
        }
    }
}
