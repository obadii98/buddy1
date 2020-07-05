package com.bracketsps.buddy_final.ui.allFragment;


public class Programme  {
   int  img1,img2;
   String tilte;


    public Programme() {

    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public Programme(int img1, int img2, String tilte) {
        this.img1 = img1;
        this.img2 = img2;
        this.tilte = tilte;
    }
    public Programme(String tilte){
        this.tilte=tilte;
    }
}
