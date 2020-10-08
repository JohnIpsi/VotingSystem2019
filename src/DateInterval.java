import java.util.GregorianCalendar;

public class DateInterval {

    GregorianCalendar date;
    int votes;

    public DateInterval(GregorianCalendar date, int votes) {
        this.date = date;
        this.votes = votes;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setVotes(String name) {
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }
}
