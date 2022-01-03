package pe.uni.snakegame;

// import javax.swing.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
// import javax.swing.text.StyleConstants.ColorConstants;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;


public class GamePanel extends JPanel implements ActionListener {

   static final int SCREEN_WIDTH = 600;
   static final int SCREEN_HEIGHT = 600;
   static final int UNIT_SIZE = 300; //25x25
   static final int DELAY = 75;
   static final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT_SIZE*UNIT_SIZE;
   final int x[] = new int[GAME_UNITS];
   final int y[] = new int[GAME_UNITS];
   int body_parts = 6;
   int apple_eaten = 0;
   int apple_x;
   int apple_y;
   boolean running = false;

   //direccion
   static boolean go_left = false;
   static boolean go_right = true;
   static boolean go_up = false;
   static boolean go_down = false;

   int applesEaten;
   Timer timer;
   
   
   GamePanel(){
      
      this.setPreferredSize(new DimensionUIResource(SCREEN_WIDTH, SCREEN_HEIGHT));
      //122, 14, 16  
      this.setBackground(Color.black);
      this.addKeyListener(new MyKeyAdapter());
      // startGame();

   }

   public void move(){

      for (int i = body_parts; i>0; i--) {
         x[i] = x[i-1];
         y[i] = y[i-1];
      }

      if (go_up) {
         y[0] -= UNIT_SIZE;
      } else if (go_down) {
         y[0] += UNIT_SIZE;
      } else if (go_left) {
         x[0] -= UNIT_SIZE;
      } else if (go_right) {
         x[0] += UNIT_SIZE;
      }
   }

   public void gameComponent(Graphics graphics){

   }

   public void startGame(){

      newApple();
      running  = true;
      timer = new Timer(DELAY, this);
      timer.start();
   }

   // JPanel() {... paintComponent.()};
   // Graphics g = new Graphics2d();

   @Override
   public void paintComponent(Graphics graphic){

      super.paintComponent(graphic);
      draw(graphic);
   }


   public void draw(Graphics g){

      for(int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++){
         g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
         g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
      }
      g.setColor(Color.red);
      g.fillOval(apple_x, apple_y, UNIT_SIZE, UNIT_SIZE);
      for (int i = 0; i < body_parts; i++) {
         if (i == 0) {
            g.setColor(Color.green);
         }
      }
   }

   public void newApple(){

      apple_x = Random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
      apple_y = Random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
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
