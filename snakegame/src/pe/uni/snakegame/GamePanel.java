package pe.uni.snakegame;

// import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
// import javax.swing.text.StyleConstants.ColorConstants;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;


public class GamePanel extends JPanel implements ActionListener {

   static final int SCREEN_WIDTH = 600;
   static final int SCREEN_HEIGHT = 600;
   static final int UNIT_SIZE = 25; //25x25
   static final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_HEIGHT) / UNIT_SIZE;
   final int x[] = new int[GAME_UNITS];
   final int y[] = new int[GAME_UNITS];

   static boolean go_left = false;
   static boolean go_right = true;
   static boolean go_up = false;
   static boolean go_down = false;
   
   
   GamePanel(){
      
      this.setPreferredSize(new DimensionUIResource(SCREEN_HEIGHT, SCREEN_HEIGHT));
      //122, 14, 16  
      this.setBackground(Color.black);
      this.addKeyListener(new MyKeyAdapter());
      // draw(this);
      // startGame();

   }


   public void gameComponent(Graphics graphics ){

   }

   public void startGame(){

   }


   public void paintComponent(Graphics graphic){
      super.paintComponent(graphic);
      draw(graphic);
   }

   public void draw(Graphics g){

      for(int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++){
         g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
         g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent event){
      
   }

   public class MyKeyAdapter extends KeyAdapter{
      @Override
      public void keyPressed(KeyEvent event){

      }
   }
   
}
