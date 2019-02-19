package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.FoodCardClickCallback;
import workshop.g_s.myfitnessnutritiondairy.entities.FoodListEntity;

class FoodsViewHolder extends RecyclerView.ViewHolder {

    private final View itemView;
    private final Context context;
    private final FoodCardClickCallback foodCardClickCallback;
    @BindView(R.id.ivFoodImage)
    ImageView ivFoodIcon;
    @BindView(R.id.tvFoodName)
    TextView tvFoodName;
    @BindView(R.id.tvFoodBrandName)
    TextView tvFoodBrandName;


    FoodsViewHolder(View itemView, Context context, FoodCardClickCallback foodCardClickCallback) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
        this.context = context;
        this.foodCardClickCallback = foodCardClickCallback;

    }

    public void bind(FoodListEntity currentFood) {
        String foodName = currentFood.getFoodName();
        tvFoodName.setText(foodName);
        String foodBrandName = currentFood.getFoodBrandName();
        tvFoodBrandName.setText(foodBrandName);
        String strFoodImageFileName = foodName.toLowerCase() + " " + foodBrandName.toLowerCase();
        String replace = strFoodImageFileName.replace(" ", "_");
        Glide.with(itemView)
                .load(getImageByStringName(replace))
                .into(ivFoodIcon);
    }

    private int getImageByStringName(String strImageName) {
        return context.getResources().getIdentifier(strImageName, "drawable", context.getPackageName());
    }

    @OnClick(R.id.FoodsCardView)
    void onFoodsCardClick() {
        foodCardClickCallback.onFoodCardClick(getAdapterPosition());
    }
}
