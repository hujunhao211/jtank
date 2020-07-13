package invadem;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class BarrierTest extends App {

    private Barrier a;
    PImage pImage;

    @Before
    public void setUp(){
        a = new Barrier();
        pImage = new PImage();
        String[] args = {"Barrier.test"};
        PApplet.runSketch(args,this);
        noLoop();
        delay(300);
    }
//    check barrier constructor
    @Test
    public void barrierConstruction() {
        /* Your Constructor Here */
        assertNotNull(a);
    }

    @Test
    public void testBarrierLeft() {
        /* Your Constructor Here */
        assertEquals(3,a.barrierLeft.getLifeTime());
    }
    @Test
    public void testSetImage(){
        BarrierLeft barrierLeft = new BarrierLeft();
        barrierLeft.setFirstImage(pImage);
        assertNotNull(barrierLeft.firstImage);
        barrierLeft.setSecondImage(pImage);
        assertNotNull(barrierLeft.SecondImage);
        barrierLeft.setThirdImage(pImage);
        assertNotNull(barrierLeft.thirdImage);
    }
    //
    @Test
    public void testBarrierRight() {
        /* Your Constructor Here */
        assertEquals(3, a.barrierRight.getLifeTime());
        BarrierRight barrierRight = new BarrierRight();
        barrierRight.setFirstImage(pImage);
        assertNotNull(barrierRight.firstImage);
        barrierRight.setSecondImage(pImage);
        assertNotNull(barrierRight.SecondImage);
        barrierRight.setThirdImage(pImage);
        assertNotNull(barrierRight.thirdImage);
    }


    @Test
    public void testBarrierNormal(){
        BarrierLeft barrierLeft = new BarrierLeft();
        assertEquals(3, barrierLeft.getLifeTime());
    }

    @Test
    public void testBarrierTop(){
        BarrierTop barrierTop = new BarrierTop();
        assertEquals(3,barrierTop.getLifeTime());
    }

    @Test
    public void testCheckBarrier(){
        assertFalse(checkBarriers());
    }
    @Test
    public void testBarrierSetLife(){
        assertEquals(3,barriers.size());
        barriers.get(0).barrierLeft.setLifeTime(1);
        assertEquals(2,barriers.get(0).barrierLeft.getLifeTime());
        setBarrierLeft(barriers.get(0));
        assertEquals(barriers.get(0).barrierLeft.SecondImage,barriers.get(0).barrierLeft.image);
        barriers.get(0).barrierTop.setLifeTime(1);
        assertEquals(2,barriers.get(0).barrierTop.getLifeTime());
        setBarrierTop(barriers.get(0));
        assertEquals(barriers.get(0).barrierTop.SecondImage,barriers.get(0).barrierTop.image);
        barriers.get(0).barrierNormals.get(0).setLifeTime(1);
        assertEquals(2,barriers.get(0).barrierNormals.get(0).getLifeTime());
        barriers.get(0).barrierRight.setLifeTime(1);
        assertEquals(2,barriers.get(0).barrierRight.getLifeTime());
        setBarrierRight(barriers.get(0));
        assertEquals(barriers.get(0).barrierRight.SecondImage,barriers.get(0).barrierRight.image);
    }
    @Test
    public void testBarrierImage(){
        assertEquals(3,barriers.size());
        barriers.get(0).barrierLeft.setLifeTime(2);
        assertEquals(1,barriers.get(0).barrierLeft.getLifeTime());
        setBarrierLeft(barriers.get(0));
        assertEquals(barriers.get(0).barrierLeft.thirdImage,barriers.get(0).barrierLeft.image);
        barriers.get(0).barrierTop.setLifeTime(2);
        assertEquals(1,barriers.get(0).barrierTop.getLifeTime());
        setBarrierTop(barriers.get(0));
        assertEquals(barriers.get(0).barrierTop.thirdImage,barriers.get(0).barrierTop.image);
        barriers.get(0).barrierNormals.get(0).setLifeTime(1);
        assertEquals(2,barriers.get(0).barrierNormals.get(0).getLifeTime());
        barriers.get(0).barrierRight.setLifeTime(2);
        assertEquals(1,barriers.get(0).barrierRight.getLifeTime());
        setBarrierRight(barriers.get(0));
        assertEquals(barriers.get(0).barrierRight.thirdImage,barriers.get(0).barrierRight.image);
    }

    @Test
    public void testCheckBarrierPart(){
        for (int i = 0; i < barriers.size(); i++){
            barriers.get(i).barrierLeft.setLifeTime(3);
        }
        assertFalse(checkBarriers());
    }

    @Test
    public void testCheckBarrierTop(){
        for (int i = 0; i < barriers.size(); i++){
            barriers.get(i).barrierLeft.setLifeTime(3);
        }
        for (int i = 0; i < barriers.size(); i++){
            barriers.get(i).barrierRight.setLifeTime(3);
        }
        assertFalse(checkBarriers());
    }

    @Test
    public void testGetLifeTime(){
        for (int i = 0; i < barriers.size(); i++){
            barriers.get(i).barrierLeft.setLifeTime(3);
        }
        assertEquals(0,barriers.get(0).barrierLeft.getLifeTime());
        for (int i = 0; i < barriers.size(); i++){
            barriers.get(i).barrierRight.setLifeTime(3);
        }
        assertEquals(0,barriers.get(0).barrierRight.getLifeTime());
    }



}
