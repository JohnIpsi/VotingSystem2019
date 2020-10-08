import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class InputValue {
    ReadFileBulletin readFileBulletin = new ReadFileBulletin();
    String cand1;
    String cand2;
    String cand3;
    String cand4;
    String cand5;

    public String getValue(String info) {
        return JOptionPane.showInputDialog(info);
    }

    public int getConfirm(String info) {
        return JOptionPane.showConfirmDialog(null, info);
    }

    public void showMessage(String info) {
        JOptionPane.showMessageDialog(null, info);
    }

    public int getSelection(String info, String name, String[] options, String iniVal) {
        return JOptionPane.showOptionDialog(null, info, name, YES_NO_OPTION, INFORMATION_MESSAGE, null, options,
                iniVal);
    }

    ArrayList<Voter> object = new ArrayList<>();

    ReadFileVoters readfile = new ReadFileVoters();

    public void openFile2() {
        readFileBulletin.openFile(
                "/Users/danielfelipefrancomendez/eclipse-workspace/Voting_System_1.1/src/Voting testing data.txt");
        readFileBulletin.getCandidate_list();

        cand1 = readFileBulletin.getCandidate_list().get(0).getName() + " "
                + readFileBulletin.getCandidate_list().get(0).getSurname();
        cand2 = readFileBulletin.getCandidate_list().get(1).getName() + " "
                + readFileBulletin.getCandidate_list().get(1).getSurname();
        cand3 = readFileBulletin.getCandidate_list().get(2).getName() + " "
                + readFileBulletin.getCandidate_list().get(2).getSurname();
        cand4 = readFileBulletin.getCandidate_list().get(3).getName() + " "
                + readFileBulletin.getCandidate_list().get(3).getSurname();
        cand5 = readFileBulletin.getCandidate_list().get(4).getName() + " "
                + readFileBulletin.getCandidate_list().get(4).getSurname();
    }

    CalculateResults calculateResults = new CalculateResults();

    public void readFile() {
        readfile.openFile("C://Users//Giannis//IdeaProjects//Voting_system//src/Voters List.txt");
    }

    public void callVoteInterface() {

        ArrayList<Integer> plusOne = new ArrayList<>();
        readFileBulletin.openFile(
                "C://Users//Giannis//IdeaProjects//Voting_system//src/Voting testing data.txt");
        readFileBulletin.getCandidate_list();

        cand1 = readFileBulletin.getCandidate_list().get(0).getName() + " "
                + readFileBulletin.getCandidate_list().get(0).getSurname();
        cand2 = readFileBulletin.getCandidate_list().get(1).getName() + " "
                + readFileBulletin.getCandidate_list().get(1).getSurname();
        cand3 = readFileBulletin.getCandidate_list().get(2).getName() + " "
                + readFileBulletin.getCandidate_list().get(2).getSurname();
        cand4 = readFileBulletin.getCandidate_list().get(3).getName() + " "
                + readFileBulletin.getCandidate_list().get(3).getSurname();
        cand5 = readFileBulletin.getCandidate_list().get(4).getName() + " "
                + readFileBulletin.getCandidate_list().get(4).getSurname();

        InputValue f = new InputValue();
        String id = f.getValue("Please enter your ID");
        if (id == null) {
            f = null;
        }

        boolean check2 = false;

        while (check2 == false) {
            try {
                Integer.parseInt(id);
                check2 = true;
            } catch (NumberFormatException i) {
                f.showMessage("Enter only Numbers");
                id = f.getValue("Please enter your ID");
            }
        }

        System.out.println("ID: " + id);
        String surname = f.getValue("Please enter your Surname");
        if (surname == null) {
            f = null;
        }
        System.out.println("Surname: " + surname);
        String firstName = f.getValue("Please enter your first name");
        if (firstName == null) {
            f = null;
        }
        System.out.println("First name: " + firstName);
        boolean ableToVote = false;
        System.out.println(ReadFileVoters.arrayVoters().get(1).getName());
        System.out.println(ReadFileVoters.arrayVoters().get(1).getId());
        System.out.println(ReadFileVoters.arrayVoters().get(1).getSurname());
        System.out.println(ReadFileVoters.arrayVoters().get(1).getVoted());

        for (int i = 0; i < ReadFileVoters.arrayVoters().size(); i++) {
            if (ReadFileVoters.arrayVoters().get(i).getId() == Integer.parseInt(id)
                    && ReadFileVoters.arrayVoters().get(i).getName().replaceAll("\\s+", "").equalsIgnoreCase(firstName)
                    && ReadFileVoters.arrayVoters().get(i).getSurname().replaceAll("\\s+", "").equalsIgnoreCase(surname)
                    && ReadFileVoters.arrayVoters().get(i).getVoted() == false) {
                Voter nV = new Voter(true, Integer.parseInt(id), firstName, surname);
                object.add(nV);
                ableToVote = true;
            }
        }

        if (ableToVote == false) {
            f.showMessage("Sorry, you are not able to vote");
            f = null;
        } else {
            int sel = vote();
            int val = f.getConfirm("Are you sure?");
            System.out.println("Choice: no" + val);

            while (val == 1) {
                sel = vote();
                val = f.getConfirm("Are you sure?");
                System.out.println("Choice: yes" + val);
                for (int i = 0; i < ReadFileVoters.arrayVoters().size(); i++) {
                    for (int y = 0; y < object.size(); y++) {
                        if (ReadFileVoters.arrayVoters().get(i).getId() == object.get(y).getId()
                                && ReadFileVoters.arrayVoters().get(i).getName().replaceAll("\\s+", "")
                                .equalsIgnoreCase(object.get(y).getName())
                                && ReadFileVoters.arrayVoters().get(i).getSurname().replaceAll("\\s+", "")
                                .equalsIgnoreCase(object.get(y).getSurname())) {
                            ReadFileVoters.arrayVoters().get(i).setVoted(true);
                            System.out.println("reeee e" + ReadFileVoters.arrayVoters().get(i).getSurname() + "  fef"
                                    + ReadFileVoters.arrayVoters().get(i).getVoted());
                        }
                    }
                }
            }
            if (val == 2) {
                System.out.println("Voting cancelled");
                f = null;
            } else {
                if (sel == 0) {
                    System.out.println("Voted candidate: " + cand1);
                    for (int o = 0; o < readFileBulletin.getCandidate_list().size(); o++) {
                        //if(readFileBulletin.getCandidate_list().get(o).getSurname().replaceAll("\\s+", "").equalsIgnoreCase("Trump")){
                        // plusOne = calculateResults.getUnsortedVotes();
                        // plusOne.set(sel, plusOne.get(sel) + 1);
                        //calculateResults.setUnsortedVotes(plusOne);
                        //}
                    }
                } else if (sel == 1) {
                    System.out.println("Voted candidate: " + cand2);
                    for (int o = 0; o < readFileBulletin.getCandidate_list().size(); o++) {
                        //if(readFileBulletin.getCandidate_list().get(o).getSurname().replaceAll("\\s+", "").equalsIgnoreCase("Trump")){
                        //plusOne = calculateResults.getUnsortedVotes();
                        // plusOne.set(sel, plusOne.get(sel) + 1);
                        //calculateResults.setUnsortedVotes(plusOne);
                        //}
                    }
                } else if (sel == 2) {
                    System.out.println("Voted candidate: " + cand3);
                    for (int o = 0; o < readFileBulletin.getCandidate_list().size(); o++) {
                        //if(readFileBulletin.getCandidate_list().get(o).getSurname().replaceAll("\\s+", "").equalsIgnoreCase("Trump")){
                        // plusOne = calculateResults.getUnsortedVotes();
                        // plusOne.set(sel, plusOne.get(sel) + 1);
                        // calculateResults.setUnsortedVotes(plusOne);
                        //}
                    }
                } else if (sel == 3) {
                    System.out.println("Voted candidate: " + cand4);
                    for (int o = 0; o < readFileBulletin.getCandidate_list().size(); o++) {
                        //if(readFileBulletin.getCandidate_list().get(o).getSurname().replaceAll("\\s+", "").equalsIgnoreCase("Trump")){
                        // plusOne = calculateResults.getUnsortedVotes();
                        // plusOne.set(sel, plusOne.get(sel) + 1);
                        calculateResults.setUnsortedVotes(plusOne);
                        //}
                    }
                } else {
                    System.out.println("Voted candidate: " + cand5);
                    for (int o = 0; o < readFileBulletin.getCandidate_list().size(); o++) {
                        //if(readFileBulletin.getCandidate_list().get(o).getSurname().replaceAll("\\s+", "").equalsIgnoreCase("Trump")){
                        // plusOne = calculateResults.getUnsortedVotes();
                        // plusOne.set(sel, plusOne.get(sel) + 1);
                        // calculateResults.setUnsortedVotes(plusOne);
                        //}
                    }
                }
                f.showMessage("Thanks for voting!");
            }
        }

    }

    public int vote() {
        InputValue f = new InputValue();
        String[] a = {cand1, cand2, cand3, cand4, cand5};
        int sel = f.getSelection("Please select your desired candidate", "Choice", a, "C");
        return sel;
    }
}
