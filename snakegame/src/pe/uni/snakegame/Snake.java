package pe.uni.snakegame;

import java.awt.*;
import java.util.Random;
import java.lang.Math;

public class Snake {

   Random random = new Random();
   char orientation = 'L';  
   public int body_parts = 4;
   final int MAX_SNAKE = Math.min(
         (GamePanel.SCREEN_HEIGHT/GamePanel.UNIT_SIZE)-1,
         (GamePanel.SCREEN_WIDTH/GamePanel.UNIT_SIZE)-1 );
   final int y[] = new int[MAX_SNAKE];
   final int x[] = new int[MAX_SNAKE];
   public boolean is_alive = false;

   public Snake(){
      for(int i=0; i<MAX_SNAKE; i++){
          this.x[i] = -1*GamePanel.UNIT_SIZE;
          this.y[i] = -1*GamePanel.UNIT_SIZE;
      }
   };

   public void setSnake(int x, int y) {
      this.is_alive = true;
      this.x[0] = x;
      this.y[0] = y;
         
      while (true) {
         direcction();
         if(!(
            (this.x[0] == 0 && this.orientation == 'L') ||
            (this.y[0] == 0 && this.orientation == 'U') || 
            (this.x[0] == GamePanel.SCREEN_WIDTH/GamePanel.UNIT_SIZE && this.orientation == 'R') ||
            (this.y[0] == GamePanel.SCREEN_HEIGHT/GamePanel.UNIT_SIZE && this.orientation == 'D'))){ 
               break;
         }
      }

      /*
      switch(orientation){

         case 'L':
            for(int i =0; i<body_parts; i++){
               this.y[i+1] = this.y[i];
               this.x[i+1] = this.x[i] + 1;
            }
            break;
         
         case 'U':
            for(int i =0; i<body_parts; i++){
               this.y[i+1] = this.y[i]+1;
               this.x[i+1] = this.x[i];
            }
            break;
         
         case 'R':
            for(int i =0; i<body_parts; i++){
               this.y[i+1] = this.y[i];
               this.x[i+1] = this.x[i]-1;
            }
            break;

         case 'D':
            for(int i =0; i<body_parts; i++){
               this.y[i+1] = this.y[i]-1;
               this.x[i+1] = this.x[i];
            }
            break;
      }
      */
      
   };

   public void render(Graphics g){
      checkCollisions();
      if (is_alive) {
         move();
         draw(g);
      } 
      else { 
         delSnake(g);
      }
   }

   public void draw(Graphics g){
      for (int i=0; i<body_parts; i++) {

         //draw to the panel array
         int coordx= this.x[i];
         int coordy= this.y[i];  
         GamePanel.PanelArray[coordx][coordy]=true;

         if (i == 0) {
            // (122,14,16)
            g.setColor(Color.white);
            g.fillRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
         }
         else {
            g.setColor( new Color(122,14,16));
            g.fillRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
         }

      }

   }

   public void checkCollisions(){
      for (int i=body_parts; i>0; i--) {
         if (x[0] > GamePanel.SCREEN_WIDTH || x[0] < 0) {
            is_alive = false;
         }

         if (y[0] > GamePanel.SCREEN_HEIGHT || y[0] < 0) {
            is_alive = false;
         }

      }
   }

   public void delSnake(Graphics g){
      //undraw
      for(int i= 0; i<body_parts;i++){
         x[i] = -1*GamePanel.UNIT_SIZE;
         y[i] = -1*GamePanel.UNIT_SIZE;
         int coordx= this.x[i];
         int coordy= this.y[i];  
         GamePanel.PanelArray[coordx][coordy]=false;
      }
      is_alive = false;
   }
   
   public void addToPanelArr(){
      //
   }







   public void move() {
      for (int i = body_parts; i>0; i--) {
         x[i] = x[i-1];
         y[i] = y[i-1];
      }

      switch (orientation) {
          case 'R':
            x[0] += GamePanel.UNIT_SIZE;
            break;
          case 'L':
            x[0] -= GamePanel.UNIT_SIZE;
            break;
          case 'U':
            y[0] -= GamePanel.UNIT_SIZE;
            break;
          case 'D':
            y[0] += GamePanel.UNIT_SIZE;
            break;

      } 
 
   }

   public void direcction(){
      
      int numberDirecction = random.nextInt(4)+1;
      switch(numberDirecction){
         
         case 1: //izquierda
            this.orientation = 'L';
            break;

         case 2: //arriba
            this.orientation = 'U';
            break;

         case 3: //derecha
            this.orientation = 'R'; 
            break;

         case 4: //abajo
            this.orientation = 'D';         
            break;
      }
   }
   
}
   