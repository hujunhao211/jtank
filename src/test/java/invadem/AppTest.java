package invadem;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

public class AppTest extends App{
//assert !!! there will be null pointer exception during test thats due
//    to my extension user have to click the screen to start the game
//    and choose the difficult state
//    it is not because any reason about the testing
    @Before
    public void before(){
        String[] args = {"invadem.APPTest"};
        PApplet.runSketch(args, this);
        noLoop();
        delay(300);
        choose = false;
        state = null;
        score = 0;
        for (Invader invader : invaders){
            invader.move = InvaderDirection.moveRight;
        }
    }
//    test whether the invader has been corrected set
    @Test
    public void testRun(){
        assertEquals(40, invaders.size());
    }

//    test the fire invade is not null
    @Test
    public void testImage(){
        assertNotNull(invaders.get(0));
    }

//    test tank move correctly
    @Test
    public void testTankMove(){
        getTank().moveLeft();
        assertEquals(308,getTank().getX(),0.1);
    }
//    test tank collision method

    @Test
    public void testTankLife(){
        assertFalse(getTank().setLife(getProjectile()));
    }
//test tank move correctly
    @Test
    public void moveLeft(){
        choose = true;
        state = new Easy();
        keyPressed();
        if (keyCode == LEFT){
            assertTrue(getTank().getMoveLeft());
        }
    }
//test barreier load image correctly
    @Test
    public void setBarrierTopImage(){
        Barrier barrier = barriers.get(0);
        assertNotNull(barrier);
        assertNotNull(barriers.get(0).barrierTop.firstImage);
    }
//test move tank is correct
    @Test
    public void moveRight(){
        if (keyCode == RIGHT){
            assertTrue(getTank().getMoveRight());
        }
    }
//test contrcutor of barrier

    @Test
    public void setBarrier(){
        assertNotNull(barriers.get(0));
    }

//test the image of invader has been correctly loaded
    @Test
    public void checkInvaderImage(){
        for(Invader invader : invaders){
            assertNotNull(invader.image);
        }
    }
//test the tank fire
    @Test
    public void checkFire(){
        if (keyCode == UP){
            assertTrue(getFire());
        }
    }
//test the game end correctly
    @Test
    public void testGameOver(){
        getTank().setLife(0);
        checktankLose();
        assertFalse(getFind());
    }
//test the projectiles size is correct if projectile has been produced
    @Test
    public void testCheckProjectile(){
        if (keyCode == UP){
            Projectile projectile = getTank().fire(getProjectile().image);
            projectiles.add(projectile);
            assertEquals(1,projectiles.size());
        }
    }
//test barrier collision
    @Test
    public void testBarrierCollision(){
        Projectile powerProjectile;
        powerProjectile = invaders.get(1).fire(getProjectile().image);
        assertNotNull(powerProjectile);
        invaderFireProjectile(0);
        invaderFireProjectile(1);
        assertFalse(check_collection(barriers.get(0).barrierLeft,projectiles.get(0)));
        assertFalse(check_collection(barriers.get(0).barrierLeft,projectiles.get(1)));
    }

//    test invader fire
    @Test
    public void testProjectile(){
        invaderFireProjectile(0);
        assertEquals(1,projectiles.size());
    }

    @Test
    public void testMouseMove(){
        mouseMoved();
        mousex  = 200;
        mousey = 240;
        mouseClicked();
        assertTrue(choose);
    }

    @Test
    public void extension(){
        mousex = 200;
        mousey = 240;
        mouseClicked();
        mousex = 220;
        mousey = 170;
        mouseClicked();
        assert state instanceof Easy;
    }

    @Test
    public void extensionTwo(){
        mousex = 200;
        mousey = 240;
        mouseClicked();
        mousex = 220;
        mousey = 270;
        mouseClicked();
        assert state instanceof Normal;
    }

    @Test
    public void entensionThird(){
        mousex = 200;
        mousey = 240;
        mouseClicked();
        mousex = 220;
        mousey = 370;
        mouseClicked();
        assert state instanceof Hard;
    }

    @Test
    public void extensionCorner(){
        mousex = 600;
        mousey = 740;
        mouseClicked();
        assertFalse(choose);
        mousex = 200;
        mousey = 240;
        mouseClicked();
        mousex = 400;
        mousey = 600;
        assertNull(state);
    }

    @Test
    public void testKeyReleased(){
        keyCode = LEFT;
        keyReleased();
        assertFalse(getTank().getMoveLeft());
    }

    @Test
    public void testTankFire(){
        Projectile projectile = getTank().fire(getProjectile().image);
        assertNotNull(projectile);
        projectiles.add(projectile);
        assertNotNull(projectiles.get(0));
        assertEquals(1,projectiles.size());
    }

    @Test
    public void testInvaderDirection(){
        setInvaderDirectinImage();
        Invader invader = invaders.get(0);
        assert invader.image == invader.moveHorizontal;
    }

    @Test
    public void checkScore(){
        Invader powerInvader = new PowerInvader(200,300);
        App.checkScore(powerInvader);
        assertEquals(250,score);
    }

    @Test
    public void checkNormalInvaderScore(){
        Invader invader = new Invader(200,400);
        App.checkScore(invader);
        assertEquals(100,score);
    }

    @Test
    public void testInvaderMoveDown(){
        for (Invader invader : invaders){
            invader.change();
        }
        for (Invader invader : invaders){
            assertEquals(invader.move,InvaderDirection.moveDown2);
        }
        setInvaderDirectinImage();
        for (Invader invader : invaders){
            assertEquals(invader.image,invader.moveDown);
        }
    }


}
