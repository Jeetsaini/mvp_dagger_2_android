package myapp.jeet.com.dagger2android.view;

import android.app.Application;
import android.app.Fragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import myapp.jeet.com.dagger2android.R;
import myapp.jeet.com.dagger2android.api.RetroFitRepositry;
import myapp.jeet.com.dagger2android.arch.RecipePresentar;
import myapp.jeet.com.dagger2android.helpers.DividerItemDecoration;
import myapp.jeet.com.dagger2android.helpers.RecyclerTouchListener;
import myapp.jeet.com.dagger2android.models.Recipe;
import myapp.jeet.com.dagger2android.models.SearchResponse;
import myapp.jeet.com.dagger2android.view.adapter.RecipeRecyclerViewAdapter;


public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner,HasSupportFragmentInjector,RecipePresentar.RecipeView {
	LifecycleRegistry lifecycleRegistry=new LifecycleRegistry(this);
	@Inject
	DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector;

	@Inject
	RecipePresentar recipePresentar;

	private List<Recipe> mRecipeList=new ArrayList<>();

	private RecyclerView mRecipeRecyclerViewAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRecipeRecyclerViewAdapter=(RecyclerView)findViewById(R.id.recipe_recycler_view);
		mRecipeRecyclerViewAdapter.setHasFixedSize(false);
		LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
		mRecipeRecyclerViewAdapter.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));

		mRecipeRecyclerViewAdapter.setLayoutManager(mLayoutManager);
		mRecipeRecyclerViewAdapter.setItemAnimator(new DefaultItemAnimator());
		mRecipeRecyclerViewAdapter.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, mRecipeRecyclerViewAdapter, new RecyclerTouchListener.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Intent intent=new Intent(getApplicationContext(),RecipeDetailActivity.class);
				intent.putExtra("recipe_id",mRecipeList.get(position).getRecipe_id());
				startActivity(intent);
			}

			@Override
			public void onLongClick(View view, int position) {

			}
		}));


		/*recipePresentar=new RecipePresentar(mRetroFitRepositry,this);*/
		recipePresentar.searchQuery("chicken");


	}

	@Override
	public LifecycleRegistry getLifecycle() {
		return lifecycleRegistry;
	}


	@Override
	public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
		return dispatchingAndroidInjector;
	}

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void updateRecyclerView(SearchResponse searchResponse) {
		mRecipeList=searchResponse.getRecipes();
		RecipeRecyclerViewAdapter recipeRecyclerViewAdapter=new RecipeRecyclerViewAdapter(MainActivity.this,searchResponse.getRecipes());
		mRecipeRecyclerViewAdapter.setAdapter(recipeRecyclerViewAdapter);
	}
}
