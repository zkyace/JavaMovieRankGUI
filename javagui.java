import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Movie {
    private JFrame frame = new JFrame();
    private String rank;
    private String name;
    private String date;
    //movie class function
    Movie(String rank, String name, String date) {
        this.rank = rank;
        this.name = name;
        this.date = date;
    }
    //display the movie data
    public void display() {
        JOptionPane.showMessageDialog(frame, "Movie rank is: " + rank + "\nMovie name is: " + name + "\nDate Released is: " + date + "\n");
    }
    //compares the rank string with the rank name
    public boolean compareToRank(String rank){
        if (rank.equals(this.rank)){
            return true;
        }
    return false;
    }
    //compares the name string with the class name
    public boolean compareToName(String name){
        if (name.equals(this.name)){
            return true;
        }
    return false;
    }
    //compares the date string with the class date
    public boolean compareToDate(String date){
        if (date.equals(this.date)){
            return true;
        }
    return false;
    }
}

class TopMovies implements ActionListener{
    private JLabel label = new JLabel("Welcome to the top 100 movies!");
    private JLabel empty1 = new JLabel("");
    private JLabel empty2 = new JLabel("");
    private JLabel empty3 = new JLabel("");
    private JLabel empty4 = new JLabel("");
    private JLabel format1 = new JLabel("");
    private JLabel format2 = new JLabel("");
    private JLabel format3 = new JLabel("");
    private JLabel format4 = new JLabel("");
    private JLabel format5 = new JLabel("");
    private JFrame frame = new JFrame();
    JButton cdbutton = new JButton("Count down the top 100 movies.");
    JButton rankbutton = new JButton("Find movie by rank.");
    JButton namebutton = new JButton("Find movie by name.");
    JButton datebutton = new JButton("Find movie by date.");
    JButton endbutton = new JButton("End the program.");
    JButton Acceptbutton = new JButton("Find Movie");
    JTextField userText = new JTextField(100);
    Movie[] arr;
    private int size = 0;
    private int counter = 0;
    private String []tempfile = new String[100];
    private String []tempdata = new String[100];
    private String []data = new String[300];
    private int tracker;
    public TopMovies()throws IOException{
        arr = new Movie[100];
        File inFile = new File("C:\\Users\\vic_2\\Documents\\GitHub\\JavaMovieRankGUI\\inputfile.txt");
        //trys to read the file if file cant be opened then throw error
        try (Scanner in = new Scanner(inFile)) {
            while(in.hasNextLine()){
                //stores the data onto tempfile array
                tempfile[counter] = in.nextLine();
                counter++;
            }
        }
        //catches the exception if there is a error
        catch(IOException e) {
            System.out.println("Error reading from text.txt");
        }
        counter = 0;
        size = tempfile.length;
        //splits the strings into smaller strings separating rank, name, and date
        for(int i = 0,c = 0; i < size;i++, c++){
            tempdata = tempfile[i].split(",");
            for(int x = 0; x < tempdata.length;x++){
                data[i + x + c] = tempdata[x];
            }
            c++;
        }
        tracker = arr.length;
        //inputs the array data into Movie Class
        for(int i = 0, x = 0; x < tracker; i++, x++){
            try{
                arr[x] = new Movie(data[i],data[i + 1],data[i + 2]);
                i += 2;
                } catch (Exception e) {
                    System.out.println("Error");
                }
        }
        cdbutton.addActionListener(this);
        rankbutton.addActionListener(this);
        namebutton.addActionListener(this);
        datebutton.addActionListener(this);
        endbutton.addActionListener(this);
        Acceptbutton.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
        panel.setLayout(new GridLayout(3,5));
        panel.add(empty1);
        panel.add(empty2);
        panel.add(label);
        panel.add(empty3);
        panel.add(empty4);
        panel.add(format1);
        panel.add(format2);
        panel.add(format3);
        panel.add(format4);
        panel.add(format5);
        panel.add(cdbutton);
        panel.add(rankbutton);
        panel.add(namebutton);
        panel.add(datebutton);
        panel.add(endbutton);
        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Top 100 Movies!");
        frame.pack();
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(cdbutton.getModel().isArmed()){
            moviecount(arr,tracker - 1);
        }
        if(rankbutton.getModel().isArmed()){
            String user = JOptionPane.showInputDialog("Please enter the rank number to find the corisponding movies rank within 1-100.");
            for(int i = 0; i < arr.length; i++){
                //compares the string if it matches then display the corrisponding data
                if(arr[i].compareToRank(user)){
                    arr[i].display();
                }
            }
        }
        if(namebutton.getModel().isArmed()){
            String user = JOptionPane.showInputDialog("Please enter the movie name to find the corisponding movies rank.");
            for(int i = 0; i < arr.length; i++){
                //compares the string if it matches then display the corrisponding data
                if(arr[i].compareToName(user)){
                    arr[i].display();
                }

            }
        }
        if(datebutton.getModel().isArmed()){
            String user = JOptionPane.showInputDialog("Please enter the date of release to find the corisponding movies rank.");
            for(int i = 0; i < arr.length; i++){
                //compares the string if it matches then display the corrisponding data
                if(arr[i].compareToDate(user)){
                arr[i].display();
                }
            }
        }
        if(endbutton.getModel().isArmed()){
            System.exit(0);
        }
    }
    public static void moviecount(Movie[] data, int num){
        if(num != -1) {
            if(data[num] == null){
                return;
            } else {
                data[num].display();
                moviecount(data,num -1);
            }
        }
    }
    public static void main(String[] args)throws IOException{
        new TopMovies();
    }
}