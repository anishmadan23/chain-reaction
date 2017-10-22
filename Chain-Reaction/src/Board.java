import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Board implements Serializable {
    private int rows;
    private int cols;
    private Cell[][] grid;

    public Board(int rows1, int cols1){
        this.rows = rows1;
        this.cols = cols1;
        this.grid = new Cell[this.rows][this.cols];
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void explosion(int i, int j){        //requires an initial click on the cell
        Deque<Cell> queue = new LinkedList<>();
        Cell[][] grid = getGrid();
        int orbCount = grid[i][j].getOrbs();
        queue.add(new Cell(i,j,orbCount));
        while(queue.size()!=0){
            Cell x = queue.pop();

        }







    }


}
