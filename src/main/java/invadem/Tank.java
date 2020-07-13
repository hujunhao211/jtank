package invadem;
import processing.core.PImage;

public class Tank extends Game{
    private boolean moveLeft;
    private boolean moveRight;
    int fireCount = 20;
    int life = 3;
    int tankFireRate = 20;
    private int speed = 1;

    public boolean canFire(){
        return fireCount >= tankFireRate;
    }
    public int getSpeed(){
        return this.speed;
    }

    public Projectile fire(PImage pImage){
        this.fireCount = 0;
        Projectile newProjectile = new Projectile(pImage, this.x + this.image.width / 2, this.y - this.image.height,1);
        return newProjectile;
    }

    public void moveLeft() {
        if (this.x - this.image.width/2 - 1 > 0){
            this.x -= speed;
        }
    }

    public boolean setLife(Projectile projectile){
        if (App.check_collection(this,projectile)){
            if (projectile instanceof PowerProjectile) {
                this.life -= 3;
            } else {
                this.life -= 1;
            }
            return true;
        }
        return false;
    }

    public void moveRight() {
        if (this.x + this.image.width - 1 < 640){
            this.x += speed;
        }
    }

    public boolean getMoveLeft(){
        return this.moveLeft;
    }

    public boolean getMoveRight(){
        return this.moveRight;
    }

    public void setMoveLeft(boolean moveLeft){
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight){
        this.moveRight = moveRight;
    }

    public void setTankFireRate (int n){
        this.tankFireRate = n;
    }


    public int getLife(){
        return this.life;
    }

    public void setLife(int n){
        this.life = n;
    }


}

