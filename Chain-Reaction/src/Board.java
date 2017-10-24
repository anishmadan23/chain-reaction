import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Board implements Serializable {
    private int rows;
    private int cols;
    public Cell[][] grid;

    public Board(int rows1, int cols1){
        this.rows = rows1;
        this.cols = cols1;
        this.grid = new Cell[this.rows][this.cols];
    }








    }



