public class Message extends Post {
    private User receiver;

    public Message(User sender, User receiver, String text) {
        super(sender, text);
        this.receiver = receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getReceiver() {
        return receiver;
    }
}