package com.example.myrecipe.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myrecipe.Basis;
import com.example.myrecipe.DBSQueries;
import com.example.myrecipe.MainActivity;
import com.example.myrecipe.MyCursorAdapter;
import com.example.myrecipe.R;
import com.example.myrecipe.add.AddRecipeActivity;

public class RecipeListActivity extends AppCompatActivity {

    private ListView listView = null;
    private ScrollView scrollView = null;

    private static final String TAG = RecipeListActivity.class.getSimpleName();

    private MyCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setTitle("My Recipes");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.colorTransparent)));
        getSupportActionBar().hide();

        setContentView(R.layout.activity_recipe_list);

        scrollView = findViewById(R.id.scroll_recipe_details);

        listView = findViewById(R.id.listview);
        insertAdapter();


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                        scrollView.setVisibility(View.VISIBLE);

                        showRecipeToast(cursor);
                    }
                }
        );
    }

    private void insertAdapter(){

        Cursor cursor = DBSQueries.getRecipeCursor();
        dataAdapter = new MyCursorAdapter(
                this, cursor, false
        );
        listView.setAdapter(dataAdapter);
    }

    private void showRecipeToast(Cursor cursor) {
        Basis.showToast(this, cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.NAME)));

        TextView tvname = findViewById(R.id.tvnamedetail);
        TextView tvdifficulty = findViewById(R.id.tvdifficultydetail);
        TextView tvtime = findViewById(R.id.tvtimedetail);
        TextView tvingredients = findViewById(R.id.tvingredientsdetail);
        TextView tvrecipe = findViewById(R.id.tvrecipedetail);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.NAME));
        String difficulty = "Der Schwierigkeitsgrad liegt bei "+cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.DIFFICULTY))+".";
        String time = "Arbeitszeit: "+cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.WORKTIME))+" und Koch-/Backzeit: "+cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.COOKINGTIME));
        String ingredients = cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.INGREDIENTS));
        String recipe = cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.RECIPE));

        tvname.setText((name));
        tvdifficulty.setText((difficulty));
        tvtime.setText((time));
        tvingredients.setText((ingredients));
        tvrecipe.setText((recipe));
    }

    public void bn_invisible_click(View view) {
        if (scrollView.getVisibility() == View.GONE){
            scrollView.setVisibility(View.VISIBLE);
        }else{
            scrollView.setVisibility(View.GONE);
        }
    }

    //Custom navigation
    //placed at the bottom
    //3 button methods to switch between the activities
    public void bn_list_click(View view) {
        /*Intent intent = new Intent(this, RecipeListActivity.class);
        startActivity(intent);*/
    }

    public void bn_create_click(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }


    public void bn_home_click(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
