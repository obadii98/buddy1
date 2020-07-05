package com.bracketsps.buddy_final.ui.allFragment;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private int quan;
    private String unit;

    public Food(String name, int quan, String unit){
        this.name=name;
        this.quan=quan;
        this.unit=unit;
    }
    public Food(String unit){
        this.unit=unit;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getQuan(){
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
