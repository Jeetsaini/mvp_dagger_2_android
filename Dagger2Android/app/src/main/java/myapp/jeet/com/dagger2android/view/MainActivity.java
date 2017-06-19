package myapp.jeet.com.dagger2android.view;

import android.app.Application;
import android.app.Fragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import myapp.jeet.com.dagger2android.R;
import myapp.jeet.com.dagger2android.api.RetroFitRepositry;
import myapp.jeet.com.dagger2android.arch.RecipePresentar;
import myapp.jeet.com.dagger2android.helpers.DividerItemDecoration;
import myapp.jeet.com.dagger2android.models.SearchResponse;
import myapp.jeet.com.dagger2android.view.adapter.RecipeRecyclerViewAdapter;


public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner,HasSupportFragmentInjector,RecipePresentar.RecipeView {
	LifecycleRegistry lifecycleRegistry=new LifecycleRegistry(this);
	@Inject
	DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector;

	@Inject
	RecipePresentar recipePresentar;

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
		RecipeRecyclerViewAdapter recipeRecyclerViewAdapter=new RecipeRecyclerViewAdapter(MainActivity.this,searchResponse.getRecipes());
		mRecipeRecyclerViewAdapter.setAdapter(recipeRecyclerViewAdapter);
	}
}
