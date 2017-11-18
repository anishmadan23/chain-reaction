import java.io.*;
import java.util.ArrayList;


/**
 * This class is made to serialize the non-Javafx Components of the game.
 */
public class Serial implements Serializable
{
    int row;
    int column;
    /**
     * Backend for grid.
     */
    int array[][];
    /**
     * Mouseclicks
     */
    int mouse;
    /**
     * Current players in game
     */
    int players_in_game;
    /**
     * Color of players in grid.
     */
    String player_color_grid[][];
    int dummy = 1;

    public Serial(int row, int column, String color[][], int a[][] ,  int mouse, int players_in_game)
    {
        this.row=row;
        this.column=column;
        array= new int[column][row];
        player_color_grid= new String[row][column];
        this.mouse=mouse;
        this.players_in_game=players_in_game;
        initialize_color(color);
        initialize(a);
    }

    /**
     *Initialises array[][] according to grid
     */
    public void initialize(int a[][])
    {
        for(int i=0; i<column; i++)
        {
            for (int j=0; j<row; j++)
            {
                array[i][j]=a[i][j];
            }
        }


    }

    /**
     * Initialises grid[][] according to color of player
     */
    public void initialize_color(String a[][])
    {
        for(int i=0; i<row; i++)
        {
            for (int j=0; j<column; j++)
            {
                player_color_grid[i][j]=a[i][j];
            }
        }


    }

    /**
     * Used to display contents of class
     */
    public  void display()
    {
        System.out.println("color grid");
        for(int i=0; i<row; i++)
        {
            for (int j=0; j<column; j++)
            {
                System.out.print(player_color_grid[i][j]+"  ");
//                String s=player_color_grid[i][j];
//                if(s!=null) {
//                    String x[] = s.split(" ");
//                    double b = Double.parseDouble(x[0]);
//                    double g = Double.parseDouble(x[1]);
//                    double r = Double.parseDouble(x[2]);
//                    System.out.println("Tokenized rgb = " + b + " " + g + " " + r);
                //}

            }
            System.out.println();
        }
        System.out.println("arrayyyyyyy");
        for(int i=0; i<row; i++)
        {
            for (int j=0; j<column; j++)
            {
                System.out.print(array[j][i]+"  ");
            }
            System.out.println();
        }
    }

//    public static Double nextDouble(String x) throws IOException{
//        return Double.
//    }
}
