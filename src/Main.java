import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args){
        ReadFileBulletin readFileBulletin = new ReadFileBulletin();
        readFileBulletin.openFile("C://Users//Giannis//IdeaProjects//Voting_system//src/Voting testing data.txt");
        GregorianCalendar startingDate= (readFileBulletin.getSystemStartDate());

        GUI gui=new GUI(startingDate);
        gui.makeWindow();
    }
}
