package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.core;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.GoToBrowser;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Mineral;
import workshop.g_s.myfitnessnutritiondairy.entities.Vitamin;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.dagger.DaggerFoodDetailsComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.list.FoodDetailsMineralsAdapter;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.list.FoodDetailsVitaminsAdapter;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailsFragment extends Fragment implements GoToBrowser {
    @BindView(R.id.tvFoodDetailsFoodName)
    TextView tvFoodName;
    @BindView(R.id.tvFoodDetailsFoodBrandName)
    TextView tvFoodBrandName;
    //TODO: figure out serving size methods
    @BindView(R.id.tvServingSize)
    TextView tvServingSize;
    @BindView(R.id.tvCaloriesAmount)
    TextView tvCaloriesAmount;
    @BindView(R.id.tvProteinAmount)
    TextView tvProteinAmount;
    @BindView(R.id.tvTotalCarbohydrates)
    TextView tvTotalCarbohydrates;
    @BindView(R.id.tvDiatryFiber)
    TextView tvDiatryFiber;
    @BindView(R.id.tvSugars)
    TextView tvSugars;
    @BindView(R.id.tvTotalFat)
    TextView tvTotalFat;
    @BindView(R.id.tvFatSaturated)
    TextView tvFatSaturated;
    @BindView(R.id.tvFatTrans)
    TextView tvFatTrans;
    @BindView(R.id.tvFatPolyunsaturated)
    TextView tvFatPolyunsaturated;
    @BindView(R.id.tvFatMonounsaturated)
    TextView tvFatMonounsaturated;
    @BindView(R.id.tvCholesterol)
    TextView tvCholesterol;
    @BindView(R.id.rcvVitamins)
    RecyclerView recyclerViewVitamins;
    @BindView(R.id.rcvMinerales)
    RecyclerView recyclerViewMinerales;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Gson gson;
    @Inject
    Coordinator coordinator;
    @Inject
    FoodDetailsViewModelFactory foodDetailsViewModelFactory;
    Unbinder butterKnife;
    private Context context;
    private FoodDetailsViewModel foodDetailsViewModel;

    public static FoodDetailsFragment getInstance(long foodId) {
        FoodDetailsFragment foodDetailsFragment = new FoodDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.FOOD_ID, foodId);
        foodDetailsFragment.setArguments(bundle);
        return foodDetailsFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFoodDetailsComponent.builder().applicationComponent(App.getApplicationComponent())
                .build()
                .inject(this);
        foodDetailsViewModel = ViewModelProviders.of(this, foodDetailsViewModelFactory).get(FoodDetailsViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflateFoodDetailsFragment = inflater.inflate(R.layout.layout_fragment_food_details, container, false);
        butterKnife = ButterKnife.bind(this, inflateFoodDetailsFragment);
        return inflateFoodDetailsFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
    }


    private void initUi() {
        Food currentFood = foodDetailsViewModel.getCurrentFood(getArguments().getLong(Constants.FOOD_ID), sharedPreferences.getString(Constants.FOOD_LIST_OBJECT, ""));
        if (currentFood != null) {
            tvFoodName.setText(currentFood.getFoodName());
            tvFoodBrandName.setText(currentFood.getFoodBrandName());
            tvCaloriesAmount.setText(getString(R.string.calories_details, foodDetailsViewModel.formatFloatToString(currentFood.getCalories())));
            tvServingSize.setText(currentFood.getServingSize());
            tvProteinAmount.setText(getString(R.string.protein_details, foodDetailsViewModel.formatFloatToString(currentFood.getProteins())));
            tvTotalCarbohydrates.setText(getString(R.string.total_carbohydrates_details, foodDetailsViewModel.formatFloatToString(currentFood.getTotalCarbohydrates())));
            tvDiatryFiber.setText(getString(R.string.diatry_fiber_details, foodDetailsViewModel.formatFloatToString(currentFood.getDiatryFiber())));
            tvSugars.setText(getString(R.string.sugars_details, foodDetailsViewModel.formatFloatToString(currentFood.getSuger())));

            tvTotalFat.setText(getString(R.string.total_fat_details, foodDetailsViewModel.formatFloatToString(currentFood.getTotalFats())));
            tvFatSaturated.setText(getString(R.string.saturated_fat_details, foodDetailsViewModel.formatFloatToString(currentFood.getSaturated())));
            tvFatTrans.setText(getString(R.string.trans_fat_details, foodDetailsViewModel.formatFloatToString(currentFood.getTrans())));
            tvFatPolyunsaturated.setText(getString(R.string.polyunsaturated_fat_details, foodDetailsViewModel.formatFloatToString(currentFood.getPolyunsaturated())));
            tvFatMonounsaturated.setText(getString(R.string.monounsaturated_fat_details, foodDetailsViewModel.formatFloatToString(currentFood.getMonounsaturated())));
            tvCholesterol.setText(getString(R.string.cholesterol_details, foodDetailsViewModel.formatFloatToString(currentFood.getCholesterol())));

            String strListVitamins = "[" + currentFood.getStrListVitamins() + "]";
            if (!strListVitamins.isEmpty()) {
                LinearLayoutManager linearLayoutManagerVitamins = new LinearLayoutManager(context);
                DividerItemDecoration dividerItemDecorationVitamins = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

                List<Vitamin> vitamins = gson.fromJson(strListVitamins, new TypeToken<List<Vitamin>>() {
                }.getType());
                recyclerViewVitamins.setHasFixedSize(true);
                recyclerViewVitamins.setLayoutManager(linearLayoutManagerVitamins);
                recyclerViewVitamins.addItemDecoration(dividerItemDecorationVitamins);
                recyclerViewVitamins.setAdapter(new FoodDetailsVitaminsAdapter(vitamins, this));
            } else {
                recyclerViewVitamins.setVisibility(View.INVISIBLE);
            }
            String strListMinerals = "[" + currentFood.getStrListMinerals() + "]";
            if (!strListMinerals.isEmpty()) {
                List<Mineral> minerals = gson.fromJson(strListMinerals, new TypeToken<List<Mineral>>() {
                }.getType());
                LinearLayoutManager linearLayoutManagerMinerals = new LinearLayoutManager(context);
                DividerItemDecoration dividerItemDecorationMinerals = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                recyclerViewMinerales.setHasFixedSize(true);
                recyclerViewMinerales.setLayoutManager(linearLayoutManagerMinerals);
                recyclerViewMinerales.addItemDecoration(dividerItemDecorationMinerals);
                recyclerViewMinerales.setAdapter(new FoodDetailsMineralsAdapter(minerals, context, this));
            } else {
                recyclerViewMinerales.setVisibility(View.INVISIBLE);
            }

        }
    }


    @OnClick(R.id.tvFoodDetailsFoodName)
    public void nameCLick(TextView foodName) {
        coordinator.searchStringInGoogleEngine(context, foodName.getText().toString());
    }

    @OnClick(R.id.tvFoodDetailsFoodBrandName)
    public void brandNameCLick(TextView tvFoodBrandName) {
        coordinator.searchStringInGoogleEngine(context, tvFoodBrandName.getText().toString());
    }


    public void searchStringInGoogle(String strForSearch) {
        coordinator.goToWebBrowser(context, strForSearch);
    }
}
