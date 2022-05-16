package be.kuleuven.sports_reservation;

public class TimeSlot {
    private String beginTime;
    private String endTime;
    private String courtName;
    private String image;

    public TimeSlot(String beginTime, String endTime, String courtName, String image) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.courtName = courtName;
        this.image = image;
    }

    public TimeSlot(String beginTime, String endTime, String courtName) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.courtName = courtName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCourtName() {
        return courtName;
    }

    public String getImage() {
        return image;
    }
}
