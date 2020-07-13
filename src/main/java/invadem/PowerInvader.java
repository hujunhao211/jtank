package invadem;

import processing.core.PImage;

public class PowerInvader extends Invader{
    public PowerInvader(float x, float y){
        super(x,y);
    }
    @Override
    public Projectile fire(PImage pImage){
        return new PowerProjectile(pImage, this.x + this.image.width / 2, this.y - this.image.height,-1);
    }
}