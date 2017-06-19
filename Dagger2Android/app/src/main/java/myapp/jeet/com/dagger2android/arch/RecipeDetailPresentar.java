package myapp.jeet.com.dagger2android.arch;

import javax.inject.Inject;

import myapp.jeet.com.dagger2android.api.APICallbacks;
import myapp.jeet.com.dagger2android.api.RetroFitRepositry;
import myapp.jeet.com.dagger2android.models.RecipeDetailItem;
import myapp.jeet.com.dagger2android.view.RecipeDetailFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Admin on 6/19/2017.
 */

public class RecipeDetailPresentar {
	private RetroFitRepositry mRetroFitRepositry;
	private RecipeDetailFragment mDetailView;
	private CompositeSubscription compositeSubscription;

	@Inject
	public RecipeDetailPresentar(RetroFitRepositry retroFitRepositry,RecipeDetailFragment recipeDetailFragment)
	{
		this.mRetroFitRepositry=retroFitRepositry;
		this.mDetailView=recipeDetailFragment;
		compositeSubscription=new CompositeSubscription();
	}


	public void getData(String recipeId)
	{
		Subscription subscription=mRetroFitRepositry.getRecipe(recipeId, new APICallbacks<RecipeDetailItem>() {
			@Override
			public void onSuccess(RecipeDetailItem response) {
				mDetailView.hideLoading();
				mDetailView.update(response);
			}

			@Override
			public void onFailed(String error) {

			}
		});
		compositeSubscription.add(subscription);
	}

	public interface DetailView
	{
		void showLoading();
		void hideLoading();

		void update(RecipeDetailItem recipeDetailItem);

	}


}
