package pe.uni.snakegame.registry;
import pe.uni.snakegame.*;

import java.util.ArrayList;
import java.awt.Graphics;

public class snakeRegistry {
   static final int maxSnakes = 10;
   public ArrayList<Snake> entRegistry = new ArrayList<>();
   
   public snakeRegistry(){
      for(int i=0; i<maxSnakes; i++){
         entRegistry.add(new Snake());
      }   
   }

   public void add(int x, int y) {
      for(int i=0; i<entRegistry.size(); i++){
         if(!(entRegistry.get(i).is_alive)){
            entRegistry.get(i).setSnake(x, y);
            break;
         }
      }
   }

   public void addToPanelArr(){
      for(Snake s : this.entRegistry){
         if (s.is_alive) {
            s.addToPanelArr();
         }
      }
   }

   public void renderSnakes(Graphics g){
      for(Snake s : this.entRegistry){
         if (s.is_alive) {
            s.render(g);
         }
      }
   }
   
}
