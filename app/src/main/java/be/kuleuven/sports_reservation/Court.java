package be.kuleuven.sports_reservation;

import java.util.ArrayList;

public class Court {
    private String id;
    private String name;

    private static ArrayList<Court> courts = new ArrayList<>();

    public Court(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void initCourt(){
        courts.clear();

        Court court0 = new Court("0", "None");
        courts.add(court0);

        Court court1 = new Court("1", "Badminton1");
        courts.add(court1);

        Court court2 = new Court("2", "Badminton2");
        courts.add(court2);

        Court court3 = new Court("3", "Badminton3");
        courts.add(court3);
    }

    public int getImage(){
        switch (getId()){
            case "0"://todo change different image
                return 0;
            case "1":
                return R.drawable.badminton;
            case "2":
                return R.drawable.badminton;
            case "3":
                return R.drawable.badminton;
        }
        return R.drawable.badminton;
    }

    public static ArrayList<Court> getCourts() {
        return courts;
    }
}
