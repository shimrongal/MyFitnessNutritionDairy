package workshop.g_s.myfitnessnutritiondairy.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Mineral")
public class Mineral {
    @PrimaryKey
    @NonNull
    @SerializedName("mineralName")
    @Expose
    private String mineralName;
    @SerializedName("mineralAmount")
    @Expose
    private Float mineralAmount;
    @SerializedName("foodId")
    @Expose
    private long foodId;

    public Mineral(long foodId, @NonNull String mineralName, Float mineralAmount) {
        this.foodId = foodId;
        this.mineralName = mineralName;
        this.mineralAmount = mineralAmount;
    }

    public Float getMineralAmount() {
        return mineralAmount;
    }

    public long getFoodId() {
        return foodId;
    }

    @NonNull
    public String getMineralName() {
        return mineralName;
    }
}
