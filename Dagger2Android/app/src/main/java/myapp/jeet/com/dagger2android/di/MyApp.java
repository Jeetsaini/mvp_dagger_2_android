package myapp.jeet.com.dagger2android.di;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


/**
 * Created by Admin on 6/15/2017.
 */

public class MyApp extends Application implements HasActivityInjector {
	@Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
	@Override
	public void onCreate() {
		super.onCreate();
		AppInjector.init(this);
	}


	@Override
	public AndroidInjector<Activity> activityInjector() {
		return dispatchingAndroidInjector;
	}
}
