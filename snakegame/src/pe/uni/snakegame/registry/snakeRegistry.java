package pe.uni.snakegame.registry;

import java.util.ArrayList;
import java.awt.Graphics;
import pe.uni.snakegame.*;

public class snakeRegistry {
   static final int maxSnakes = 10;
   public ArrayList<Snake> entRegistry = new ArrayList<>();
   
   snakeRegistry(){
      for(int i=0;i<maxSnakes;i++){
         entRegistry.add(new Snake());
      }   
   }

   public void add(Graphics g, int x, int y) {
      for(int i=0;i<entRegistry.size();i++){
         if(!entRegistry.get(i).is_alive){
            entRegistry.get(i).setSnake(g,x,y);
         }
      }
   }

   public void renderSnakes(Graphics g){
      for(Snake s : this.entRegistry){
         if(s.is_alive){s.render(g); }
      }
   }
   
}
