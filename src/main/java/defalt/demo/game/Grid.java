package defalt.demo.game;

public class Grid {
    private byte [][] grid = new byte[3][3];
    private byte winner = 0;

    public static void main(String[] args) {
        Grid g =new Grid();
        g.showGrid();
    }
    public void showGrid(){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    System.out.print(this.grid[i][j]+" ");
                }
                System.out.println("");
            }
    }

    public Grid() {

    }
    public void playTurn(){

        if(isWin()){
            this.winner= 1;
        }
        if(draw()){
            this.winner= 2;
        }
    }

    public boolean isWin(){
        for(int i=0;i<3;i++){
            if(this.grid[i][0]==this.grid[i][1] && this.grid[i][1]==this.grid[i][2] && this.grid[i][0]!=0 ){return true; }
            if(this.grid[0][i]==this.grid[1][i] && this.grid[1][i]==this.grid[2][i]&& this.grid[0][i]!=0){return true; }
        }
        if(this.grid[0][0]==this.grid[1][1] && this.grid[1][1]==this.grid[2][2] && this.grid[0][0]!=0){return true; }
        if(this.grid[0][2]==this.grid[1][1] && this.grid[1][1]==this.grid[2][0] && this.grid[2][0]!=0){return true; }
        return false;
    }


    public boolean draw(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.grid[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }

    public byte[][] getGrid() {
        return grid;
    }

    public void setGrid(byte[][] grid) {
        this.grid = grid;
    }

    public byte getWinner() {
        return winner;
    }
}
