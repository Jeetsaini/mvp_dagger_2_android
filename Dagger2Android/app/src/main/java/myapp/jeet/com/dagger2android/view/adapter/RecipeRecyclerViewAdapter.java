package myapp.jeet.com.dagger2android.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import myapp.jeet.com.dagger2android.R;
import myapp.jeet.com.dagger2android.models.Recipe;

/**
 * Created by Admin on 6/19/2017.
 */

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeHolder>{

	private List<Recipe> mListRecipes;
	private Context mContext;
	public RecipeRecyclerViewAdapter(Context context,List<Recipe> recipeList)
	{
		this.mListRecipes=recipeList;
		this.mContext=context;

	}
	@Override
	public RecipeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item_list,viewGroup, false);
		return new RecipeHolder(view);
	}

	@Override
	public void onBindViewHolder(RecipeHolder recipeHolder, int i) {
      Recipe recipe=mListRecipes.get(i);
      recipeHolder.recipeName.setText(recipe.getTitle());
		Glide.with(mContext).load(recipe.getImage_url()).into(recipeHolder.recipeImage);
	}

	@Override
	public int getItemCount() {
		return mListRecipes.size();
	}

	public class RecipeHolder extends RecyclerView.ViewHolder
	{
       private ImageView recipeImage;
       private TextView recipeName;
		public RecipeHolder(View itemView) {
			super(itemView);

			recipeImage=(ImageView)itemView.findViewById(R.id.recipe_image);
			recipeName=(TextView)itemView.findViewById(R.id.artist_name);
		}
	}
}
