package invadem;

import processing.core.PImage;

public class Projectile extends Game{
    int speed;
    boolean hit;
    public Projectile(){
        speed = 1;
    }

    public Projectile(PImage image, float x, float y, int speed) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }



    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setImage(PImage image){
        this.image = image;
    }

    public void move() {
        this.y = this.y - speed;
    }
}

