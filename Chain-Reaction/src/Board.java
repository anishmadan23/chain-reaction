import java.io.Serializable;

public class Board implements Serializable {
    private int rows;
    private int cols;
    private Cell[][] grid;

    public Board(int rows1, int cols1){
        this.rows = rows1;
        this.cols = cols1;
        this.grid = new Cell[this.rows][this.cols];
    }

    public void explosion(){
        //code to be written
    }


}
