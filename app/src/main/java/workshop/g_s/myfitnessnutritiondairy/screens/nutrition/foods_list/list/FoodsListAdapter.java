package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.FoodCardClickCallback;
import workshop.g_s.myfitnessnutritiondairy.entities.FoodListEntity;

public class FoodsListAdapter extends RecyclerView.Adapter<FoodsViewHolder> {
    private final Context context;
    private final FoodCardClickCallback foodCardClickCallback;
    private ArrayList<FoodListEntity> foodArrayList;

    public FoodsListAdapter(Context context, FoodCardClickCallback foodCardClickCallback, ArrayList<FoodListEntity> foodArrayList) {
        this.context = context;
        this.foodCardClickCallback = foodCardClickCallback;
        this.foodArrayList = foodArrayList;
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflateFoodsListLayout = LayoutInflater.from(context).inflate(R.layout.layout_list_row_foods, parent, false);
        return new FoodsViewHolder(inflateFoodsListLayout, context, foodCardClickCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        holder.bind(foodArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public void updateFoodsList(List<FoodListEntity> foodList) {
        foodArrayList.clear();
        foodArrayList.addAll(foodList);
        notifyDataSetChanged();
    }

    public long getCurrentFoodId(int adapterPosition) {
        return foodArrayList.get(adapterPosition).getFoodId();
    }
}
