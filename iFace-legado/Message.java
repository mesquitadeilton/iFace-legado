public class Message {
    private User sender;
    private String message;

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return this.sender;
    }

    public String getMessage() {
        return this.message;
    }
}