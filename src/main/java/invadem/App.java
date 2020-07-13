package invadem;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import java.util.Random;
import java.util.ArrayList;

abstract class DifficultState{
    public abstract void changeTank(Tank tank);
}
class Easy extends DifficultState{
    @Override
    public void changeTank(Tank tank){
        tank.setTankFireRate(0);
    }
}
class Normal extends DifficultState{
    @Override
    public void changeTank(Tank tank){
        tank.setTankFireRate(10);
    }
}

class Hard extends DifficultState{
    @Override
    public void changeTank(Tank tank){
        tank.setTankFireRate(20);
    }
}
public class App extends PApplet {
    private Tank tank;
    PImage sucess;
    ArrayList<PImage> tankLife;
    public static DifficultState state;
    public static boolean drawing = false;
    public static boolean choose = false;
    public static int restart = 0;
    public static int mousex;
    public static int mousey;
    public static int score;
    public static int highestScore;
    public static int time = 0;
    public static int counterTime = 0;
    private static int invaderFire;
    private static int fireRate = 360;
    private static boolean find;
    private static int countMove = 30;
    private static int counter = 0;
    // 3 boolean to store whether left right fire is pressed
    private boolean moveLeft;
    private boolean moveRight;
    private boolean fire;
    private Projectile projectile;
    private PowerProjectile powerProjectile;
    public ArrayList<Invader> invaders;
    public ArrayList<Barrier> barriers;
    public ArrayList<BarrierLifeTime> barrierLifeTimes;
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Invader> removeInvader;
    public ArrayList<Projectile> removeProjectile;
    private PFont font;
    static long currentTime;
    public boolean drawMenu = true;
    public App() {
        //Set up your objects
        tankLife = new ArrayList<>();
        score = 0;
        highestScore = 10000;
    }
    public boolean getFind(){
        return find;
    }
    public boolean getFire(){
        return fire;
    }

    public boolean canFire(){ return invaderFire % fireRate == 0; }

    public void loadArmouedInvader(Invader invader){
        invader.setMoveDown(loadImage("invader2_armoured.png"));
    }

    public void loadPowerInvader(Invader invader){
        invader.setMoveDown(loadImage("invader2_power.png"));
    }

    public void loadInvaders(Invader invader){
        invader.setMoveDown(loadImage("invader2.png"));
    }

    public void loadPicture(Invader invader){
        if (invader instanceof ArmouredInvader) {
            loadArmouedInvader(invader);
        }
        else if(invader instanceof PowerInvader) {
            loadPowerInvader(invader);
        }
        else {
            loadInvaders(invader);
        }
    }

    public void loadInvader(){
        for (int i = 0; i < invaders.size(); i++){
            loadPicture(invaders.get(i));
        }
        for (int i = 0; i < invaders.size(); i++){
            if (i < 10) {
                invaders.get(i).setMoveHorizontal(loadImage("invader1_armoured.png"));
            }
            else if(i < 20) {
                invaders.get(i).setMoveHorizontal(loadImage("invader1_power.png"));
            }
            else {
                invaders.get(i).setMoveHorizontal(loadImage("invader1.png"));
            }
        }
    }

    public Projectile getProjectile(){
        return this.projectile;
    }

    public void mouseMoved(){
        this.mousex = mouseX;
        this.mousey = mouseY;
    }

