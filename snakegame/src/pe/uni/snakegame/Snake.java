package pe.uni.snakegame;

import java.util.Random;
import java.lang.Math;

public class Snake { // snakeEnemy snakePlayer

   Random random = new Random();

   Snake(GamePanel panel, int x, int y) {
      direcction();
   };
   
   char orientation = 'R'; // L R U D   
   final int body_parts = random.nextInt(Math.min((GamePanel.SCREEN_HEIGHT/GamePanel.UNIT_SIZE)-1,(GamePanel.SCREEN_WIDTH/GamePanel.UNIT_SIZE)-1) ) + 1;
   final int y[] = new int[GamePanel.GAME_UNITS];
   final int x[] = new int[GamePanel.GAME_UNITS];
   

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
            x[0] += GamePanel.UNIT_SIZE;
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
      
      int numberDirecction = random.nextInt(3)+1;
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
   