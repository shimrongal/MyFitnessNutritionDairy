package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.GoToBrowser;
import workshop.g_s.myfitnessnutritiondairy.entities.Vitamin;

class FoodDetailsVitaminsViewHolder extends RecyclerView.ViewHolder {

    private final GoToBrowser goToBrowser;
    private final View itemView;
    @BindView(R.id.tvFoodDetailsVitaminNameAndAmount)
    TextView tvNameListRowFoodDetails;


    FoodDetailsVitaminsViewHolder(View itemView, GoToBrowser goToBrowser) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        this.goToBrowser = goToBrowser;
    }

    void bind(Vitamin vitamin) {
        String[] splitVitaminText = vitamin.getNameVitamin().split("_");
        if (splitVitaminText[1].length() == 1) {
            splitVitaminText = new String[]{splitVitaminText[0],
                    itemView.getContext().getResources().getString(R.string.gram)};
        }
        String text = splitVitaminText[0] + ": " + vitamin.getAmountVitmin().toString() + splitVitaminText[1];
        tvNameListRowFoodDetails.setText(text);
    }

    @OnClick(R.id.tvFoodDetailsVitaminNameAndAmount)
    void onClickVitaminName() {
        //TODO check internet connection
        String strForSearch = tvNameListRowFoodDetails.getText().toString();
        String[] splitTextViewText = strForSearch.split(":");
        goToBrowser.searchStringInGoogle(splitTextViewText[0]);
    }
}
