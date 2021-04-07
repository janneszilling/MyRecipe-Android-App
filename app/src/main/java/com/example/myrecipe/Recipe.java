package com.example.myrecipe;

public class Recipe {

    public int pindex = -1;
    public String name = "";
    public String difficulty = "";
    public String worktime = "";
    public String cookingtime = "";
    public String ingredients = "";
    public String recipe = "";

    public Recipe(String name, String difficulty, String worktime, String cookingtime, String ingredients, String recipe){
        this.name = name;
        this.difficulty = difficulty;
        this.worktime = worktime;
        this.cookingtime = cookingtime;
        this.ingredients = ingredients;
        this.recipe = recipe;
    }

    public Recipe(){
    }

    @Override
    public String toString(){
        return pindex+", "+name+", "+difficulty+", "+worktime+", "+cookingtime+", "+ingredients+", "+recipe;
    }
}


