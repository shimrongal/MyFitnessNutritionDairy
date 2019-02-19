package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.FoodCardClickCallback;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.FoodListEntity;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.dagger.DaggerFoodsComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.list.FoodsListAdapter;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.RxSchedulers;

public class FoodsFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener, FoodCardClickCallback {

    public static final String TAG = "FoodsFragment";
    @BindView(R.id.rcvFoodsList)
    RecyclerView recyclerView;
    @Inject
    ApplicationRepository applicationRepository;
    @Inject
    RxSchedulers rxSchedulers;
    @Inject
    Coordinator coordinator;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Gson gson;
    private FoodsListAdapter foodsListAdapter;
    private Context context;
    private View inflateFragmentFoods;
    private Unbinder bindButterKnife;


    public static FoodsFragment getInstance() {
        FoodsFragment foodsFragment = new FoodsFragment();
        return foodsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        Log.e(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");

        initDagger();

        foodsListAdapter = new FoodsListAdapter(context, this, initFoodListForRecyclerView());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: ");
        bindButterKnife.unbind();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        inflateFragmentFoods = inflater.inflate(R.layout.layout_fragment_foods, container, false);
        return inflateFragmentFoods;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated");
        bindButterKnife = ButterKnife.bind(this, inflateFragmentFoods);
        initRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(foodsListAdapter);
    }

    private void initDagger() {
        DaggerFoodsComponent.builder().
                applicationComponent(App.getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    public void onFoodCardClick(int adapterPosition) {
        //TODO get specific food by is id and present it on next fragment
        coordinator.goToFoodDetailFragment(getActivity(), foodsListAdapter.getCurrentFoodId(adapterPosition));
    }

    private ArrayList<FoodListEntity> initFoodListForRecyclerView() {
        String strFromJsonFoodList = sharedPreferences.getString(Constants.FOOD_LIST_OBJECT, "");
        ArrayList<FoodListEntity> foodsListEntities = new ArrayList<>();
        if (!strFromJsonFoodList.isEmpty()) {
            List<Food> lstFoods = gson.fromJson(strFromJsonFoodList, new TypeToken<List<Food>>() {
            }.getType());

            for (int index = 0; index < lstFoods.size(); index++) {
                Food food = lstFoods.get(index);
                foodsListEntities.add(new FoodListEntity(food.getId(), food.getFoodName(), food.getFoodBrandName()));
            }
        }
        Log.e(TAG, "initFoodListForRecyclerView: foodsListEntities.size() : " + foodsListEntities.size());
        return foodsListEntities;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.e(TAG, "onSharedPreferenceChanged: ");
        if (key.equals(Constants.FOOD_LIST_OBJECT)) {
            foodsListAdapter.updateFoodsList(initFoodListForRecyclerView());
        }
    }
}
