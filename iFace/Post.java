public class Post extends Message {
    private boolean visibility = true;

    public Post(User sender, String text, String date) {
        super(sender, text, date);
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}