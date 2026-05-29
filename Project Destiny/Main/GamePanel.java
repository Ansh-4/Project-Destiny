package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import tile.Tilemanager;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    //works as a game screen
    //Screen setting
    final int originalTilesize = 16; //16x16 tile (Standard size for many 2d games)
    final int scale = 3;

    public final int tilesize = originalTilesize * scale; // 48x48 tile

    // TOTAL SCREEN SIZE
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 14;
    public final int screenWidth = tilesize * maxScreenCol; //960 pixels

    public final int screenHeight =  tilesize * maxScreenRow; // 864 pixels

    //FPS
    int fps = 60;

    //tile manager
    Tilemanager tileM = new Tilemanager(this);



    // using a thread aka light weight process to create a concept of time in our game
    Thread gameThread;

    //keyinput
    movement m1 = new movement();

    // Entity 1 = player
    Player player = new Player(this, m1);

    


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(m1);
        this.setFocusable(true);


    }
    public void startGameThread(){
        gameThread = new Thread(this); //thread initialization using thread constructor
        gameThread.start();
    }
    @Override
    public void run(){
        //game loop
        double drawInterval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + drawInterval; //allocated time for every loop is 0.01666 secondss
        long timer = 0;
        int drawCount = 0;
            
        while(gameThread != null){
            // UPDATE INFORMATION SUCH AS CHARACTER POSITION AND DRAW THE SCREEN WITH THE UPDATED INFORMATION

            update();

            repaint(); //calling paint component method

            long lastTime = System.nanoTime();
            long currenTime;
            try{
                currenTime = System.nanoTime();
                double remainingTime = nextDrawTime - currenTime;
                //sleep method only accepts milliseconds and hence we need to convert the nanoseconds into milliseconds

                remainingTime = remainingTime / 1000000;
                timer += (currenTime - lastTime);
                if(remainingTime<0){
                    remainingTime = 0;
                    drawCount ++;
                }

                Thread.sleep((long)remainingTime); //loop stays inactive for the remaining time
                nextDrawTime += drawInterval;
                if(timer >= 1000000000){
                    System.out.println("FPS:"+drawCount);
                }
            } catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }
    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g){
        //standard method provided by java to draw or paint on panel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; //convert
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose(); //to save some memory
        

    }




}
