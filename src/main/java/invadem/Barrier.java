package invadem;

import processing.core.PImage;

import java.util.ArrayList;

abstract class BarrierLifeTime extends Game {
    int lifeTime;
    public abstract void setLifeTime(int i);
    public int getLifeTime(){
        return this.lifeTime;
    }

}
public class Barrier {
    float x;
    float y;
    int TotalLifeTime;
    public BarrierTop barrierTop;
    public BarrierLeft barrierLeft;
    public BarrierRight barrierRight;
    public ArrayList<BarrierNormal> barrierNormals;

    public Barrier() {
        barrierTop = new BarrierTop();
        barrierLeft = new BarrierLeft();
        barrierRight = new BarrierRight();
        barrierNormals = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            barrierNormals.add(new BarrierNormal());
        }
    }
}
class BarrierLeft extends BarrierLifeTime{
    public PImage firstImage;
    public PImage SecondImage;
    public PImage thirdImage;

    public BarrierLeft() {
        this.lifeTime = 3;
    }

    public void setLifeTime(int i){
        this.lifeTime -= i;
    }

    public int getLifeTime(){ return lifeTime; }

    public void setFirstImage(PImage firstImage) {
        this.firstImage = firstImage;
    }

    public void setSecondImage(PImage secondImage) {
        SecondImage = secondImage;
    }

    public void setThirdImage(PImage thirdImage) {
        this.thirdImage = thirdImage;
    }
}

class BarrierRight extends BarrierLifeTime{
    public PImage firstImage;
    public PImage SecondImage;
    public PImage thirdImage;

    public BarrierRight() {
        this.lifeTime = 3;
    }


    public void setLifeTime(int i){
        this.lifeTime -= i;
    }

    public int getLifeTime(){
        return lifeTime;
    }

    public void setFirstImage(PImage firstImage) {
        this.firstImage = firstImage;
    }

    public void setSecondImage(PImage secondImage) {
        SecondImage = secondImage;
    }

    public void setThirdImage(PImage thirdImage) {
        this.thirdImage = thirdImage;
    }
}
class BarrierTop extends BarrierLifeTime{
    public PImage firstImage;
    public PImage SecondImage;
    public PImage thirdImage;

    public BarrierTop(){
        this.lifeTime = 3;
    }
    public int getLifeTime() {
        return lifeTime;
    }
    public void setLifeTime(int i){
        this.lifeTime -= i;
    }
    public void setFirstImage(PImage firstImage) {
        this.firstImage = firstImage;
    }

    public void setSecondImage(PImage secondImage) {
        SecondImage = secondImage;
    }

    public void setThirdImage(PImage thirdImage) {
        this.thirdImage = thirdImage;
    }
}

class BarrierNormal extends BarrierLifeTime{
    PImage firstImage;
    PImage SecondImage;
    PImage thirdImage;

    public BarrierNormal(){
        this.lifeTime = 3;
    }

    public void setLifeTime(int i){
        this.lifeTime -= i;
    }

    public int getLifeTime(){
        return lifeTime;
    }

    public void setFirstImage(PImage firstImage) {
        this.firstImage = firstImage;
    }

    public void setSecondImage(PImage secondImage) {
        SecondImage = secondImage;
    }

    public void setThirdImage(PImage thirdImage) {
        this.thirdImage = thirdImage;
    }
}