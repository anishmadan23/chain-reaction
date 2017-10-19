import com.sun.rowset.internal.Row;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GUI extends Application {

    public int rows=15;
    public int cols=10;
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

    public Scene scene1,scene2;

    public Scene makeInitialPage(){
        StackPane rootpane = new StackPane();
        GridPane  pageContents = new GridPane();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(46);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(46);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(8);
        pageContents.getColumnConstraints().addAll(col1,col2,col3);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10.0);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25.0);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(25.0);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(25.0);
        RowConstraints row5 = new RowConstraints();
        row5.setPercentHeight(15.0);
        pageContents.getRowConstraints().addAll(row1,row2,row3,row4,row5);

        Label gameName = new Label("Chain Reaction for PC ");
        gameName.setFont(new Font("Cambria",30));

        Label infoLabel = new Label();

        Image image = new Image("info1.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(40);
        img.setFitWidth(40);
        infoLabel.setGraphic(img);

        pageContents.add(gameName,0,0);
        GridPane.setColumnSpan(gameName,2);
        GridPane.setHalignment(gameName, HPos.CENTER);

        pageContents.add(infoLabel,2,0);
        GridPane.setHalignment(infoLabel, HPos.CENTER);

        Button resumeBtn = new Button("Resume Game");
        GridPane.setHalignment(resumeBtn, HPos.CENTER);
        pageContents.add(resumeBtn,0,1);

        Button playGame =  new Button("New Game");
        GridPane.setHalignment(playGame, HPos.CENTER);
        pageContents.add(playGame,1,1);

        Label GridSizeLabel = new Label("Grid Size");
        GridPane.setHalignment(GridSizeLabel, HPos.CENTER);
        pageContents.add(GridSizeLabel,0,2);

        Label playersLabel = new Label("Players");
        GridPane.setHalignment(playersLabel, HPos.CENTER);
        pageContents.add(playersLabel,0,3);

        Button settingsBtn = new Button("Settings");
        GridPane.setColumnSpan(settingsBtn,3);
        GridPane.setHalignment(settingsBtn, HPos.CENTER);
        pageContents.add(settingsBtn,0,4);









//        pageContents.gridLinesVisibleProperty().set(true);
        rootpane.getChildren().addAll(pageContents);
        scene1 = new Scene(rootpane,720,720);
        return scene1;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chain Reaction");


        scene1 = makeInitialPage();
        primaryStage.setScene(scene1);
        GridPane gridpane = makeGrid();
        scene2 = new Scene(gridpane);
//        primaryStage.setScene(scene2);
        primaryStage.show();
    }
}
