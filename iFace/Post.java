public class Post extends Message {
    private boolean visibility = true;

    public Post(User sender, String text) {
        super(sender, text);
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}