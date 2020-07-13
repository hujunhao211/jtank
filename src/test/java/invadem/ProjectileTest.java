package invadem;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProjectileTest extends App {
    @Before
    public void setUp(){
        String[] args = {"invadem.APPTest"};
        PApplet.runSketch(args, this);
        noLoop();
        delay(300);
    }
//check projectile constrcutor
    @Test
    public void testProjectileConstruction() {
        Projectile proj = new Projectile();
        assertNotNull(proj);
    }
//check projectile image
    @Test
    public void testProjectileImage(){
        for (Projectile projectile : projectiles){
            assertNotNull(projectile.image);
        }
    }
//check projectile speed
    @Test
    public void testProjectileSetSpeed(){
        Projectile projectile = new Projectile();
        projectile.setSpeed(5);
        assertEquals(projectile.getSpeed(),5);
    }
//check powerprojectile
    @Test
    public void testPowerProjectile(){
        PowerProjectile powerProjectile = new PowerProjectile();
        powerProjectile.setImage(loadImage("projectile_lg.png"));
        assertNotNull(powerProjectile);
        PowerInvader powerInvader = new PowerInvader(200,400);
        powerInvader.setImage(loadImage("invader1_power.png"));
        Projectile projectile = powerInvader.fire(powerProjectile.image);
        getTank().setX(208);
        getTank().setY(384);
        assertTrue(check_collection(projectile,getTank()));
    }
//check power projectile damage
    @Test
    public void testPowerProjectiledamage(){
        PowerInvader powerInvader = new PowerInvader(200,400);
        powerInvader.setImage(loadImage("invader1_power.png"));
        Projectile projectile = powerInvader.fire(loadImage("projectile_lg.png"));
        getTank().setX(208);
        getTank().setY(384);
        projectiles.add(projectile);
        assertNotNull(projectiles);
        assertNotNull(projectiles.get(0));
        assertEquals(1,projectiles.size());
        removeProjectile = new ArrayList<>();
        checkTankCollision();
        assertEquals(0,getTank().getLife());
    }

    @Test
    public void checkSetSpeed(){
        Projectile projectile = new Projectile();
        projectile.setSpeed(2);
        assertEquals(2,projectile.getSpeed());
    }




//    @Test
//    public void testProjectileIsFriendly() {
//        Projectile proj = /* Your Constructor Here */
//        assertTrue(proj.isFriendly());
//    }

//    @Test
//    public void testProjectileIsNotFriendly() {
//        Projectile proj = /* Your Constructor Here */
//        assertFalse(proj.isFriendly());
//    }

//    @Test
//    public void testProjectileIntersect() {
//        Projectile proj = /* Your Constructor Here */
//        Invader inv = /* Your Constructor Here */
//        Tank tank = /* Your Constructor Here */
//        assertFalse(proj.intersect(inv));
//        assertFalse(proj.intersect(tank));
//    }

}
