package com.example.myrecipe.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myrecipe.Basis;
import com.example.myrecipe.DBSOpenHandler;
import com.example.myrecipe.DBSQueries;
import com.example.myrecipe.MainActivity;
import com.example.myrecipe.R;
import com.example.myrecipe.Recipe;
import com.example.myrecipe.list.RecipeListActivity;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText t_name = null;
    private Spinner spinner_difficulty = null;
    private EditText t_worktime = null;
    private EditText t_cookingtime = null;
    private EditText t_ingredients = null;
    private EditText t_recipe = null;

    private List<String> difficultyList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_add_recipe);

        t_name = findViewById(R.id.t_name);
        spinner_difficulty = findViewById(R.id.spinner_difficulty);
        t_worktime = findViewById(R.id.t_worktime);
        t_cookingtime = findViewById(R.id.t_cookingtime);
        t_ingredients = findViewById(R.id.t_ingredients);
        t_recipe = findViewById(R.id.t_recipe);

        difficultyList = new ArrayList<String>();
        difficultyList.add("Einfach");
        difficultyList.add("Mittel");
        difficultyList.add("Schwer");

        DBSOpenHandler.createInstance(this, DBSQueries.getDatabaseName(), DBSQueries.getDatabaseVersion());
    }

    private Recipe checkInputs(){
        String difficulty = "";

        String name = t_name.getText().toString();
        if (name.length()==0){
            Basis.showToast(this, "Der Name muss mindestens einen Buchstaben haben.");
            return null;
        }

        String worktime = t_worktime.getText().toString();
        if (worktime.length()==0){
            Basis.showToast(this, "Eine Zeit muss angegeben werden.");
            return null;
        }

        String cookingtime = t_cookingtime.getText().toString();
        if (cookingtime.length()==0){
            Basis.showToast(this, "Eine Zeit muss angegeben werden.");
            return null;
        }

        String ingredients = t_ingredients.getText().toString();
        if (ingredients.length()==0){
            Basis.showToast(this, "Es müssen Zutaten angegeben werden.");
            return null;
        }

        String recipe = t_recipe.getText().toString();
        if (recipe.length()==0){
            Basis.showToast(this, "Das Zubereitungsrezept muss angegeben werden.");
            return null;
        }

        int indexDifficulty = spinner_difficulty.getSelectedItemPosition();
        difficulty = difficultyList.get(indexDifficulty);

        return new Recipe(name, difficulty, worktime, cookingtime, ingredients, recipe);
    }

    public void bn_insert_click(View view) {
        Recipe recipe = checkInputs();
        if (recipe!=null){
            long rowid = DBSQueries.insertRecipe(recipe);

            if (rowid<1){
                Basis.showToast(this, "Datensatz konnte nicht eingefügt werden: "+rowid);
            }else {
                Basis.showToast(this, "RowId: "+rowid);
            }
        }
    }

    //Custom navigation
    //placed at the bottom
    //3 button methods to switch between the activities
    public void bn_list_click(View view) {
        Intent intent = new Intent(this, RecipeListActivity.class);
        startActivity(intent);
    }

    public void bn_create_click(View view) {
        /*Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);*/
    }


    public void bn_home_click(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
