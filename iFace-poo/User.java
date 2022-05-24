import java.util.*;

public class User extends People implements Interface1 {
    private ArrayList<User> friends;
    private ArrayList<User> invitations;

    private ArrayList<Chat> chats;
    
    private ArrayList<Community> communities;
    private ArrayList<Community> myCommunities;

    private ArrayList<Post> posts;

    public User(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassowrd(password);

        friends = new ArrayList<User>();
        invitations = new ArrayList<User>();

        chats = new ArrayList<Chat>();
        
        communities = new ArrayList<Community>();
        myCommunities = new ArrayList<Community>();

        posts = new ArrayList<Post>();
    }

    public void setFriend(User friend) {
        this.friends.add(friend);
    }

    public void setInvitation(User user) {
        this.invitations.add(user);
    }

    public void setChat(Chat chat) {
        this.chats.add(chat);
    }

    public void setComunity(Community community) {
        this.communities.add(community);
    }

    public void setMyComunity(Community community) {
        this.myCommunities.add(community);
    }

    public void setPost(Post post) {
        this.posts.add(post);
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getInvitations() {
        return invitations;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public ArrayList<Community> getCommunities() {
        return communities;
    }

    public ArrayList<Community> getMyCommunities() {
        return myCommunities;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    @Override
    public String getKey() {
        return getEmail();
    }
}