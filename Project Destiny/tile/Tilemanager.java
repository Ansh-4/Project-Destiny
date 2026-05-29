package tile;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Tilemanager {
    GamePanel gp;
    tile[] Tile;
    int mapTileNumber[] [];
    public Tilemanager(GamePanel gp){
        this.gp = gp;
        Tile = new tile[10]; //10 types of tiles
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();

    }
    public void getTileImage(){
        try{

            Tile[0] = new tile();
            Tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            Tile[1] = new tile();
            Tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));

            Tile[2] = new tile();
            Tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));


        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/res/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col<gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt((numbers[col]));

                    mapTileNumber[col] [row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col<gp.maxScreenCol && row<gp.maxScreenRow){
            int tileNum = mapTileNumber[col][row];
            g2.drawImage(Tile[tileNum].image, x, y, gp.tilesize, gp.tilesize, null);
            col++;
            x += gp.tilesize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tilesize;
            }
        }
    }


}
