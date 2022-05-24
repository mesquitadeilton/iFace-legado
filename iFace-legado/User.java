import java.util.*;

public class User {
    private int id;
    private boolean status;

    private String name;
    private String lastName;
    private String login;
    private String password;

    private ArrayList<User> invitations;
    private ArrayList<User> friends;
    private ArrayList<Message> chat;
    private ArrayList<String> feed;
    private ArrayList<Community> communities;

    public User() {
        Random number = new Random();

        this.id = number.nextInt(2001) + 1001;
        this.status = true;
        this.invitations = new ArrayList<User>();
        this.friends = new ArrayList<User>();
        this.chat = new ArrayList<Message>();
        this.feed = new ArrayList<String>();
        this.communities = new ArrayList<Community>();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInvitation(User user) {
        invitations.add(user);
    }

    public void setFriend(User user) {
        friends.add(user);
    }

    public void setMessage(User user, String message) {
        Message temp = new Message();
        temp.setSender(user);
        temp.setMessage(message);
        chat.add(temp);
    }

    public void setFeed(String post) {
        feed.add(post);
    }

    public void setComunity(User creator, String name, String description) {
        Community temp = new Community();
        temp.setCreator(creator);
        temp.setName(name);
        temp.setDescription(description);
        communities.add(temp);
    }

    public void setComunities(Community community) {
        communities.add(community);
    }

    public int getId() {
            return id;
    }

    public boolean getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getInvitations() {
        if(invitations.isEmpty())
            return  false;
        else 
            return true;
    }

    public void getInvitationsName() {
        System.out.println("SOLICITAÇÃO DE:");

        int i = 0;
        for(User user : invitations) {
            if(invitations.get(i).getStatus()) {
                System.out.printf("|%d| %s\n", i+1, invitations.get(i).getLogin());
                i++;
            }
        }
        System.out.println();
    }

    public User getInvitation(int i) {
        return invitations.get(i);
    }

    public void getFriendsName() {
        if(friends.isEmpty()) {
            System.out.println("Você ainda não tem amigos");
        }
        else {
            int i = 0;
            for(User user : friends) {
                if(friends.get(i).getStatus()) {
                    System.out.print(friends.get(i).getName());
                    String temp = (friends.get(i).getLastName() == null) ? "" : friends.get(i).getLastName();
                    System.out.println(" "+temp);
                }
                else System.out.println("Você ainda não tem amigos");
                i++;
            }
        }
    }

    public void getChat() {
        if(chat.isEmpty()) {
            System.out.println("Sem mensagens");
        }
        else {
            int i = 0;
            for(Message message : chat) {
                System.out.print(chat.get(i).getSender().getName());
                String temp = (chat.get(i).getSender().getLastName() == null) ? "" : " "+chat.get(i).getSender().getLastName();
                System.out.print(temp);
                System.out.println(": "+chat.get(i).getMessage());
                i++;
            }
        }
        System.out.println();
    }

    public void getFeed() {
        if(feed.isEmpty()) {
            System.out.println("Sem publicações");
        }
        else {
            int i = 0;
            for(String string : feed) {
                System.out.println(feed.get(i));
                i++;
            }
        }
        System.out.println();
    }

    public void getComunititesName() {
        if(communities.isEmpty()) {
            System.out.println("Sem comunidades");
        }
        else {
            int i = 0;
            for(Community community : communities) {
                System.out.println(communities.get(i).getName());
                i++;
            }
        }
    }

    public void removeInvitation(int i) {
        invitations.remove(i);
    }

    public String getComunity(int i) {
        return this.communities.get(i).getName();
    }
}
