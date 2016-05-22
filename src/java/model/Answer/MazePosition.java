package model.Answer;

/**
 *
 * @author Max
 */
public class MazePosition {
    private int row;
    private int col;
    
    public int getRow(){
        return this.row;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public int getCol(){
        return this.col;
    }
    
    public void setCol(int col){
        this.col = col;
    }
    
    @Override
    public String toString(){
        return "(row: " + this.row + ", col: " + this.col + ")";
    }
}
