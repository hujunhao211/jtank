package invadem;

import processing.core.PImage;



public class Invader extends Game{
    public PImage moveDown;
    public PImage moveHorizontal;
    public InvaderDirection move;

    public Invader(float x, float y) {
        this.x = x;
        this.y = y;
        this.move = InvaderDirection.moveRight;
    }

    public Projectile fire(PImage pImage){
        Projectile newProjectile = new Projectile(pImage, this.x + this.image.width / 2, this.y - this.image.height,-1);
        return newProjectile;
    }

    public void setX(int direction){
        this.x += direction;
    }

    public void setY(){
        this.y++;
    }

    public void setMove(){
        if (this.move == InvaderDirection.moveLeft){
            setX(-1);
        } else if(this.move == InvaderDirection.moveRight){
            setX(1);
        } else if(this.move == InvaderDirection.moveDown1 || this.move == InvaderDirection.moveDown2){
            setY();
        }
    }



    public void setMoveDown(PImage image) {
        this.moveDown = image;
    }

    public void setMoveHorizontal(PImage image){
        this.moveHorizontal = image;
    }

    public void change() {
        move = move.change();
    }
}


