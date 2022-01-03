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
   static final int UNIT_SIZE = 25; //25x25
   static final int DELAY = 75;
   static final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT_SIZE*UNIT_SIZE;
   static int GENERATION_TIME = 10000; //ms
   
   final int x[] = new int[GAME_UNITS];
   final int y[] = new int[GAME_UNITS];
   int body_parts = 6;
   int snake_x;
   int snake_y;
   boolean running = false;

   //dirección
   static boolean go_left = false;
   static boolean go_right = true;
   static boolean go_up = false;
   static boolean go_down = false;

   int applesEaten;
   int gameClock;
   Timer timer;
   Random random = new Random();
   
   
   GamePanel(){
      
      this.setPreferredSize(new DimensionUIResource(SCREEN_WIDTH, SCREEN_HEIGHT));
      //122, 14, 16  
      this.setBackground(Color.black);
      this.setFocusable(true);
      this.addKeyListener(new MyKeyAdapter());
      startGame();

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

// estoy copiando el codigo del video :v
   public void checkCollisions() {
      for (int i = body_parts; i > 0; i--) {
         if ((x[0] == x[i]) && (y[0] == y[i])) {
            running = false;
         }
         if (x[0] > SCREEN_WIDTH || x[0] < 0) {
            running = false;
         }
         if (y[0] > SCREEN_HEIGHT || y[0] < 0) {
            running = false;
         }
         if (!running) {
            timer.stop();
         }
      }
   }
// fin del copiado

/*

esto para qué es? no sirve parece

   public void gameComponent(Graphics graphics){

   }
*/

   public void startGame(){

      newSnakeEnemy();
      running  = true; 
      timer = new Timer(DELAY, this);
      timer.start(); // esto hace que el juego funci0ne, recomendacion: no leer la documentacion.
   }


   @Override
   public void paintComponent(Graphics graphic){ //si modifico un parámetro aqui se muere el método??
      super.paintComponent(graphic);
      draw(graphic);
   }


   public void draw(Graphics g){ 
      for(int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++){
         g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
         g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
      }

      //for(int i = 0; i < )
      g.setColor(Color.red);
      g.fillRect(snake_x, snake_y, UNIT_SIZE, UNIT_SIZE);

      // la cabecita de la serpiente
      for (int i = 0; i < body_parts; i++) {
         if (i == 0) {
            g.setColor(Color.red);
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
         }
         else {
            g.setColor(Color.green);
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
         }
      }
   }

   public void newSnakeEnemy(){

      snake_x = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
      snake_y = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;

      new Snake(this, snake_x, snake_y); //esto es la generacion de una nueva EnemySnake
   }

   @Override
   public void actionPerformed(ActionEvent event){ //Timer
      if (running){
         gameClock += DELAY;
         move();
         checkCollisions();
      }
      repaint();
   }

   public class MyKeyAdapter extends KeyAdapter {

      @Override
      public void keyPressed(KeyEvent event){
                   
         switch(event.getKeyCode()){

            case KeyEvent.VK_LEFT: //izquierda
               go_left = true;
               go_right = false;
               go_up = false;
               go_down = false;
               break;

            case KeyEvent.VK_UP: //ariba
               go_left = false;
               go_right = false;
               go_up = true;
               go_down = false;
               break; 
               
            case KeyEvent.VK_RIGHT://derecha
               go_left = false;
               go_right = true;
               go_up = false;
               go_down = false;
               break; 

            case KeyEvent.VK_DOWN: //abajo
               go_left = false;
               go_right = false;
               go_up = false;
               go_down = true;
               break;

         }
      }
   }
   
}
