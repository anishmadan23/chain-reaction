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

    public int rows=9;
    public int cols=6;
    public int numberOfPlayers  = 2;

    public GUI() {
        this.rows = 9;
        this.cols = 6;
    }

    ComboBox<String> comboBox;
    public Scene scene1 =null,scene2=null,scene3=null,scene4;      //scene3 - settings, scene4 -> Name, ColorPicker
    Scene[] nameAndColorPickerScenes = new Scene[8];
    public Button resumeBtn, playGame,settingsBtn,backToMenuBtn;
    public Stage pstage;
    public Label[] playerSettings;
    public Players[] playersForSettings = new Players[8];
    public Color[] defaultColorList = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.PINK,Color.LIGHTCYAN,
            Color.ORANGE, Color.WHITE};
//    public Label[] playerName = new Label[8];
    public TextField[] playerNameInputs = new TextField[8];
    GridPane settingsView;

    Button backToSettings, saveSettings;
    Label headingSettings;






    public static void main(String[] args)
    {
        launch(args);
    }

//    public GridPane makeGrid()
//    {
//        GridPane gridpane = new GridPane();
//        gridpane.setAlignment(Pos.CENTER);
//        ColumnConstraints[] colCons = new ColumnConstraints[cols];
//        RowConstraints[] rowCons = new RowConstraints[rows];
//
//        for(int i = 0;i< cols ; i++)
//        {
//            colCons[i] = new ColumnConstraints();
//            colCons[i].setPercentWidth(100.0/cols);
//            gridpane.getColumnConstraints().add(colCons[i]) ;
//        }
//
//
//        for( int i =0 ; i<rows;i++){
//            rowCons[i] = new RowConstraints();
//            rowCons[i].setPercentHeight(100.0/rows);
//            gridpane.getRowConstraints().add(rowCons[i]);
//        }
//
//        Sphere[][] spheres = new Sphere[rows][cols];
//        for(int i = 0; i< rows;i++){
//            for(int j = 0 ;j<cols;j++){
//                spheres[i][j] = new Sphere(15);
//                spheres[i][j].setMaterial(new PhongMaterial(Color.BLUE));
//                gridpane.add(spheres[i][j],j,i);
//                GridPane.setHalignment(spheres[i][j], HPos.CENTER);
//                GridPane.setValignment(spheres[i][j], VPos.CENTER);
//            }
//        }
//
//        gridpane.setAlignment(Pos.CENTER);
//        gridpane.setPrefSize(720,720);
//        gridpane.gridLinesVisibleProperty().set(true);
//        return gridpane;
//    }



    public Scene[] makeNameAndColorPickerPage(){


        for(int i = 0;i<8;i++){


            GridPane nameAndColorPicker = new GridPane();

            Label playerName = new Label("Player Name");
            playerName.setFont(Font.font("Cambria",FontWeight.BOLD,20));
            playerName.setPadding(new Insets(0,0,0,10));
            GridPane.setHalignment(playerName,HPos.CENTER);
            GridPane.setMargin(playerName,new Insets(20,20,0,0));
            GridPane.setColumnSpan(playerName,2);

            playerNameInputs[i] = new TextField();
            playerNameInputs[i].setPromptText("Player 1");
//            playerNameInputs[i].getText();
            playerNameInputs[i].setMinHeight(40);
            playerNameInputs[i].setPadding(new Insets(0,20,0,10));
            GridPane.setHalignment(playerNameInputs[i],HPos.LEFT);
            GridPane.setMargin(playerNameInputs[i],new Insets(20,20,0,20));
            GridPane.setColumnSpan(playerNameInputs[i],2);


            ColorPicker colorPicker1 = new ColorPicker(defaultColorList[i]);
//        ColorPicker colorPicker2 = new ColorPicker(Color.BLACK);
            GridPane.setColumnSpan(colorPicker1,2);


            Button saveNameBtn = new Button("Save");
            saveNameBtn.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
            saveNameBtn.setFont(new Font("Arial",20));
            GridPane.setMargin(saveNameBtn, new Insets(10,10,10,10));
            GridPane.setHalignment(saveNameBtn, HPos.CENTER);
//            Players p = playersForSettings[i];
            int index =  i;
            saveNameBtn.setOnMouseClicked(e->
                                {System.out.println(index);
                                playersForSettings[index].setName(playerNameInputs[index].getText());//fix null pointer issue here
                                playersForSettings[index].setColor(colorPicker1.getValue());
                                });
            //define onclick


            Label orbColor = new Label("Colour of Orb");
            orbColor.setFont(Font.font("Cambria",FontWeight.BOLD,20));
            orbColor.setPadding(new Insets(0,0,0,20));
            GridPane.setColumnSpan(orbColor,2);
            GridPane.setHalignment(orbColor,HPos.CENTER);

            backToSettings = new Button("Back");
            GridPane.setMargin(backToSettings, new Insets(10,10,10,10));
            backToSettings.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
            backToSettings.setFont(new Font("Arial",20));
            GridPane.setHalignment(backToMenuBtn, HPos.CENTER);
            backToSettings.setOnMouseClicked(e->{
                pstage.setScene(scene3);
            });

            headingSettings = new Label("Chain Reaction Settings");
            headingSettings.setFont(Font.font("Cambria",FontWeight.BOLD,20));
            GridPane.setHalignment(headingSettings, HPos.CENTER);
            GridPane.setColumnSpan(headingSettings,2);


            RowConstraints row1 = new RowConstraints();
            row1.setPercentHeight(10);

            RowConstraints row2 = new RowConstraints();
            row2.setPercentHeight(10);

            RowConstraints row3 = new RowConstraints();
            row3.setPercentHeight(10);

            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(15);

            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(35);

            ColumnConstraints col3 = new ColumnConstraints();
            col3.setPercentWidth(35);

            ColumnConstraints col4 = new ColumnConstraints();
            col4.setPercentWidth(15);

            nameAndColorPicker.getRowConstraints().addAll(row1,row2,row3);
            nameAndColorPicker.getColumnConstraints().addAll(col1,col2,col3,col4);


            nameAndColorPicker.add(backToSettings,0,0);
            nameAndColorPicker.add(headingSettings,1,0);
            nameAndColorPicker.add(saveNameBtn,3,0);
            nameAndColorPicker.add(playerName,0,1);

            nameAndColorPicker.add(playerNameInputs[i],2,1);

            nameAndColorPicker.add(orbColor,0,2);
            nameAndColorPicker.add(colorPicker1,2,2);

//        GridPane.setColumnSpan(playerName,2);
//        GridPane.setColumnSpan(playerNameInput,2);





        nameAndColorPickerScenes[i] = new Scene(nameAndColorPicker,720,720);
        nameAndColorPickerScenes[i].setFill(Color.BLACK);
        }

        return nameAndColorPickerScenes;

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


        comboBox = new ComboBox<String>();
        comboBox.getItems().addAll("Small","Big");
        comboBox.getSelectionModel().selectFirst();
        comboBox.setPrefSize(200,40);
        comboBox.setStyle("-fx-font: 20px \"Serif\";");
        GridPane.setHalignment(comboBox, HPos.CENTER);
        pageContents.add(comboBox,1,2);
        comboBox.setOnAction(e -> {
                    if (comboBox.getValue().equals("Big")) {
                        rows = 15;
                        cols = 10;
//                        if(Grid.list!=null){
//                        Grid.list.remove(0,Grid.list.size());}
                    }
                    else{
                        rows = 9;
                        cols = 6;
                    }
                });





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


        settingsBtn.setOnAction(event -> ButtonClick(event));

        playGame.setOnAction(event -> ButtonClick(event));
        resumeBtn.setOnAction(event -> ButtonClick(event));


//        pageContents.gridLinesVisibleProperty().set(true);
        rootpane.getChildren().addAll(pageContents);
//        rootpane.setStyle("-fx-background-color: #000;");
        scene1 = new Scene(rootpane,720,720);       //scene1 -> initial page
        return scene1;
    }





    public Scene Grid_GUI(){
        Grid g = new Grid(rows,cols);
        g.createGrid(rows,cols);
        Cell[][] cellsArray = new Cell[rows][cols];
        System.out.println(rows+" "+cols);
        Cell c = new Cell(cellsArray);
        for(int i =0 ;i<rows ; i++){
            for(int j =0 ;j<cols;j++){
                cellsArray[i][j] = new Cell(0);
            }
        }

        g.comboBox.setOnAction(e -> {
            if(g.comboBox.getValue().equals("New Game")){
                scene2 = Grid_GUI();
                pstage.setScene(scene2);
            }
            else if(g.comboBox.getValue().equals("Go to Main Menu")){
//                scene1 = makeInitialPage();
                pstage.setScene(scene1);
//                g.comboBox.setValue("Options");
            }
        });




//        g.root.getChildren().addAll(g.root1);
//        StackPane pane = new StackPane();
        scene2 = new Scene(g.root, 720, 600);
        scene2.setFill(Color.BLACK);


//        Duration DURATION = Duration.seconds(4);
//        Animation animation;
        scene2.setOnMouseClicked(event -> explosionEvent(event,g,c));
        comboBox.setValue("Small");

        return scene2;
    }

    public void explosionEvent(MouseEvent event,Grid g, Cell c) {
        int cellSize, xGridStart, yGridStart;
        if (rows == 9 && cols == 6) {
            cellSize = 50;
            xGridStart = 20;
            yGridStart = 50;
        } else {
            cellSize = 45;
            xGridStart = 20;
            yGridStart = 45;
        }
        double x = event.getSceneX();
        double y = event.getSceneY();
        //System.out.println(x);
        //System.out.println(y);
        x = x - xGridStart;
        y = y - yGridStart;
        x = x / cellSize;
        y = y / cellSize;
        x = Math.floor(x);
        y = Math.floor(y);

        if (x < cols + 1 && y < rows + 1) {
            System.out.println("this" + (int) x + " " + (int) y);

            c.explosion((int) y, (int) x,g);
        }
        for(int i = 0;i<rows;i++){
            for(int j = 0; j<cols ;j++){
                System.out.print(c.grid[i][j].getOrbs()+" ");
            }
            System.out.println();
        }


    }

    public Scene makeSettingsPage(){
        for(int i = 0;i<numberOfPlayers;i++){
            String x = "Player "+String.valueOf(i+1);
            playersForSettings[i] = new Players(x,defaultColorList[i]);
        }


        settingsView = new GridPane();

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
        backToMenuBtn.setOnAction(e -> ButtonClick(e));
        GridPane.setHalignment(backToMenuBtn, HPos.LEFT);
//        GridPane.setHalignment(resumeBtn, HPos.CENTER);
        settingsView.add(backToMenuBtn,0,0);
        GridPane.setMargin(backToMenuBtn,new Insets(0,0,0,20));



        playerSettings = new Label[8];
        for( int i = 0; i<8 ; i++) {
            playerSettings[i] = new Label("Player " + (i+1) + " Settings"+"\n");
            playerSettings[i].setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
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
            settingsView.getChildren().get(1).setOnMouseClicked(e -> LabelClick(e,0));
            settingsView.getChildren().get(2).setOnMouseClicked(e -> LabelClick(e,1));
            settingsView.getChildren().get(3).setOnMouseClicked(e -> LabelClick(e,2));
            settingsView.getChildren().get(4).setOnMouseClicked(e -> LabelClick(e,3));
            settingsView.getChildren().get(5).setOnMouseClicked(e -> LabelClick(e,4));
            settingsView.getChildren().get(6).setOnMouseClicked(e -> LabelClick(e,5));
            settingsView.getChildren().get(7).setOnMouseClicked(e -> LabelClick(e,6));
            settingsView.getChildren().get(8).setOnMouseClicked(e -> LabelClick(e,7));
        }
        else if(event.getSource()==backToMenuBtn){
            System.out.println(event.getSource());
            scene1 = makeInitialPage();
            pstage.setScene(scene1);
            }
        }



    public void LabelClick(MouseEvent event, int i){
        Scene[] scenes = makeNameAndColorPickerPage();
        pstage.setScene(scenes[i]);
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

//        if(primaryStage.getScene()==scene3) {

//        }
//        System.out.println(primaryStage.getScene()==scene3);


        primaryStage.show();
    }
}
