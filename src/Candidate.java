import java.util.ArrayList;

public class Candidate {
    String name;
    String surname;
    ArrayList<DateInterval> votes_of_interval;

    public Candidate(String name, String surname , ArrayList<DateInterval> votes_of_interval){
        this.name=name;
        this.surname=surname;
        this.votes_of_interval=votes_of_interval;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setSurname(String surname){
        this.surname=surname;
    }

    public String getSurname(){
        return surname;
    }

    public void setVotes_of_interval(ArrayList<DateInterval> votes_of_interval){ this.votes_of_interval=votes_of_interval; }

    public ArrayList<DateInterval> getVotes_of_interval(){
        return votes_of_interval;
    }
}
