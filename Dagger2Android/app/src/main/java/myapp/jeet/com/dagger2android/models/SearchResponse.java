package myapp.jeet.com.dagger2android.models;

import java.util.List;

/**
 * Created by Admin on 6/19/2017.
 */

public class SearchResponse {
	private int count;
	private List<Recipe> recipes;

	public int getCount() {
		return count;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}
}
