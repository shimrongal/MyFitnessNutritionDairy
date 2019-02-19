package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.GoToBrowser;
import workshop.g_s.myfitnessnutritiondairy.entities.Mineral;

public class FoodDetailsMineralViewHolder extends RecyclerView.ViewHolder {

    private final View itemView;
    private final GoToBrowser goToBrowser;
    @BindView(R.id.tvFoodDetailsMineralName)
    TextView tvFoodDetailsMineralName;

    FoodDetailsMineralViewHolder(View itemView, GoToBrowser goToBrowser) {
        super(itemView);
        this.itemView = itemView;
        this.goToBrowser = goToBrowser;
        ButterKnife.bind(this, itemView);

    }

    public void bind(Mineral mineral) {
        String[] splitMineralText = mineral.getMineralName().split("_");
        if (splitMineralText.length == 1) {
            splitMineralText = new String[]{splitMineralText[0],
                    itemView.getContext().getResources().getString(R.string.gram)};
        }
        String text = splitMineralText[0] + ": " + mineral.getMineralAmount() + splitMineralText[1];
        tvFoodDetailsMineralName.setText(text);
    }

    @OnClick(R.id.tvFoodDetailsMineralName)
    void onClickMineralName() {
        String strForSearch = tvFoodDetailsMineralName.getText().toString();
        String[] splitStrForSearch = strForSearch.split(":");
        goToBrowser.searchStringInGoogle(splitStrForSearch[0]);
    }
}
