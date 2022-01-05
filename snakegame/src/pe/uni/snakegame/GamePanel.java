package pe.uni.snakegame;
import pe.uni.snakegame.registry.snakeRegistry;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

   static final int SCREEN_WIDTH = 600;
   static final int SCREEN_HEIGHT = 600;
   static final int UNIT_SIZE = 25; // 25x25
   static final int GAME_UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH) / UNIT_SIZE * UNIT_SIZE;
   static final int DELAY = 75;
   static final int REREDUCTION_SLOPE = 1;
   static final int REDUCTION_TIME_UNITS = 30;
   static int generation_time = 100 * DELAY; // ms //debe ser mayor o igual a DELAY
   boolean running = false;

   static final boolean PanelArray[][] = new boolean[SCREEN_WIDTH/UNIT_SIZE][SCREEN_HEIGHT/UNIT_SIZE];
   final int x[] = new int[GAME_UNITS];
   final int y[] = new int[GAME_UNITS];
   
   
   static boolean go_left = false;
   static boolean go_right = true;
   static boolean go_up = false;
   static boolean go_down = false;

   snakeRegistry registroSnakes = new snakeRegistry();
   int body_parts = 6;
   int snake_x;
   int snake_y;
   long gameClock;

   Timer timer;
   Random random = new Random();

   GamePanel() {
      this.setPreferredSize(new DimensionUIResource(SCREEN_WIDTH, SCREEN_HEIGHT));
      // 122, 14, 16
      this.setBackground(Color.black);
      this.setFocusable(true);
      this.addKeyListener(new MyKeyAdapter());
      startGame();

   }

   public void move() {
      for (int i = body_parts; i > 0; i--) {
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

      //a new check collisions

   }

   public void checkCollisions() {


      if (x[0] >= SCREEN_WIDTH || x[0] < 0) {
         running = false;
      }
      if (y[0] >= SCREEN_HEIGHT || y[0] < 0) {
         running = false;
      }

      // printPanel();
      for (int i = 0; i <body_parts && running; i++) {
         if (PanelArray[x[i]/UNIT_SIZE][y[i]/UNIT_SIZE]) {
            System.out.println("Se ha colisionado con una snake");
            running = false;
            break;
         }  
      }
      
      for (int i = body_parts; i > 0; i--) {

         if ((x[0] == x[i]) && (y[0] == y[i])) {
            running = false;
         }

         //LAST CHECK
         if (!running) {
            timer.stop();
            //gmaeOver();
            break;
         }

      }
   }

   public void startGame() {
      running = true;
      timer = new Timer(DELAY, this);
      timer.start(); // esto hace que el juego funcione, recomendacion: no leer la documentacion.
   }

   @Override
   public void paintComponent(Graphics graphic) {
      super.paintComponent(graphic);
      draw(graphic);
   }

   public void printPanel(){
      // for(int l=0;l<body_parts;l++){
      //    PanelArray[x[l]/(UNIT_SIZE)][y[l]/(UNIT_SIZE)] = true;
      // }
      for(int i=0;i<body_parts && running;i++){
         System.out.print(x[i]/UNIT_SIZE+" ");
         System.out.print(y[i]/UNIT_SIZE+" |");
         if(PanelArray[x[i]/UNIT_SIZE][y[i]/UNIT_SIZE]) System.out.print(" COL");
      }

      // System.out.println();
      // for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
      //    System.out.println();
      //    for(int j=0;j<SCREEN_WIDTH/UNIT_SIZE;j++){
      //    boolean founded = false;
      //    for(int l=0;l<body_parts;l++){
      //       if(x[l]/(UNIT_SIZE)==j && y[l]/(UNIT_SIZE)==i) {System.out.print(1+""); founded = true;}
      //    }
      //    if(!founded) System.out.print(0+"");
      //    }
      // }

      System.out.println();

      for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
         System.out.println();
         for(int j=0;j<SCREEN_WIDTH/UNIT_SIZE;j++){

         if(i==20 && j==10) {System.out.print("S"); continue;}
         if(PanelArray[i][j]) System.out.print("1");
         else System.out.print("0");

         }
      }
      System.out.println();
   }

   public void draw(Graphics g) {
      if(running){
         for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
         }

         for (int i = 0; i < body_parts; i++) {
            if (i == 0) {
               g.setColor(Color.red);
               g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
               g.setColor(Color.green);
               g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
         }
         
         registroSnakes.renderSnakes(g);
      }
      else{ gameOver(g);}
   }

   public void gameOver(Graphics g) {
      g.setColor(Color.red);
      g.setFont( new Font("Ink Free",Font.BOLD,55));
      FontMetrics metrics = getFontMetrics(g.getFont());

      String display = "ACOSTAAAAAAAAAA";
      g.drawString(display ,(SCREEN_WIDTH - metrics.stringWidth(display))/2, SCREEN_HEIGHT/2);
   }
   
   public void newSnakeEnemy() {
      // snake_x = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
      // snake_y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
      snake_x = 20 * UNIT_SIZE;
      snake_y = 10* UNIT_SIZE;
      registroSnakes.add(snake_x, snake_y); // esto es la generacion de una nueva EnemySnake
   }

   @Override
   public void actionPerformed(ActionEvent event) { // Timer
      if (running) {
         gameClock += DELAY;
         move();
         checkCollisions();
         
         if(gameClock %  REDUCTION_TIME_UNITS * DELAY == 0 && generation_time!=DELAY * 3)
            refreshGenTime();
      
         if (gameClock % generation_time == 0) {
            newSnakeEnemy();
         }
      }
      repaint();
      // printPanel();
      // checkCollisions();
   }

   public void refreshGenTime() {
      generation_time -= REREDUCTION_SLOPE * DELAY;
   }

   public class MyKeyAdapter extends KeyAdapter {

      @Override
      public void keyPressed(KeyEvent event) {
         switch (event.getKeyCode()) {

            case KeyEvent.VK_LEFT:
               if (go_right) break;
               go_left = true;
               go_right = false;
               go_up = false;
               go_down = false;
               break;

            case KeyEvent.VK_UP:
               if (go_down) break;
               go_left = false;
               go_right = false;
               go_up = true;
               go_down = false;
               break;

            case KeyEvent.VK_RIGHT:
               if (go_left) break;
               go_left = false;
               go_right = true;
               go_up = false;
               go_down = false;
               break;

            case KeyEvent.VK_DOWN:
            if (go_up) break;
               go_left = false;
               go_right = false;
               go_up = false;
               go_down = true;
               break;
         }
      }
   }

}
