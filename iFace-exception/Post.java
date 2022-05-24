public class Post {
    private User sender;
    private String text;

    public Post(User sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }
}