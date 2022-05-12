package be.kuleuven.sports_reservation;

public class recyclerView {
    private int court;
    private String title, description;

    public int getCourt() {
        return court;
    }

    public void setCourt(int court) {
        this.court = court;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    recyclerView(int court, String title, String description){
        this.court = court;
        this.title = title;
        this.description = description;
    }
}
