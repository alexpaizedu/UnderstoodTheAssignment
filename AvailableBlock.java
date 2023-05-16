//this class contains the object that will be stored in each of the teachers' array list of avaliable schedules. The data parsed from json will be stored as objects of the teacer's array list.


public class AvailableBlock {
    
    //instance variables
    private String date;
    private String from;
    private String to;

    
    //constructor
    public AvailableBlock(String date, String from, String to) {
        this.date = date;
        this.from = from;
        this.to = to;
    }
    
    //getter methods
    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
