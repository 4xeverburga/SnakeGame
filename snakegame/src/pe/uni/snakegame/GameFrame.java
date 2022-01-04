package pe.uni.snakegame;


import javax.swing.JFrame;

public class GameFrame extends JFrame{
   GameFrame(){
      this.add(new GamePanel());
      this.setTitle("SnakeGAME");
      this.setResizable(false);
      // this.pack();
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }
}
