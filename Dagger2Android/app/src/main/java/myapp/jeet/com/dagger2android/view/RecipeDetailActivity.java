package myapp.jeet.com.dagger2android.view;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import myapp.jeet.com.dagger2android.R;

/**
 * Created by Admin on 6/19/2017.
 */

public class RecipeDetailActivity extends LifecycleActivity implements LifecycleRegistryOwner,HasSupportFragmentInjector{

	LifecycleRegistry lifecycleRegistry=new LifecycleRegistry(this);
	@Inject
	DispatchingAndroidInjector<android.support.v4.app.Fragment> mDispatchingAndroidInjector;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_detail);
	}

	@Override
	public LifecycleRegistry getLifecycle() {
		return lifecycleRegistry;
	}


	@Override
	public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
		return mDispatchingAndroidInjector;
	}
}
