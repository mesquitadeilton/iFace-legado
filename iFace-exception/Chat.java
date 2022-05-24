import java.util.*;

public class Chat {
    private User[] users = new User[2];
    private ArrayList<Message> messages;

    public Chat(User[] users) {
        this.users = users;
        
        messages = new ArrayList<Message>();
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void setMessage(Message message) {
        this.messages.add(message);
    }

    public User[] getUsers() {
        return users;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}