    public void mouseClicked() {
        if (mousex > 189 && mousex < 455) {
            if (mousey > 230 && mousey < 250) {
                choose = true;
            }
        }
        if (choose) {
            if (mousex > 200 && mousex < 280) {
                if (mousey > 161 && mousey < 179) {
                    drawing = true;
                    state = new Easy();
                }
                else if (mousey > 261 && mousey < 282) {
                    drawing = true;
                    state = new Normal();
                }
                else if (mousey > 361 && mousey < 381) {
                    drawing = true;
                    state = new Hard();
                }
            }
        }
    }
    //    set barrier left part image depends on the liftime
    public void setBarrierLeft(Barrier barrier){
        if (barrier.barrierLeft.getLifeTime() == 3) {
            barrier.barrierLeft.setImage(barrier.barrierLeft.firstImage);
        } else if (barrier.barrierLeft.getLifeTime() == 2) {
            barrier.barrierLeft.setImage(barrier.barrierLeft.SecondImage);
        } else if (barrier.barrierLeft.getLifeTime() == 1) {
            barrier.barrierLeft.setImage(barrier.barrierLeft.thirdImage);
        }
    }
    //    set barrier right part image depends on the liftime
    public void setBarrierRight(Barrier barrier){
        if (barrier.barrierRight.getLifeTime() == 3) {
            barrier.barrierRight.setImage(barrier.barrierRight.firstImage);
        } else if (barrier.barrierRight.getLifeTime() == 2) {
            barrier.barrierRight.setImage(barrier.barrierRight.SecondImage);
        } else if (barrier.barrierRight.getLifeTime() == 1) {
            barrier.barrierRight.setImage(barrier.barrierRight.thirdImage);
        }
    }

    public void setBarrierTop(Barrier barrier){
        if (barrier.barrierTop.getLifeTime() == 3) {
            barrier.barrierTop.setImage(barrier.barrierTop.firstImage);
        } else if (barrier.barrierTop.getLifeTime() == 2) {
            barrier.barrierTop.setImage(barrier.barrierTop.SecondImage);
        } else if (barrier.barrierTop.getLifeTime() == 1) {
            barrier.barrierTop.setImage(barrier.barrierTop.thirdImage);
        }
    }

    public void setBarrierNormals(BarrierNormal barrierNormal){
        if (barrierNormal.getLifeTime() == 3) {
            barrierNormal.setImage(barrierNormal.firstImage);
        }
        if (barrierNormal.getLifeTime() == 2) {
            barrierNormal.setImage(barrierNormal.SecondImage);
        }
        if (barrierNormal.getLifeTime() == 1) {
            barrierNormal.setImage(barrierNormal.thirdImage);
        }
    }

    public void BarrierImage(){
        for (int i = 0; i < barriers.size(); i++) {
            setBarrierLeft(barriers.get(i));
            setBarrierRight(barriers.get(i));
            setBarrierTop(barriers.get(i));
            for (int j = 0; j < barriers.get(i).barrierNormals.size(); j++) {
                setBarrierNormals(barriers.get(i).barrierNormals.get(j));
            }
        }
    }

