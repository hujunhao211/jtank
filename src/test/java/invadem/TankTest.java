package invadem;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.*;

public class TankTest extends App {

    @Before
    public void before(){
        String[] args = {"invadem.APPTest"};
        PApplet.runSketch(args, this);
        noLoop();
        delay(300);
    }
//check tank constructor
    @Test
    public void testTankConstruction() {
        Tank tank = new Tank();
        assertNotNull(tank);
    }
//check tank fire
    @Test
    public void testTankFire() {
        Tank tank = new Tank();
        assertNotNull(tank);
        tank.setImage(loadImage("tank1.png"));
        assertNotNull(tank.image);
        tank.setX(420);
        tank.setY(240);
        Projectile projectile = new Projectile();
        assertNotNull(projectile);
        projectile.setImage(loadImage("projectile.png"));
        assertNotNull(projectile.image);
        assertNotNull(tank.fire(projectile.image));
    }
//check tank move
    @Test
    public void testTankMoveRight(){
        getTank().setMoveRight(true);
        assertTrue(getTank().getMoveRight());
    }
//check tank move left
    @Test
    public void testTankMoveLeft(){
        getTank().setMoveLeft(true);
        assertTrue(getTank().getMoveLeft());
    }
//check tank fire rate
    @Test
    public void testTankFireRate() {
        getTank().setTankFireRate(8);
        assertEquals(8,getTank().tankFireRate);
    }
//check tank move comprehensively
    @Test
    public void testTankMove(){
        double value = getTank().getX();
        getTank().moveRight();
        assertEquals(value + 1,getTank().getX(),0.1);
    }
//    check tank can fire
    @Test
    public void testCanFire(){
        assertTrue(getTank().canFire());
    }
//check tank spped
    @Test
    public void testGetSpeed(){
        assertEquals(1,getTank().getSpeed());
    }
//check tank hitted by projectile
    @Test
    public void testTankCollision(){
        Projectile projectile = new Projectile(getProjectile().image,getTank().getX(),getTank().getY(),1);
        getTank().setLife(projectile);
        assertEquals(2,getTank().getLife());
    }

}
