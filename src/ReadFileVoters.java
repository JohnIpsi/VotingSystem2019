import java.io.*;
import java.util.ArrayList;

public class ReadFileVoters {

    static ArrayList<Voter> voter_list = new ArrayList<>(); // in this list we save all the voter objects
    ArrayList<String> stringList = new ArrayList<>(); // in this list we save all the info about the voters before
                                                        // editing them to separate the specific infos

    public void openFile(String path) {
        int counter = 0;
        File f = null;
        BufferedReader reader = null;
        String line;

        try {
            f = new File(path);
        } catch (NullPointerException e) { // we check if the is such file
            System.err.println("File not found.");
        }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }

        try {
            line = reader.readLine(); // we start reading the file and save each line to a string
            counter++;
            while (!line.trim().equalsIgnoreCase("END")) { // END is the keyword to stop reading the txt file
                if (line != null) {
                    stringList.add(line); // here we add the line of the text file we just read if it's valid
                    counter++;
                    line = reader.readLine(); // we go next line
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Line " + counter + ": Sudden end.");
        }

        String name = null;
        String surname = null;
        boolean voted = false;
        int id = 0;
        String contextID = null;
        String contextSur = null;
        String contextName = null;
        String contextVoted = null;

        for (int i = 0; i < stringList.size(); i++) {
            // here we will separate the info of each voter
            contextID = stringList.get(i).substring(0, 3);
            if (contextID.equalsIgnoreCase("ID:")) {
                id = Integer.parseInt(stringList.get(i).substring(4, 8)); // separating ID
            }

            contextSur = stringList.get(i).substring(9, 13);
            if (contextSur.equalsIgnoreCase("Sur:")) {
                surname = stringList.get(i).substring(14, 22); // separating surname
            }

            contextName = stringList.get(i).substring(22, 27);
            if (contextName.equalsIgnoreCase("Name:")) {
                name = stringList.get(i).substring(28, 36); // separating name
            }

            contextVoted = stringList.get(i).substring(36, 42);
            if (contextVoted.equalsIgnoreCase("Voted:")) {
                voted = Boolean.parseBoolean(stringList.get(i).substring(43, 47)); // separating if he voted
            }
            Voter voter = new Voter(voted, id, name, surname); // create a new voter with all the specific info
            voter_list.add(voter); // we add the voter to an arraylist where we save all the voter objects
        }
    }

    public void test() {
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println(i + " " + stringList.get(i));
            // System.out.println(i+ "" +stringList.get(i) + "yeah " +
            // voter_list.get(i).getId());
            // System.out.println(i+ "" +stringList.get(i) + "yeah" +
            // voter_list.get(i).getName());
            // System.out.println(i+ "" +stringList.get(i) + "yeah " +
            // voter_list.get(i).getSurname());
            // System.out.println(i+ "" +stringList.get(i) + "yeah " +
            // voter_list.get(i).getVoted());
        }
    }

    public static ArrayList<Voter> arrayVoters() {
        return voter_list;
    }
}
