package myapp.jeet.com.dagger2android.api;



import myapp.jeet.com.dagger2android.models.RecipeDetail;
import myapp.jeet.com.dagger2android.models.RecipeDetailItem;
import myapp.jeet.com.dagger2android.models.SearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Admin on 6/19/2017.
 */

public interface RetrofitServiceInterface {
	@GET("search")
	Observable<SearchResponse> callSearchRecipeAPI(@Query("key") String key, @Query("q")String query);

	@GET("get")
	Observable<RecipeDetailItem>  callGetRecipeDetail(@Query("key")String key, @Query("rId") String recipeId);


}
