public class Message {
    private User sender;
    private String text;
    private String date;

    public Message(User sender, String text, String date) {
        this.sender = sender;
        this.text = text;
        this.date = date;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}