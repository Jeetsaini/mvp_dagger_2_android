package myapp.jeet.com.dagger2android.api;

/**
 * Created by Admin on 6/19/2017.
 */

public interface APICallbacks<T> {

	void onSuccess(T response);

	void onFailed(String error);
}
