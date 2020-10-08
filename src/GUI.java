import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GUI implements ActionListener {
    GregorianCalendar gc;
    int days_To_Add;
    GregorianCalendar saved_changed_Date;
    InputValue iP = new InputValue();


    public GUI(GregorianCalendar gc) {
        this.gc = gc;
    }

    public void skipDay() {
        gc.add(Calendar.DAY_OF_MONTH, days_To_Add);
    }

    JFrame f = new JFrame("Main E-Voting Window");
    JPanel p1 = new JPanel(new BorderLayout());
    JPanel p2 = new JPanel(new BorderLayout());


    JTextField textField = new JTextField("0", 10);

    final JTextArea textArea = new JTextArea(18, 5);
    final JButton button = new JButton("Add days");
    final JButton button2 = new JButton("Vote");
    final JButton button3 = new JButton("Show Results");

    public void makeWindow() { // use this to add the main window//
        ReadFileBulletin readFileBulletin = new ReadFileBulletin();
        iP.readFile();
        readFileBulletin.openFile(
                "C://Users//Giannis//IdeaProjects//Voting_system//src//Voting testing data.txt");
        f.setSize(450, 400);
        f.setLocation(400, 300);

        p1.add(textArea, BorderLayout.CENTER);

        Date date = gc.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = dateFormat.format(date);
        JTextArea textArea2 = new JTextArea();

        textArea.setEditable(false);
        textArea2 = new JTextArea(formattedDate);
        p2.add(textArea2, BorderLayout.CENTER);
        textArea2.setEditable(false);
        p2.add(textField, BorderLayout.SOUTH);

        p2.add(button, BorderLayout.EAST);
        button.addActionListener(this);

        p2.add(button2, BorderLayout.LINE_START);
        p2.add(button3, BorderLayout.BEFORE_FIRST_LINE);

        button2.addActionListener(new ActionListener() { //vote button
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check_date = false;
                for (int i = 0; i < readFileBulletin.getIntervals_date_list().size(); i++) {
                    if (gc.getTime().equals(readFileBulletin.getIntervals_date_list().get(i).getTime())) { // here we
                        // check if
                        // the
                        // current
                        // date is
                        // valid for
                        // voting
                        check_date = true;
                    }
                }
                if (check_date == true) {
                    iP.callVoteInterface();

                } else {
                    textArea.append("The voting period has not yet started.\n"); // we show the message if current date
                    // is not valid for voting
                }
            }
        });

        button3.addActionListener(new ActionListener() { //show results button
            @Override
            public void actionPerformed(ActionEvent e) {
                makeResultsWindow();
            }
        });
        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.SOUTH);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {  //add days to the date button
        String days = textField.getText();
        try {
            days_To_Add = Integer.parseInt(days);
        } catch (NumberFormatException i) {
            textArea.append("Please enter a number\n");
        }

        textArea.append(days_To_Add + " days added\n");
        skipDay();
        saved_changed_Date = gc;
        Date date2 = gc.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = dateFormat.format(date2);
        JTextArea textArea2 = new JTextArea(formattedDate);
        p2.add(textArea2, BorderLayout.CENTER);
    }

    CalculateResults calculateResults = new CalculateResults();

    public void makeResultsWindow() {  //this is a window where the winner and the total votes he got are shown
        JFrame resultsFrame = new JFrame("Results");
        resultsFrame.setSize(300, 300);
        resultsFrame.setLocation(500, 200);
        JPanel resultPanel = new JPanel(new BorderLayout());
        final JButton button4 = new JButton("Back");
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultPanel.add(resultTextArea, BorderLayout.CENTER);
        calculateResults.calculateWinner();
        for (int i = 0; i < calculateResults.getUnsortedVotes().size(); i++) {
            resultTextArea.append(calculateResults.getUnsortedCanditates().get(i) + calculateResults.getUnsortedVotes().get(i) + "\n");
        }
        resultPanel.add(button4, BorderLayout.SOUTH);
        resultsFrame.add(resultPanel, BorderLayout.CENTER);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultTextArea.setText("");
                resultsFrame.dispatchEvent(new WindowEvent(resultsFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        resultsFrame.setVisible(true);
    }
}
