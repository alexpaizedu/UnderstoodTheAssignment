public class AvailableBlock {
    private String date;
    private String from;
    private String to;

    public AvailableBlock(String date, String from, String to) {
        this.date = date;
        this.from = from;
        this.to = to;
    }

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
