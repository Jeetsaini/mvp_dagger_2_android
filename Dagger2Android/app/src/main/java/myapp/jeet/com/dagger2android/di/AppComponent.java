package myapp.jeet.com.dagger2android.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Admin on 6/15/2017.
 */



@Singleton
@Component(modules ={AndroidInjectionModule.class,
		AppModule.class,MainActivityModule.class,RecipeDetailModule.class})
public interface AppComponent {
	@Component.Builder
	interface Builder
	{
		@BindsInstance Builder application(Application application);
		AppComponent build();
	}

	void inject(MyApp myApp);
}
