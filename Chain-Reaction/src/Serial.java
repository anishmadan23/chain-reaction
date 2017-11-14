import java.io.*;
import java.util.ArrayList;

public class Serial implements Serializable
{
    int row;
    int column;
    int array[][];
    int next_player_index;
    String player_color_grid[][];
    int mouse_clicks;
    int dummy = 1;

    public Serial(int row, int column, String color[][], int a[][] )
    {
        this.row=row;
        this.column=column;
        array= new int[column][row];
        player_color_grid= new String[row][column];
        initialize_color(color);
        initialize(a);
    }

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

    public  void display()
    {
        for(int i=0; i<row; i++)
        {
            for (int j=0; j<column; j++)
            {
                System.out.print(player_color_grid[i][j]+"  ");
            }
            System.out.println();
        }

        for(int i=0; i<row; i++)
        {
            for (int j=0; j<column; j++)
            {
                System.out.print(array[j][i]+"  ");
            }
            System.out.println();
        }
    }
}
