package com.bracketsps.buddy_final.metadata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;



public class SavingProgram implements Serializable {
    boolean isthere;
    int train_day;
    double weight;
    String kg_week;
    char type_pro;
    String file_name="pro.bin";



    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public char getType_pro() {
        return type_pro;
    }

    public void setType_pro(char type_pro) {
        this.type_pro = type_pro;
    }

    public SavingProgram()
    {
     isthere=false;
     train_day=0;
     kg_week="";
     weight=0.0;
    type_pro=' ';


    }

    public SavingProgram(boolean isthere, int train_day, String kg_week) {
        this.isthere = isthere;
        this.train_day = train_day;
        this.kg_week = kg_week;
    }
    public SavingProgram(SavingProgram s){

       this.kg_week=s.kg_week;
        this.train_day=s.train_day;
        this.isthere=s.isthere;
    }




    public boolean isIsthere() {
        return isthere;
    }

    public void setIsthere(boolean isthere) {
        this.isthere = isthere;
    }

    public int getTrain_day() {
        return train_day;
    }

    public void setTrain_day(int train_day) {
        this.train_day = train_day;
    }

    public String getKg_week() {
        return kg_week;
    }

    public void setKg_week(String kg_week) {
        this.kg_week = kg_week;
    }


    public SavingProgram(String file_name){
        SavingProgram tmp=new SavingProgram();
        FileInputStream read_file = null;
        try {
            read_file = new FileInputStream("/data/data/com.bracketsps.buddy_final/files/pro.bin");
            ObjectInputStream is = new ObjectInputStream(read_file);
            tmp = (SavingProgram) is.readObject();
            is.close();
            read_file.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.kg_week=tmp.getKg_week();
        this.isthere=tmp.isthere;
        this.type_pro=tmp.type_pro;
        this.train_day=tmp.train_day;
        this.weight=tmp.train_day;



    }



}
