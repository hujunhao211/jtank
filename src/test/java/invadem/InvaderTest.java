package invadem;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class InvaderTest extends App{
    Invader inv;
    Projectile projectile;
    PImage pImage;
    @Before
    public void init(){
        String[] args = {"Invader.txt"};
        PApplet.runSketch(args,this);
        noLoop();
        delay(300);
        inv = new Invader(2,3);
        projectile = new Projectile();
        pImage = new PImage();
        projectile.setImage(pImage);
    }





    @Test
    public void testInvaderConstruction() {
        /* Your Constructor Here */
        assertNotNull(projectile);

    }
    @Test
    public void setMoveHorizontal() {
        /* Your Constructor Here */
        inv.setMoveHorizontal(pImage);
        assertNotNull(inv.moveHorizontal);
    }

    @Test
    public void setMoveDown(){
        inv.setMoveDown(pImage);
        assertNotNull(inv.moveDown);
    }

    @Test
    public void testPowerInvaderConstructor(){
        PowerInvader powerInvader = new PowerInvader(2,4);
        assertNotNull(powerInvader);
    }
    @Test
    public void testArmouredInvaderConstructor(){
        ArmouredInvader armouredInvader = new ArmouredInvader(2,5);
        assertNotNull(armouredInvader);
        assertEquals(3,armouredInvader.getLiftTime());
    }
    @Test
    public void TestArmouredInvaderLife(){
        ArmouredInvader armouredInvader = new ArmouredInvader(2,5);
        armouredInvader.minusLife();
        assertEquals(2,armouredInvader.getLiftTime());
        armouredInvader.minusLife();
        armouredInvader.minusLife();
        assertTrue(armouredInvader.checkLifeTime());
    }

    @Test
    public void testSetY(){
        Invader invader = new Invader(200,300);
        invader.setY();
        assertEquals(301,invader.getY(),0.1);
    }

    @Test
    public void testSetX(){
        Invader invader = new Invader(200,300);
        invader.setX(1);
        assertEquals(201,invader.getX(),0.1);
    }

    @Test
    public void testSetMove(){
        double invaderX = invaders.get(0).getX();
        for (Invader invader : invaders){
            invader.setMove();
        }
        assertEquals(invaderX + 1,invaders.get(0).getX(),0.1);
        double invaderY = invaders.get(0).getY();
        for (Invader invader : invaders){
            invader.change();
        }
        for (Invader invader : invaders){
            invader.setMove();
        }
        assertEquals(invaderY + 1,invaders.get(0).getY(),0.1);
        double invaderLeftX = invaders.get(0).getX();
        for (Invader invader : invaders){
            invader.change();
        }
        for (Invader invader : invaders){
            invader.setMove();
        }
        assertEquals(invaderLeftX - 1,invaders.get(0).getX(),0.1);
    }

    @Test
    public void checkLifeTime(){
        ArmouredInvader armouredInvader = new ArmouredInvader(200,230);
        armouredInvader.minusLife();
        armouredInvader.minusLife();
        armouredInvader.minusLife();
        assertTrue(armouredInvader.checkLifeTime());
    }



}











