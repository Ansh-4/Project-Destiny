package Main;

import javax.swing.JFrame;
 class Window {
    public static void main(String[] args) {
        //new window for tha game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        window.setTitle("Project Destiny");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setResizable(false); //makes the game window non resizable
        window.setVisible(true);

        gamePanel.startGameThread();



    }
}