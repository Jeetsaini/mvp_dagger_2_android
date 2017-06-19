package myapp.jeet.com.dagger2android.view;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import myapp.jeet.com.dagger2android.R;
import myapp.jeet.com.dagger2android.arch.RecipeDetailPresentar;
import myapp.jeet.com.dagger2android.di.Injectable;
import myapp.jeet.com.dagger2android.models.RecipeDetailItem;

/**
 * Created by Admin on 6/19/2017.
 */

public class RecipeDetailFragment extends LifecycleFragment implements Injectable,RecipeDetailPresentar.DetailView{
	 private android.support.v4.app.Fragment mRecipeFragment;

	 @Inject
	 RecipeDetailPresentar mRecipeDetailPresentar;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.recipe_fragment,null);
    mRecipeFragment=getFragmentManager().findFragmentById(R.id.recipe_fragment);

    mRecipeDetailPresentar.getData("35120");



    return view;

	}

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void update(RecipeDetailItem recipeDetailItem) {

	}
}
