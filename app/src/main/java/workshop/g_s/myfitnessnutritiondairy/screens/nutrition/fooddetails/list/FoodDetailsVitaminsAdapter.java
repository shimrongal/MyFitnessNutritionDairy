package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.GoToBrowser;
import workshop.g_s.myfitnessnutritiondairy.entities.Vitamin;

public class FoodDetailsVitaminsAdapter extends RecyclerView.Adapter<FoodDetailsVitaminsViewHolder> {

    private final GoToBrowser goToBrowser;
    private List<Vitamin> vitamins;

    public FoodDetailsVitaminsAdapter(List<Vitamin> vitamins, GoToBrowser goToBrowser) {
        this.vitamins = vitamins;
        this.goToBrowser = goToBrowser;
    }


    @NonNull
    @Override
    public FoodDetailsVitaminsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflateFoodVitaminsDetailsViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food_details_vitamins_row, parent, false);
        return new FoodDetailsVitaminsViewHolder(inflateFoodVitaminsDetailsViewHolder, goToBrowser);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDetailsVitaminsViewHolder holder, int position) {
        Vitamin vitamin = vitamins.get(position);
        if (vitamin != null) {
            holder.bind(vitamin);
        }
    }

    @Override
    public int getItemCount() {

        return vitamins.size();
    }

    public void updateList(ArrayList<Pair> pairs) {
        notifyDataSetChanged();

    }
}
