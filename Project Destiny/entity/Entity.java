package entity;

import java.awt.image.BufferedImage;

//super class (parent class) that stores vairables that will be used in player, monster and all the npc classes

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    

}
