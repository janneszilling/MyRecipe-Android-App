package com.example.myrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myrecipe.add.AddRecipeActivity;
import com.example.myrecipe.list.RecipeListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_main);

        //Datenbank Helper initializieren !!!
        DBSOpenHandler.createInstance(this, DBSQueries.getDatabaseName(), DBSQueries.getDatabaseVersion());
    }



    //Custom navigation
    //placed at the bottom
    //3 button methods to switch between the activities
    public void bn_list_click(View view) {
        Intent intent = new Intent(this, RecipeListActivity.class);
        startActivity(intent);
    }

    public void bn_create_click(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }


    public void bn_home_click(View view) {
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
    }
}
