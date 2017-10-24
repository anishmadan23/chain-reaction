import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;



public class GUI extends Application 
{

    public int rows=15;
    public int cols=10;
//    public GUI(int rows1, int cols1){
//        this.rows = rows1;
//        this.cols = cols1;
//    }

    public Scene scene1,scene2,scene3,scene4;      //scene3 - settings, scene4 -> Name, ColorPicker
    public Button resumeBtn, playGame,settingsBtn,backToMenuBtn;
    public Stage pstage;
    public Label[] playerSettings;




    public static void main(String[] args) 
    {
        launch(args);
    }

    public GridPane makeGrid()
    {
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        ColumnConstraints[] colCons = new ColumnConstraints[cols];
        RowConstraints[] rowCons = new RowConstraints[rows];

        for(int i = 0;i< cols ; i++)
        {
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

    public Scene makeNameAndColorPickerPage(){
        GridPane nameAndColorPicker = new GridPane();

        Label playerName = new Label("Player Name");
        playerName.setFont(Font.font("Cambria",FontWeight.BOLD,20));
        playerName.setPadding(new Insets(0,0,0,10));
        GridPane.setHalignment(playerName,HPos.CENTER);
        GridPane.setMargin(playerName,new Insets(20,0,0,0));

        final TextField playerNameInput = new TextField();
        playerNameInput.setPromptText("Player 1");
        playerNameInput.getText();
        playerNameInput.setMinHeight(40);
        playerNameInput.setPadding(new Insets(0,20,0,0));
        GridPane.setHalignment(playerNameInput,HPos.LEFT);
        GridPane.setMargin(playerNameInput,new Insets(20,20,0,0));

        Button saveNameBtn = new Button("Save");
        saveNameBtn.setPrefSize(80,20);
        saveNameBtn.setFont(new Font("Arial",12));
        GridPane.setHalignment(saveNameBtn, HPos.LEFT);


        Label orbColor = new Label("Colour of Orb");
        orbColor.setFont(Font.font("Cambria",FontWeight.BOLD,20));
        orbColor.setPadding(new Insets(0,0,0,20));
        GridPane.setHalignment(orbColor,HPos.CENTER);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);

        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(5);

        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        nameAndColorPicker.getRowConstraints().addAll(row1,row2,row3);
        nameAndColorPicker.getColumnConstraints().addAll(col1,col2);

        nameAndColorPicker.add(playerName,0,0);
        nameAndColorPicker.add(playerNameInput,1,0);
        nameAndColorPicker.add(saveNameBtn,1,1);
        nameAndColorPicker.add(orbColor,0,2);

//        GridPane.setColumnSpan(playerName,2);
//        GridPane.setColumnSpan(playerNameInput,2);


        ColorPicker colorPicker1 = new ColorPicker(Color.BLUE);
//        ColorPicker colorPicker2 = new ColorPicker(Color.BLACK);
        nameAndColorPicker.add(colorPicker1,1,2);

        scene4 = new Scene(nameAndColorPicker,720,720);
        scene4.setFill(Color.BLACK);
        return scene4;

    }

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
        row2.setPercentHeight(70.0/3);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(70.0/3);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(70.0/3);
        RowConstraints row5 = new RowConstraints();
        row5.setPercentHeight(20.0);
        pageContents.getRowConstraints().addAll(row1,row2,row3,row4,row5);

        Label gameName = new Label("Chain Reaction for PC ");
        gameName.setFont(Font.font("Cambria",FontWeight.BOLD,30));

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

        resumeBtn = new Button("Resume Game");
        resumeBtn.setPrefSize(200,50);
        resumeBtn.setFont(new Font("Cambria",20));
        GridPane.setHalignment(resumeBtn, HPos.CENTER);
        pageContents.add(resumeBtn,0,1);

        playGame =  new Button("New Game");
        playGame.setPrefSize(200,50);
        playGame.setFont(new Font("Cambria",20));
        GridPane.setHalignment(playGame, HPos.CENTER);
        pageContents.add(playGame,1,1);


        Label GridSizeLabel = new Label("Grid Size");
        GridSizeLabel.setFont(Font.font("Arial",FontWeight.BOLD,25));
        GridPane.setHalignment(GridSizeLabel, HPos.CENTER);
        pageContents.add(GridSizeLabel,0,2);


        final ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll("Small","Big");
        comboBox.getSelectionModel().selectFirst();
        comboBox.setPrefSize(200,40);
        comboBox.setStyle("-fx-font: 20px \"Serif\";");
        GridPane.setHalignment(comboBox, HPos.CENTER);
        pageContents.add(comboBox,1,2);


        Label playersLabel = new Label("Players");
        playersLabel.setFont(new Font("Arial",25));
        playersLabel.setFont(Font.font("Arial",FontWeight.BOLD,25));
        GridPane.setHalignment(playersLabel, HPos.CENTER);
        pageContents.add(playersLabel,0,3);

        ObservableList<Integer> players = FXCollections.observableArrayList(2,3,4,5,6,7,8);
        final Spinner<Integer> spinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(players);
        valueFactory.setValue(2);                               // Default value
        spinner.setValueFactory(valueFactory);
        spinner.setPrefSize(200,40);
        spinner.setStyle("-fx-font: 20px \"Serif\";");
        GridPane.setHalignment(spinner, HPos.CENTER);
        pageContents.add(spinner,1,3);


        settingsBtn = new Button("Settings");
        settingsBtn.setPrefSize(200,50);
        settingsBtn.setFont(new Font("Cambria",20));
        GridPane.setColumnSpan(settingsBtn,3);
        GridPane.setHalignment(settingsBtn, HPos.CENTER);
        pageContents.add(settingsBtn,0,4);


//        pageContents.gridLinesVisibleProperty().set(true);
        rootpane.getChildren().addAll(pageContents);
//        rootpane.setStyle("-fx-background-color: #000;");
        scene1 = new Scene(rootpane,720,720);       //scene1 -> initial page
        return scene1;
    }

