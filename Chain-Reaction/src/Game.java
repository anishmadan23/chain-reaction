import java.util.ArrayList;

public class Game {

    private int rows;
    private int cols;
    private int numberOfPlayers;
    private ArrayList<Players> playersArrayList;
    private Cell[][] gridGame;
//    private Board board;

    public Game(int rows1, int cols1, int numberOfPlayers1){
        this.rows = rows1;
        this.cols = cols1;
        this.numberOfPlayers = numberOfPlayers1;
        this.playersArrayList = new ArrayList<>(this.numberOfPlayers);
//        this.board = new Board(this.rows, this.cols);
//        this.gridGame = board.getGrid();
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public ArrayList<Players> getPlayersArrayList() {
        return playersArrayList;
    }

//    public Board getBoard() {
//        return board;
//    }

    public Cell[][] getGridGame() {
        return gridGame;
    }
    public static void serialize(){}
    public static void deserialize(){}
    public void play(){}
    public void undo(){

    }
}
