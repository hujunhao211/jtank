package invadem;

public class ArmouredInvader extends Invader {
    private int liftTime;
    public ArmouredInvader(float x, float y){
        super(x,y);
        liftTime = 3;
    }
    public int getLiftTime(){ return this.liftTime; }
    public void minusLife(){
        this.liftTime--;
    }
    public boolean checkLifeTime(){
        return this.liftTime == 0;
    }
}