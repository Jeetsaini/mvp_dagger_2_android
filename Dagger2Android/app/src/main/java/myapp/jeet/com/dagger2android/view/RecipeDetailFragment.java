package myapp.jeet.com.dagger2android.view;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import myapp.jeet.com.dagger2android.R;
import myapp.jeet.com.dagger2android.arch.RecipeDetailPresentar;
import myapp.jeet.com.dagger2android.di.Injectable;
import myapp.jeet.com.dagger2android.models.RecipeDetail;
import myapp.jeet.com.dagger2android.models.RecipeDetailItem;

/**
 * Created by Admin on 6/19/2017.
 */

public class RecipeDetailFragment extends LifecycleFragment implements Injectable,RecipeDetailPresentar.DetailView{
	private android.support.v4.app.Fragment mRecipeFragment;

	@Inject
	RecipeDetailPresentar mRecipeDetailPresentar;
	private ImageView mRecipeImage;
	private TextView mRecipeName;
	private TextView mRecipeTitle;
	private ProgressBar mProgressBar;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.recipe_fragment,null);
		mRecipeImage=(ImageView)view.findViewById(R.id.imageView);
		mRecipeName=(TextView)view.findViewById(R.id.textView);
		mRecipeTitle=(TextView)view.findViewById(R.id.textView2);
		mProgressBar=(ProgressBar)view.findViewById(R.id.progressBar);
		mProgressBar.setVisibility(View.GONE);
		mRecipeFragment=getFragmentManager().findFragmentById(R.id.recipe_fragment);
		mRecipeDetailPresentar.getData("35120");
		return view;

	}

	@Override
	public void showLoading() {
     mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		mProgressBar.setVisibility(View.GONE);
	}

	@Override
	public void update(RecipeDetailItem recipeDetailItem) {
		RecipeDetail recipeDetail=recipeDetailItem.getRecipe();
		Glide.with(getActivity()).load(recipeDetail.getImage_url()).into(mRecipeImage);
		mRecipeName.setText(recipeDetail.getPublisher());
		mRecipeTitle.setText(recipeDetail.getTitle());
	}
}
