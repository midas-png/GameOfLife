package gameofllife;

import java.util.Random;


public class GameOfLife {
    int x;
    int y;
    int[][] board;
    
    public GameOfLife(int x, int y){
        this.x = x;
        this.y = y;
        this.board = new int[x][y];
    }
    
    public static void main(String[] args) throws InterruptedException {
        int x=14;
        int y=7;
        GameOfLife g = new GameOfLife(x, y);
        for(int i=0; i<15; i++){
            g.setAlive(new Random().nextInt(x), new Random().nextInt(y));
        }
        
        while(true){
            Thread.sleep(1000);
            g.getBoard();
            g.checkDef();
        }
    }
    
    public void getBoard(){
        for(int i=0; i<this.y; i++){
            String unit = "|";
            for(int j=0; j<this.x; j++){
                if(this.board[j][i] != 0){
                    unit += "#";
                } else {
                    unit += " ";
                }
            }
            unit += "|";
            System.out.println(unit);
        }
        System.out.println("\n");
    }
    
    public int isAlive(int x, int y){
        if(x<0 || x>= this.x){
            return 0;
        }
        if (y<0 || y>= this.y){
            return 0;
        }
        
        return this.board[x][y];
    }
    
    
    public void setAlive(int x, int y){
        this.board[x][y] = 1;
    }
    
    public int countAlive(int x, int y){
        int count = 0;
 
        count += isAlive(x - 1, y - 1);
        count += isAlive(x - 1, y);
        count += isAlive(x - 1, y + 1);  
        count += isAlive(x, y - 1);
        count += isAlive(x, y + 1);
        count += isAlive(x + 1, y - 1);
        count += isAlive(x + 1, y);
        count += isAlive(x + 1, y + 1);        
        return count;
    }
    
    
    public void checkDef(){
        for(int i=0; i<this.y; i++){
            for(int j=0; j<this.x; j++){ 
                int aliveN = countAlive(j, i);
                
                if(this.board[j][i] == 1){ //Для живых
                    if(aliveN < 2){
                        this.board[j][i] = 0;
                    } else if(aliveN == 2 || aliveN == 3){
                        this.board[j][i] = 1;
                    } else if(aliveN > 3){
                        this.board[j][i] = 0;
                    }
                } else {
                    if(aliveN == 3){
                        this.board[j][i] = 1;
                    } 
                }
            }   
        }
    }
    
}