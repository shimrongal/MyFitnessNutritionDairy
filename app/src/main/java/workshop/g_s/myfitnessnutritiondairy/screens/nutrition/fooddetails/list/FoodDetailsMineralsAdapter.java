package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.GoToBrowser;
import workshop.g_s.myfitnessnutritiondairy.entities.Mineral;

public class FoodDetailsMineralsAdapter extends RecyclerView.Adapter<FoodDetailsMineralViewHolder> {

    private final Context context;
    private List<Mineral> minerals;
    private GoToBrowser goToBrowser;

    public FoodDetailsMineralsAdapter(List<Mineral> minerals, Context context, GoToBrowser goToBrowser) {
        this.minerals = minerals;
        this.context = context;
        this.goToBrowser = goToBrowser;

    }

    @NonNull
    @Override
    public FoodDetailsMineralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflateFoodDetailsMineralsList = LayoutInflater.from(context).inflate(R.layout.layout_food_details_minerals_row, parent, false);
        return new FoodDetailsMineralViewHolder(inflateFoodDetailsMineralsList, goToBrowser);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDetailsMineralViewHolder holder, int position) {
        Mineral mineral = minerals.get(position);
        if (mineral != null) {
            holder.bind(mineral);
        }
    }

    @Override
    public int getItemCount() {
        return minerals.size();
    }
}
