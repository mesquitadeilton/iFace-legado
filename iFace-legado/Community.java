public class Community {
    private User creator;
    private String name;
    private String description;

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return this.creator;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }
}