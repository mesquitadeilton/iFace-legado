import java.util.*;

public class Community implements Interface1 {
    private User creator;
    private String name;
    private String description;
    
    private ArrayList<User> members;
    private ArrayList<User> invitations;

    private ArrayList<Post> posts;

    public Community(User creator, String name, String description) {
        this.creator = creator;
        this.name = name;
        this.description = description;
        
        members = new ArrayList<User>();
        invitations = new ArrayList<User>();
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMember(User member) {
        this.members.add(member);
    }

    public void setInvitation(User user) {
        this.invitations.add(user);
    }

    public void setPost(Post post) {
        this.posts.add(post);
    }

    public User getCreator() {
        return creator;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public ArrayList<User> getInvitations() {
        return invitations;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    @Override
    public String getKey() {
        return name;
    }
}