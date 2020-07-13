package invadem;

import processing.core.PImage;

public abstract class Game {
    float x;
    float y;
    public PImage image;

    public void setImage(PImage image){
        this.image = image;
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public PImage getImage() {
        return image;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}