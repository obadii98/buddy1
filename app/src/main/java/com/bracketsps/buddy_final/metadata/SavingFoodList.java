package com.bracketsps.buddy_final.metadata;

import com.bracketsps.buddy_final.ui.allFragment.Food;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SavingFoodList implements Serializable {
    ArrayList<Food> foods;
    int calories,protien;
    String File_name="Food.bin";
    public SavingFoodList() {
        foods = new ArrayList<Food>();
        calories=0;
        protien=0;

    }



    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public String getFile_name() {
        return File_name;
    }

    public void setFile_name(String file_name) {
        File_name = file_name;
    }


    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtien() {
        return protien;
    }

    public void setProtien(int protien) {
        this.protien = protien;
    }
}
