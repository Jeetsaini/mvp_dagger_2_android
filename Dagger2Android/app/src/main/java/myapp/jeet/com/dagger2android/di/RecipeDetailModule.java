package myapp.jeet.com.dagger2android.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import myapp.jeet.com.dagger2android.view.RecipeDetailActivity;

/**
 * Created by Admin on 6/19/2017.
 */
@Module
public abstract class RecipeDetailModule {
	@ContributesAndroidInjector(modules = FragmentBuildersModule.class)
	abstract RecipeDetailActivity getRecipeDetailActivity();

}
