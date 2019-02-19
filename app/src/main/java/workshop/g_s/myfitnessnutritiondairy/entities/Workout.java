package workshop.g_s.myfitnessnutritiondairy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Workout")
public class Workout {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("durationInMinutes")
    @Expose
    private int durationInMinutes;
    @SerializedName("comments")
    @Expose
    private String comments;

    public Workout(int id, String name, int durationInMinutes, String comments) {
        this.id = id;
        this.name = name;
        this.durationInMinutes = durationInMinutes;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getComments() {
        return comments;
    }

}
