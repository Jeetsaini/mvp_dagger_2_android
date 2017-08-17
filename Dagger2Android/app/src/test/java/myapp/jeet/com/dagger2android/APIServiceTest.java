package myapp.jeet.com.dagger2android;

import android.database.Observable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import myapp.jeet.com.dagger2android.api.ApiConstants;
import myapp.jeet.com.dagger2android.api.RetrofitServiceInterface;
import myapp.jeet.com.dagger2android.models.Recipe;
import myapp.jeet.com.dagger2android.models.SearchResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static org.bouncycastle.util.Arrays.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Admin on 8/16/2017.
 */
@RunWith(JUnit4.class)
public class APIServiceTest {
	private RetrofitServiceInterface mRetrofitServiceInterface;

	private MockWebServer mMockWebServer;


	@Before
	public void createService() throws IOException {
		mMockWebServer = new MockWebServer();
		mRetrofitServiceInterface = new Retrofit.Builder()
				.baseUrl(ApiConstants.API_BAS_URL)
				.addConverterFactory( GsonConverterFactory.create())
				.addCallAdapterFactory( RxJavaCallAdapterFactory.create())
				.build()
				.create(RetrofitServiceInterface.class);
	}

	@After
	public void stopService() throws IOException {
		mMockWebServer.shutdown();
	}



	@Test
	public void getRecipe()
	{
		rx.Observable<SearchResponse> observable=mRetrofitServiceInterface.callSearchRecipeAPI( ApiConstants.APIKEY,"chicken" );
		observable.doOnNext( searchResponse -> {
			Recipe recipe=searchResponse.getRecipes().get( 0 );
			//assertThat( recipe.getTitle(),is(  ) );

		} );





	}

	private <T>T getFirstValue(SearchResponse searchResponse)
	{
		List<Recipe> recipes=searchResponse.getRecipes();
		return (T)recipes.get( 0 );
	}


}
