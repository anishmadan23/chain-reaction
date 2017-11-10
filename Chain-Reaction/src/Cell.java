import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Cell implements Serializable {

    private int orbs;
    private int X_Coordinate;
    private int Y_Coordinate;
//    public int rows = 15;
//    public int cols = 10;
    public Cell[][] grid;

    public Cell(int orbs1) {
        this.orbs = orbs1;
    }

    public Cell(int x1, int y1, int orbs1,int rows,int cols) {
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
        this.orbs = 0;
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

    public int getCriticalMass(int i, int j,int rows,int cols) {
        if (isCorner(i, j,rows, cols)){System.out.println("In corner");
            return 2;}
        else if (isEdge(i, j,rows,cols))
            return 3;
        else
            return 4;
    }

    private boolean isCorner(int i, int j,int rows, int cols) {
        return ((i == 0 && j == 0) || (i == rows - 1 && j == 0) || (i == 0 && j == cols - 1) ||
                (i == rows - 1 && j == cols - 1));
    }

    private boolean isEdge(int i, int j,int rows, int cols) {
        return ((i == 0) || (j == 0) || (i == rows - 1) || (j == cols - 1));
    }

    public boolean isValidNeighbour(int i, int j,int rows, int  cols) {
        return (i >= 0 && i < rows && j >= 0 && j < cols);
    }


    public Queue<Coordinates> getNeighbours(int i, int j,int rows, int cols) {
        Deque<Coordinates> queue = new LinkedList<>();
        if (isValidNeighbour(i + 1, j,rows,cols))
            queue.add(new Coordinates(i + 1, j));
        if (isValidNeighbour(i, j + 1,rows,cols))
            queue.add(new Coordinates(i, j + 1));
        if (isValidNeighbour(i - 1, j,rows,cols))
            queue.add(new Coordinates(i - 1, j));
        if (isValidNeighbour(i, j - 1,rows,cols))
            queue.add(new Coordinates(i, j - 1));
        return queue;
    }

    public void explosion(int i, int j, Grid g,int rows, int cols)
    {        //requires an initial click on the cell
        System.out.println("Inside explosion " + this.grid[i][j].getOrbs() + " " + "Coord " + i + " " + j);
        this.grid[i][j].setOrbs(this.grid[i][j].getOrbs() + 1);
        if (this.grid[i][j].getOrbs() < getCriticalMass(i, j,rows,cols))
        {
            this.grid = g.createSphere(j,i,this);
            return;
        }
        else
        {
//            this.grid[i][j].setOrbs(0);

            System.out.println(this.grid[i][j].getOrbs()+"Sfsdfsfesfsdsed");
            Queue<Coordinates> queue = getNeighbours(i, j,rows, cols);
            ArrayList<Coordinates> a = new ArrayList<>(queue);
            for (int f = 0; f < a.size(); f++) {
                System.out.println("Neighbours " + a.get(f).getX() + " " + a.get(f).getY());
            }
            int length = queue.size();
            System.out.println("Size : " + length);
            for (int l = 0; l < length; l++) 
            {
                Coordinates cxy = queue.poll();
                g.shiftOrbs(i,j,cxy.getX(),cxy.getY(),this);
                this.grid[i][j].setOrbs(0);
                g.animation1.setOnFinished(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        System.out.println(cxy.getX()+"   "+cxy.getY());
                        System.out.println(g.root1[cxy.getX()][cxy.getY()].getChildren().remove(0));
//                        g.root1[cxy.getX()][cxy.getY()].getChildren().remove(g.sphere11);
                        //g.root1[cxy.getX()][cxy.getY()].getChildren().remove(g.line);
                        explosion(cxy.getX(), cxy.getY(),g,rows,cols);

                    }
                });
                

            }
//            return grid;
        }
    }
}

