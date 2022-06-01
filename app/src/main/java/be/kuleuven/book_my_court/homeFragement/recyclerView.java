package be.kuleuven.book_my_court.homeFragement;

public class recyclerView {
    private int court;
    private String title, description, openHour;

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

    public String getOpenHour(){return openHour;}

    public void setOpenHour(String openHour){this.openHour = openHour;}

    recyclerView(int court, String title, String description, String openHour){
        this.court = court;
        this.title = title;
        this.description = description;
        this.openHour = openHour;
    }
}