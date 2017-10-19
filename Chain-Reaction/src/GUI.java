import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;



public class GUI extends Application {

    public int rows=9;
    public int cols=6;
//    public GUI(int rows1, int cols1){
//        this.rows = rows1;
//        this.cols = cols1;
//    }
    public static void main(String[] args) {
        launch(args);
    }

    public GridPane makeGrid(){
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        ColumnConstraints[] colCons = new ColumnConstraints[cols];
        RowConstraints[] rowCons = new RowConstraints[rows];


        for(int i = 0;i< cols ; i++){
            colCons[i] = new ColumnConstraints();
            colCons[i].setPercentWidth(100.0/cols);
            gridpane.getColumnConstraints().add(colCons[i]) ;
        }


        for( int i =0 ; i<rows;i++){
            rowCons[i] = new RowConstraints();
            rowCons[i].setPercentHeight(100.0/rows);
            gridpane.getRowConstraints().add(rowCons[i]);
        }

        Sphere[][] spheres = new Sphere[rows][cols];
        for(int i = 0; i< rows;i++){
            for(int j = 0 ;j<cols;j++){
                spheres[i][j] = new Sphere(15);
                spheres[i][j].setMaterial(new PhongMaterial(Color.BLUE));
                gridpane.add(spheres[i][j],j,i);
                GridPane.setHalignment(spheres[i][j], HPos.CENTER);
                GridPane.setValignment(spheres[i][j], VPos.CENTER);
            }
        }

        gridpane.setAlignment(Pos.CENTER);
        gridpane.setPrefSize(720,720);
        gridpane.gridLinesVisibleProperty().set(true);
        return gridpane;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chain Reaction");
        GridPane gridpane = makeGrid();
        Scene scene = new Scene(gridpane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
