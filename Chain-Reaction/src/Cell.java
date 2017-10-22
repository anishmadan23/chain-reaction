import java.io.Serializable;
import java.util.LinkedList;

public class Cell implements Serializable {

    private int orbs;
    private int X_Coordinate;
    private int Y_Coordinate;

    public Cell(int orbs1){
        this.orbs = orbs1;
    }
    public Cell(int x1, int y1, int orbs1){
        this.X_Coordinate = x1;
        this.Y_Coordinate = y1;
        this.orbs = orbs1;
    }

    public Cell(){
        this.orbs = 0;
    }

    public int getOrbs() {
        return orbs;
    }

    public void setOrbs(int orbs) {
        this.orbs = orbs;
    }

    public int getCriticalMass(int i, int j, int r, int c){
       if(isCorner(i,j,r,c))
           return 2;
       else if(isEdge(i,j,r,c))
           return 3;
       else
           return 4;
    }

    private boolean isCorner(int i, int j, int r, int c){
        return (( i==0 && j==0 ) || ( i==r-1 && j==0 ) || ( i==0 && j==c-1 ) ||
                ( i==r-1 && j==c-1 ));
    }

    private boolean isEdge(int i, int j, int r, int c){
        return (( i==0 ) || ( j==0 ) || ( i==r-1 ) || ( j==c-1 ));
    }

    private LinkedList<Cell> getOrthogonallyAdjacentCells(int i, int j,int r, int c){
        LinkedList<Cell> OrthAdjCells = new LinkedList<>();
        if(isCorner(i,j,r,c)){

        }
        return OrthAdjCells;
    }

}

