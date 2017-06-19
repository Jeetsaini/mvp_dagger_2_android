package myapp.jeet.com.dagger2android.arch;

import javax.inject.Inject;

import myapp.jeet.com.dagger2android.view.MainActivity;
import myapp.jeet.com.dagger2android.api.APICallbacks;
import myapp.jeet.com.dagger2android.api.RetroFitRepositry;
import myapp.jeet.com.dagger2android.models.SearchResponse;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Admin on 6/19/2017.
 */

public class RecipePresentar {
	private RetroFitRepositry mRetroFitRepositry;
	private MainActivity mRecipeView;
	CompositeSubscription compositeSubscription;

    @Inject
	public RecipePresentar(RetroFitRepositry retroFitRepositry,MainActivity recipeView)
	{
		this.mRetroFitRepositry=retroFitRepositry;
		this.mRecipeView=recipeView;
		compositeSubscription=new CompositeSubscription();


	}

	public void searchQuery(String query)
	{
		mRecipeView.showLoading();
		Subscription subscription=mRetroFitRepositry.callSearchAPI(query, new APICallbacks<SearchResponse>() {
			@Override
			public void onSuccess(SearchResponse response) {
				mRecipeView.hideLoading();
				mRecipeView.updateRecyclerView(response);
			}

			@Override
			public void onFailed(String error) {
                mRecipeView.hideLoading();
			}
		});
		compositeSubscription.add(subscription);



	}


	public interface RecipeView
	{
		void showLoading();
		void hideLoading();
		void updateRecyclerView(SearchResponse searchResponse);
	}
}
