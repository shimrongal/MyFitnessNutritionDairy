package workshop.g_s.myfitnessnutritiondairy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Vitamin")
public class Vitamin {

    @SerializedName("foodId")
    @Expose
    private long foodId;

    @SerializedName("amountVitmin")
    @Expose
    private Float amountVitmin;

    @PrimaryKey
    @NonNull
    @SerializedName("nameVitamin")
    @Expose
    private String nameVitamin;

    public Vitamin(long foodId, @NonNull String nameVitamin, Float amountVitmin) {
        this.nameVitamin = nameVitamin;
        this.amountVitmin = amountVitmin;
        this.foodId = foodId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public Float getAmountVitmin() {
        return amountVitmin;
    }

    @NonNull
    public String getNameVitamin() {
        return nameVitamin;
    }
}