    public void setInvaders(){
        final int gap = 40;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0){
                    invaders.add(new ArmouredInvader(120 + j * gap, 50 + i * gap));
                }
                else if (i == 1){
                    invaders.add(new PowerInvader(120 + j * gap, 50 + i * gap));
                }
                else {
                    invaders.add(new Invader(120 + j * gap, 50 + i * gap));
                }
            }
        }
    }
    public void invaderFireProjectile(int index){
        Projectile newP;
        Invader invader = invaders.get(index);
        if (invader instanceof PowerInvader) {
            newP = invaders.get(index).fire(powerProjectile.image);
        } else
            newP = invaders.get(index).fire(projectile.image);
        projectiles.add(newP);
    }

    public void setup(){
        find = true;
        if (fireRate > 60){
            fireRate -= 60;
        }
        projectiles = new ArrayList<>();
        font = createFont("PressStart2P-Regular.ttf",16);
        textFont(font);
        tank = new Tank();
        projectile = new Projectile();
        powerProjectile = new PowerProjectile();
        invaders = new ArrayList<>();
        barriers = new ArrayList<>();
        setInvaders();
        loadInvader();
        for (int k = 0; k < 3; k++){
            barriers.add(new Barrier());
        }
        tank.setImage(loadImage("tank1.png"));
        sucess  = loadImage("nextlevel.png");
        tank.x = 640/2 - tank.image.width/2;
        tank.y = 480 - tank.image.height - 10;
        projectile.setImage(loadImage("projectile.png"));
        powerProjectile.setImage(loadImage("projectile_lg.png"));
        for (int i = 0; i < invaders.size(); i++) {
            invaders.get(i).setImage(invaders.get(i).moveHorizontal);
        }

        for(int i = 0; i < barriers.size(); i++){
            barriers.get(i).barrierTop.setFirstImage(loadImage("barrier_top1.png"));
            barriers.get(i).barrierTop.setSecondImage(loadImage("barrier_top2.png"));
            barriers.get(i).barrierTop.setThirdImage(loadImage("barrier_top3.png"));
            barriers.get(i).barrierRight.setFirstImage(loadImage("barrier_right1.png"));
            barriers.get(i).barrierRight.setSecondImage(loadImage("barrier_right2.png"));
            barriers.get(i).barrierRight.setThirdImage(loadImage("barrier_right3.png"));
            barriers.get(i).barrierLeft.setFirstImage(loadImage("barrier_left1.png"));
            barriers.get(i).barrierLeft.setSecondImage(loadImage("barrier_left2.png"));
            barriers.get(i).barrierLeft.setThirdImage(loadImage("barrier_left3.png"));
            for (int j = 0; j < barriers.get(i).barrierNormals.size(); j++){
                barriers.get(i).barrierNormals.get(j).setFirstImage(loadImage("barrier_solid1.png"));
                barriers.get(i).barrierNormals.get(j).setSecondImage(loadImage("barrier_solid2.png"));
                barriers.get(i).barrierNormals.get(j).setThirdImage(loadImage("barrier_solid3.png"));
            }
        }
        barriers.get(0).x = 154;
        barriers.get(1).x = 640/2 - (barriers.get(1).barrierLeft.firstImage.width + barriers.get(1).barrierTop.firstImage.width + barriers.get(1).barrierRight.firstImage.width)/2;
        barriers.get(2).x = 462;
        for (int i = 0; i < barriers.size(); i++){
            barriers.get(i).y = tank.y - 60;
        }
        BarrierImage();
        setBarrierLifeTimes();

        frameRate(60);
    }
    public Tank getTank(){
        return this.tank;
    }

    public void tankMove(){
        if (tank.getMoveLeft()) {
            if (find)
                tank.moveLeft();
        }

        if (tank.getMoveRight()) {
            if (find)
                tank.moveRight();
        }
    }

    public void drawTank(){
        if (find)
            image(tank.image, tank.x, tank.y);
    }
    public void drawInvader(){
        for (int i = 0; i < invaders.size(); i++) {
            if (find)
                image(invaders.get(i).image, invaders.get(i).x, invaders.get(i).y);
        }
    }



    public void drawBarrier(int i){
        if (barriers.get(i).barrierLeft.lifeTime > 0)
            image(barriers.get(i).barrierLeft.image, barriers.get(i).barrierLeft.x, barriers.get(i).barrierLeft.y);
        barriers.get(i).barrierTop.x = 154 * (i + 1) + barriers.get(i).barrierLeft.firstImage.width;
        barriers.get(i).barrierTop.y = barriers.get(i).y;
        if (barriers.get(i).barrierTop.lifeTime > 0)
            image(barriers.get(i).barrierTop.image, barriers.get(i).barrierTop.x, barriers.get(i).barrierTop.y);
        barriers.get(i).barrierRight.x = 154 * (i + 1) + barriers.get(i).barrierLeft.firstImage.width + barriers.get(i).barrierTop.firstImage.width;
        barriers.get(i).barrierRight.y = barriers.get(i).y;
        if (barriers.get(i).barrierRight.lifeTime > 0)
            image(barriers.get(i).barrierRight.image, barriers.get(i).barrierRight.x, barriers.get(i).barrierRight.y);
    }

    public void drawBarrierNormal(int i){
        for (int j = 0; j < 4; j++){
            if (barriers.get(i).barrierNormals.get(j).lifeTime > 0)
                image(barriers.get(i).barrierNormals.get(j).image, barriers.get(i).barrierNormals.get(j).x, barriers.get(i).barrierNormals.get(j).y);
        }
    }

    public void drawBarrierNormals(int i){
        barriers.get(i).barrierNormals.get(1).x = 154 * (i + 1) + barriers.get(i).barrierLeft.firstImage.width + barriers.get(i).barrierTop.firstImage.width;
        barriers.get(i).barrierNormals.get(1).y = barriers.get(i).y + barriers.get(i).barrierTop.firstImage.height;
        barriers.get(i).barrierNormals.get(2).x = 154 * (i + 1);
        barriers.get(i).barrierNormals.get(2).y = barriers.get(i).y + barriers.get(i).barrierTop.firstImage.height + barriers.get(i).barrierNormals.get(i).firstImage.height;
        barriers.get(i).barrierNormals.get(3).x = 154 * (i + 1) + barriers.get(i).barrierLeft.firstImage.width + barriers.get(i).barrierTop.firstImage.width;
        barriers.get(i).barrierNormals.get(3).y = barriers.get(i).y + barriers.get(i).barrierTop.firstImage.height + barriers.get(i).barrierNormals.get(i).firstImage.height;
        drawBarrierNormal(i);
    }

    public void updateBarrierImage(){
        for (int i = 0; i < barriers.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    barriers.get(i).barrierLeft.x = 154 * (i + 1);
                    barriers.get(i).barrierLeft.y = barriers.get(i).y;
                    drawBarrier(i);
                } else {
                    barriers.get(i).barrierNormals.get(0).x = 154 * (i + 1);
                    barriers.get(i).barrierNormals.get(0).y = barriers.get(i).y + barriers.get(i).barrierTop.firstImage.height;
                    drawBarrierNormals(i);
                }
            }
        }
    }

    public void checkInvaderLose(){
        for (Invader invader : invaders) {
            for (Barrier barrier : barriers) {
                if (check_collection(barrier.barrierLeft, invader) || invader.y == (barrier.barrierLeft.y - barrier.barrierLeft.image.height - 10)) {
                    find = false;
                }
                if (check_collection(barrier.barrierTop, invader) || invader.y == (barrier.barrierTop.y - barrier.barrierTop.image.height - 10)) {
                    find = false;
                }
                if (check_collection(barrier.barrierRight, invader)) {
                    find = false;
                }
                for (int j = 0; j < barrier.barrierNormals.size(); j++) {
                    if (check_collection(barrier.barrierNormals.get(j), invader)) {
                        find = false;
                    }
                }
            }
        }
    }

    public void checktankLose(){
        if (tank.life <= 0) {
            find = false;
        }
    }

    public void setBarrierLifeTimes(){
        barrierLifeTimes = new ArrayList<>();
        if (barriers != null) {
            for (Barrier barrier : barriers){
                barrierLifeTimes.add(barrier.barrierLeft);
                barrierLifeTimes.add(barrier.barrierRight);
                barrierLifeTimes.add(barrier.barrierTop);
                barrierLifeTimes.addAll(barrier.barrierNormals);
            }
        }
    }

    public void setInvaderDirectinImage(){
        if (invaders.get(0).move == InvaderDirection.moveDown1 || invaders.get(0).move == InvaderDirection.moveDown2) {
            for (int i = 0; i < invaders.size(); i++) {
                if (find)
                    invaders.get(i).setImage(invaders.get(i).moveDown);
            }
            countMove = 8;
        } else {
            for (int i = 0; i < invaders.size(); i++) {
                if (find)
                    invaders.get(i).setImage(invaders.get(i).moveHorizontal);
            }
            countMove = 30;
        }
    }



    public void settings() {
        size(640, 480);

    }
    //    check the score of power invader
    public static void checkPowerInvaderScore(Invader invader){
        App.score += 250;
    }
    //    check the socre of armoured invader
    public static void checkArmouredInvaderScore(Invader invader) {
        if (((ArmouredInvader) invader).checkLifeTime()) {
            App.score += 250;
        }
    }
    //    show the tank's life time
    public void showTankLiftTime(){
        switch (tank.life) {
            case 1:
                image(loadImage("tank1.png"), 10, 80);
                break;
            case 2:
                image(loadImage("tank1.png"), 10, 80);
                image(loadImage("tank1.png"), 10, 110);
                break;
            case 3:
                image(loadImage("tank1.png"), 10, 80);
                image(loadImage("tank1.png"), 10, 110);
                image(loadImage("tank1.png"), 10, 140);
                break;
        }
    }


    public static void checkScore(Invader invader){
        if (invader instanceof PowerInvader){
            checkPowerInvaderScore(invader);
        } else if (invader instanceof ArmouredInvader){
            checkArmouredInvaderScore(invader);
        } else {
            App.score += 100;
        }
    }

    public void invaderChangeDirection(){
        for (Invader invader : invaders){
            invader.change();
        }
    }

    public boolean checkBarriers(){
        for (int i = 0; i < barriers.size(); i++){
            if (barriers.get(i).barrierLeft.getLifeTime() > 0)
                return false;
            if (barriers.get(i).barrierRight.getLifeTime() > 0)
                return false;
            if (barriers.get(i).barrierTop.getLifeTime() > 0)
                return false;
            for (int j = 0; j < barriers.get(i).barrierNormals.size(); j++){
                if (barriers.get(i).barrierNormals.get(j).getLifeTime() > 0)
                    return false;
            }
        }
        return true;
    }

    public void checkInvaderCollision(){
        for (Invader invader : invaders) {
            for (int j = 0; j < projectiles.size(); j++) {
                if (check_collection(invader, projectiles.get(j)) && projectiles.get(j).speed == 1) {
                    if (invader instanceof ArmouredInvader) {
                        ((ArmouredInvader) invader).minusLife();
                        if (((ArmouredInvader) invader).checkLifeTime()) {
                            removeInvader.add(invader);
                        }
                    } else {
                        removeInvader.add(invader);
                    }
                    checkScore(invader);
                    removeProjectile.add(projectiles.get(j));
                }
            }
        }
    }

    public void checkBarrierCollision(){
        for (Projectile projectile : projectiles){
            for (int i = 0; i < barrierLifeTimes.size(); i++){
                if (check_collection(barrierLifeTimes.get(i),projectile)){
                    if (barrierLifeTimes.get(i).lifeTime > 0)
                        removeProjectile.add(projectile);
                    if (projectile instanceof PowerProjectile) {
                        barrierLifeTimes.get(i).setLifeTime(3);
                    } else {
                        barrierLifeTimes.get(i).setLifeTime(1);
                    }
                }
            }
        }
    }

    public ArrayList<Invader> updateInvader(){
        ArrayList<Invader> newInvader = new ArrayList<>();
        for (Invader invader : invaders) {
            boolean found = false;
            for (Invader inv : removeInvader) {
                if (invader == inv)
                    found = true;
            }
            if (!found)
                newInvader.add(invader);
        }
        return newInvader;
    }

    public void checkTankCollision(){
        for (Projectile projectile : projectiles) {
            if (tank.setLife(projectile)) {
                removeProjectile.add(projectile);
            }
        }
    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            tank.setMoveLeft(true);
        } else if(keyCode == RIGHT) {
            tank.setMoveRight(true);
        } else if(keyCode == UP){
            fire = true;
        }
    }

    public void keyReleased() {
        if (keyCode == LEFT){
            tank.setMoveLeft(false);
        } else if(keyCode == RIGHT) {
            tank.setMoveRight(false);
        } else if(keyCode == UP){
            fire = false;
        }
    }

    public static <T extends Game> boolean check_collection(T r1,T r2){
        return r1.x < (r2.x + r2.image.width) && (r1.x + r1.image.width) > r2.x && r1.y < (r2.y + r2.image.height) && (r1.image.height + r1.y) > r2.y;
    }

    public void draw() {
        if (drawMenu) {
            textSize(20);
            background(0);
            image(loadImage("images.jpg"),-60,-100);
            text("START THE GAME", 180, 250);
            if (choose){
                background(0);
                image(loadImage("images.jpg"),-60,-100);
                text("EAZY",200,180);
//                System.out.println(mousex);
                text("NORM",200,280);
                text("HARD",200,380);
            }
        }
        if (drawing) {
            drawMenu = false;
            if (invaders.size() > 0) {
                state.changeTank(this.tank);
//                System.out.println(tank.tankFireRate);
//              System.out.println(state instanceof Hard);
                currentTime = System.currentTimeMillis();
                background(0);
                Random random = new Random();
                int index = 0;
                if (find) {
                    image(loadImage("WechatIMG5.jpeg"), -38, -30);
                    showTankLiftTime();
                }
                String a = "score";
                textSize(15);
                text(a, 10, 20);
                text(score, 100, 20);
                text("HIGHEST SCORE",440,20);
                text(highestScore, 530, 40);
                removeInvader = new ArrayList<>();
                removeProjectile = new ArrayList<>();
//                check invader collision here
                checkInvaderCollision();
//                update invaders
                this.invaders = updateInvader();

                //            check barrier collision here
                checkBarrierCollision();
                //            check tank hit by projectile
                checkTankCollision();

                checktankLose();

                ArrayList<Projectile> newProjectiles = new ArrayList<>();
                for (Projectile projectile : projectiles) {
                    boolean found = false;
                    for (Projectile pro : removeProjectile) {
                        if (projectile == pro)
                            found = true;
                    }
                    if (!found)
                        newProjectiles.add(projectile);
                }
                this.projectiles = newProjectiles;

                BarrierImage();
                if (find) {
                    updateBarrierImage();

                }


                if (checkBarriers()) {
                    background(0);
                    find = false;
                }
                if (!find) {
                    if (score > highestScore)
                        highestScore = score;
                    image(loadImage("gameover.png"), 275, 240);
                    if (restart == 120){
                        restart = 0;
                        setup();
                        score = 0;
                        fireRate = 360;
                        counterTime = 0;
                    }
                    restart++;

                }

                // draw all objects
                drawInvader();
                drawTank();
                if (countMove == 0) {
                    invaderChangeDirection();
                    setInvaderDirectinImage();
                }

                if (counter % 2 == 0) {
                    if (invaders.size() > 0) {
                        if (find){
                            for (Invader invader : invaders){
                                invader.setMove();
                            }
                        }
                        countMove--;
                    }
                }
                checkInvaderLose();
                if (invaders.size() > 0) {
                    index = random.nextInt(invaders.size());
                }


                // projectile
                for (Projectile p : projectiles) {
                    if (find)
                        image(p.image, p.x, p.y);
                }
                // invader
                if (canFire()) {
                    invaderFireProjectile(index);
                }

                // game logic
                // movement
                tankMove();

                if (fire) {
                    if (tank.canFire()) {
                        Projectile newP = tank.fire(projectile.image);
                        projectiles.add(newP);
                    }
                }

                for (Projectile p : projectiles) {
                    if (find)
                        p.move();
                }

                tank.fireCount++;
                counter++;
                invaderFire++;
                if (counterTime % 60 == 0) {
                    time = counterTime / 60;
                }
                String times = " s";
                textSize(15);
                if (find)
                    text(time + times, 10, 60);
                counterTime++;
            } else {
                image(sucess, 640 / 2 - sucess.width / 2, 480 / 2);
                if (System.currentTimeMillis() - currentTime > 2000) {
                    setup();
                }
            }
        }




    }
    // check collection

    // fire




//    // 3 boolean





    public static void main(String[] args) { PApplet.main("invadem.App"); }

}

