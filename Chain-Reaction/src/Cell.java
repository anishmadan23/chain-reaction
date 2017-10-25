import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Cell implements Serializable {

    private int orbs;
    private int X_Coordinate;
    private int Y_Coordinate;
    public int rows = 9;
    public int cols = 6;
    public Cell[][] grid;

    public Cell(int orbs1) {
        this.orbs = orbs1;
    }

    public Cell(int x1, int y1, int orbs1) {
        this.X_Coordinate = x1;
        this.Y_Coordinate = y1;
        this.orbs = orbs1;
        this.grid = new Cell[rows][cols];
    }


    public Cell(int x1, int y1, int orbs1, Cell[][] grid1) {
        this.X_Coordinate = x1;
        this.Y_Coordinate = y1;
        this.orbs = orbs1;
        this.grid = grid1;
    }

    public Cell( Cell[][] grid1) {
        this.grid = grid1;
    }

    public Cell() {
        this.orbs = 0;
    }

    public int getOrbs() {
        return orbs;
    }

    public void setOrbs(int orbs) {
        this.orbs = orbs;
    }

    public int getCriticalMass(int i, int j) {
        if (isCorner(i, j)){System.out.println("In corner");
            return 2;}
        else if (isEdge(i, j))
            return 3;
        else
            return 4;
    }

    private boolean isCorner(int i, int j) {

        return ((i == 0 && j == 0) || (i == rows - 1 && j == 0) || (i == 0 && j == cols - 1) ||
                (i == rows - 1 && j == cols - 1));
    }

    private boolean isEdge(int i, int j) {
        return ((i == 0) || (j == 0) || (i == rows - 1) || (j == cols - 1));
    }

    public boolean isValidNeighbour(int i, int j) {
        return (i >= 0 && i < rows && j >= 0 && j < cols);
    }


    public Queue<Coordinates> getNeighbours(int i, int j) {
        Deque<Coordinates> queue = new LinkedList<>();
        if (isValidNeighbour(i + 1, j))
            queue.add(new Coordinates(i + 1, j));
        if (isValidNeighbour(i, j + 1))
            queue.add(new Coordinates(i, j + 1));
        if (isValidNeighbour(i - 1, j))
            queue.add(new Coordinates(i - 1, j));
        if (isValidNeighbour(i, j - 1))
            queue.add(new Coordinates(i, j - 1));

        return queue;
    }

    public void explosion(int i, int j) {        //requires an initial click on the cell
        System.out.println("Inside explosion "+grid[i][j].getOrbs()+" "+"Coord "+i+" "+j);
        grid[i][j].setOrbs(grid[i][j].getOrbs() + 1);
        if (grid[i][j].getOrbs() < getCriticalMass(i, j))
            return;
        else{
            grid[i][j].setOrbs(0);
            Queue<Coordinates> queue = getNeighbours(i,j);
            ArrayList<Coordinates> a = new ArrayList<>(queue);
            for(int f = 0;f<a.size();f++){
                System.out.println("Neighbours "+a.get(f).getX()+" "+a.get(f).getY());
            }
            int length = queue.size();
            System.out.println("Size : "+length);
            for(int l = 0; l<length;l++){
                Coordinates cxy = queue.poll();
                explosion(cxy.getX(),cxy.getY());
            }
        }




//    private LinkedList<Cell> getOrthogonallyAdjacentCells(int i, int j,int r, int c){
//        LinkedList<Cell> OrthAdjCells = new LinkedList<>();
//        if(isCorner(i,j,r,c)){
//
//        }
//        return OrthAdjCells;
//    }

    }
}