    public Scene Grid_GUI(){
        Grid g = new Grid();
        g.createGrid();
        scene2 = new Scene(g.root, 720, 600);
        scene2.setFill(Color.BLACK);


//        Duration DURATION = Duration.seconds(4);
//        Animation animation;
        scene2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                double x=event.getSceneX();
                double y=event.getSceneY();
                //System.out.println(x);
                //System.out.println(y);
                x=x-20;
                y=y-50;
                x=x/50;
                y=y/50;
                x=Math.ceil(x);
                y=Math.ceil(y);
                //System.out.println((int)x+" "+(int)y);
                if(x<7 && y<10)
                g.createSphere(x,y);
            }
        });


        return scene2;
    }

    public Scene makeSettingsPage(){
        GridPane settingsView = new GridPane();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100);
        settingsView.getColumnConstraints().add(col1);

        RowConstraints[] playerRows = new RowConstraints[9];
        for(int i = 0; i<9;i++){
            playerRows[i] = new RowConstraints();
            playerRows[i].setPercentHeight(100.0/9);
            settingsView.getRowConstraints().add(playerRows[i]);
        }

        backToMenuBtn = new Button("Back To Menu");
        backToMenuBtn.setPrefSize(150,30);
        backToMenuBtn.setFont(new Font("Cambria",13));
//        GridPane.setHalignment(resumeBtn, HPos.CENTER);
        settingsView.add(backToMenuBtn,0,0);
        GridPane.setMargin(backToMenuBtn,new Insets(0,0,0,20));



        playerSettings = new Label[8];
        for( int i = 0; i<8 ; i++) {
            playerSettings[i] = new Label("Player " + (i+1) + " Settings"+"\n");
            settingsView.add(playerSettings[i],0,i+1);
            playerSettings[i].setFont(Font.font("Cambria", FontWeight.SEMI_BOLD, 20));
            playerSettings[i].setPadding(new Insets(0,0,0,20));
            GridPane.setFillHeight(playerSettings[i],true);
            GridPane.setFillWidth(playerSettings[i],true);
//            playerSettings[i].setStyle("-fx-border-color: black");
        }
        settingsView.setGridLinesVisible(true);
        scene3 = new Scene(settingsView,720,720);
        scene3.setFill(Color.BLACK);

        return scene3;
    }

    public void ButtonClick(ActionEvent event){
        if(event.getSource()==resumeBtn) {
            scene2 = Grid_GUI();
            pstage.setScene(scene2);
        }
        else if(event.getSource()==playGame){
            scene2 = Grid_GUI();
            pstage.setScene(scene2);

        }
        else if(event.getSource()==settingsBtn){
            scene3 = makeSettingsPage();
            pstage.setScene(scene3);
            playerSettings[0].setOnMouseClicked(e -> LabelClick(e));
            playerSettings[1].setOnMouseClicked(e -> LabelClick(e));
            playerSettings[2].setOnMouseClicked(e -> LabelClick(e));
            playerSettings[3].setOnMouseClicked(e -> LabelClick(e));
            playerSettings[4].setOnMouseClicked(e -> LabelClick(e));
            playerSettings[5].setOnMouseClicked(e -> LabelClick(e));
            playerSettings[6].setOnMouseClicked(e -> LabelClick(e));
            playerSettings[7].setOnMouseClicked(e -> LabelClick(e));
            backToMenuBtn.setOnAction(event1 -> BackToMenu(event1));


        }
    }

    public void BackToMenu(ActionEvent event){
        if(event.getSource()==backToMenuBtn){
            scene1 = makeInitialPage();
            pstage.setScene(scene1);
        }
    }
    public void LabelClick(MouseEvent event){
        scene4 = makeNameAndColorPickerPage();
        pstage.setScene(scene4);
    }

    @Override
    public void start(Stage primaryStage) {
        pstage = primaryStage;
        primaryStage.setTitle("Chain Reaction");
        scene1 = makeInitialPage();

        primaryStage.setScene(scene1);
        settingsBtn.setOnAction(event -> ButtonClick(event));
        playGame.setOnAction(event -> ButtonClick(event));
        resumeBtn.setOnAction(event -> ButtonClick(event));



    primaryStage.show();
    }
}
