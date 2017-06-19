package myapp.jeet.com.dagger2android.api;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import myapp.jeet.com.dagger2android.models.SearchResponse;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin on 6/19/2017.
 */

public class RetroFitRepositry {

	@Inject
	public RetroFitRepositry()
	{

	}
	public RetrofitServiceInterface createRetrofitClient()
	{
		OkHttpClient client = new OkHttpClient.Builder()
				.connectTimeout(2, TimeUnit.MINUTES)
				.readTimeout(2, TimeUnit.MINUTES)
				.build();
		return new Retrofit.Builder()
				.baseUrl(ApiConstants.API_BAS_URL)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.addConverterFactory(ScalarsConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())

				.build().create(RetrofitServiceInterface.class);

	}

	public Subscription callSearchAPI(String query, APICallbacks<SearchResponse> apiCallbacks)
	{
		RetrofitServiceInterface retrofitServiceInterface=createRetrofitClient();
		Subscription subscription=retrofitServiceInterface.callSearchRecipeAPI(ApiConstants.APIKEY,query).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<SearchResponse>() {
					@Override
					public void onCompleted() {
						Log.d("OnCompleted","");
					}

					@Override
					public void onNext(SearchResponse searchResponse) {
						apiCallbacks.onSuccess(searchResponse);
					}

					@Override
					public void onError(Throwable e) {
						e.printStackTrace();

					}
				});
		return subscription;
	}


}
