import java.util.ArrayList;
import java.util.Collections;

public class CalculateResults {
    ReadFileBulletin readFileBulletin = new ReadFileBulletin();

    int sum = 0;
    int check = 0;
    ArrayList<Integer> maxVotes = new ArrayList<>();
    ArrayList<String> nameOfwinner = new ArrayList<>();


    public void calculateWinner() {
        if (check == 0) {
            readFileBulletin.openFile("C://Users//Giannis//IdeaProjects//Voting_system//src//Voting testing data.txt");
            check += 1;
        }
        readFileBulletin.getCandidate_list();

        for (int i = 0; i < readFileBulletin.getCandidate_list().size(); i++) {
            sum = readFileBulletin.getCandidate_list().get(i).getVotes_of_interval().get(i * 3).getVotes() + readFileBulletin.getCandidate_list().get(i).getVotes_of_interval().get(i * 3 + 1).getVotes() + readFileBulletin.getCandidate_list().get(i).getVotes_of_interval().get(i * 3 + 2).getVotes();
            maxVotes.add(sum);
            nameOfwinner.add(readFileBulletin.getCandidate_list().get(i).getName() + " " + readFileBulletin.getCandidate_list().get(i).getSurname());
            System.out.println(nameOfwinner.get(i) + sum);
            sum = 0;
        }
        System.out.println(nameOfwinner + " " + maxVotes);
    }

    // public void sort(){
    //   Collections.sort(maxVotes);
    //}


    public ArrayList<Integer> getUnsortedVotes() {
        return maxVotes;
    }

    public ArrayList<String> getUnsortedCanditates() {
        return nameOfwinner;
    }

    public void setUnsortedVotes(ArrayList<Integer> maxVotes) {
        this.maxVotes = maxVotes;
    }

    public int getWinnerVotes() {
        //  sort();
        return maxVotes.get(0);
    }

    public String getWinnerName() {
        return nameOfwinner.get(0);
    }

}