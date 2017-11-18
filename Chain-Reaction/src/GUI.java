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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;

/**
 This class is responsible for all the components displayed on the screen which include the initial page, the game screen,
 settings page and individual player settings page.

 */
public class GUI extends Application
{
    /**
     * holds the rows for the grid
     */
    public int rows;

    /**
     * holds the column for the grid
     */
    public int cols;

    /**
     * number of players playing the game

     */
    public int playersInGame  = 2;

    /**
     * Warning alert defined to signal if same color chosen by 2 players
     */
    public Alert alert = new Alert(Alert.AlertType.WARNING);

    /**
     * mouseClicks calculate color and turn of player
     */
    public int mouseClicks = 0;

    /**
     * current player in turn
     */
    public int playerIndex1 = 0;

    /**
     * current color in turn
     */
    public int colorIndex1 = 0;

    /**
     * value returned after explosion of orbs
     */
    public int  r  = 1;

    /**
     * checks if textField in Player Settings has been initialised.
     */
    boolean initialisedTextFields = false;

    /**
     * checks if colorPicker in Player Settings has been initialised.
     */
    boolean initalisedColorPicker = false;

    /**
     * checks if Players in Settings have been initialised.
     */
    boolean initialisedPlayers = false;

    /**
     * checks if Current Players in Game have been initialised.
     */
    boolean initialisedInGamePlayers = false;

    /**
     * Serialised Objects to savve state of game
     */
    public Serial s1,s2;

    /**
     * 2d array after each explosion
     */
    int array_after_explosion[][];

    /**
     * checks if undo is possible
     */
    int undo_click=0;
    String serial_color[];


    public GUI() {

        this.rows = 9;
        this.cols = 6;
        array_after_explosion= new int[this.rows][this.cols];
    }

    /**
     * List for number of players to choose.
     */
    ObservableList<Integer> players = FXCollections.observableArrayList(2,3,4,5,6,7,8);

    /**
     * Used to select players
     */
    Spinner<Integer> spinner ;

    /**
     * used to check grid size
     */
    ComboBox<String> comboBox;

    /**
     * different scenes for multiple screens.
     */
    public Scene scene1 =null,scene2=null,scene3=null,scene4;
    private Scene[] nameAndColorPickerScenes = new Scene[8];
    private Button resumeBtn, playGame,settingsBtn,backToMenuBtn;
    /**
     * Stage similar to primaryStage
     */
    public Stage pstage;
    private Label[] playerSettings;
    private ArrayList<Players> playersForSettings = new ArrayList<>(8);
    private ArrayList<Players> playersInGameArray = new ArrayList<>();

    /**
     * Initial List for colour of players' orbs
     */
    private Color[] defaultColorList = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.PINK,Color.LIGHTCYAN,
            Color.ORANGE, Color.WHITE};

    private TextField[] playerNameInputs = new TextField[8];
    private ColorPicker[] colorPickers = new ColorPicker[8];

    /**
     * Grid for settings page.
     */
    private GridPane settingsView;
    private Button backToSettings;
    private Label headingSettings;


    /**
     * Initialises color picker with values from defaultColorList[]
     */
    public void initialiseColorPicker(){
        if(!initalisedColorPicker){
            for(int i = 0;i<8;i++){
                colorPickers[i] = new ColorPicker(defaultColorList[i]);
            }
            initalisedColorPicker = true;
        }

    }

    /**
     * Sets up warning alert if same color is chosen by two different players.
     */
    public void setupAlert(){
        alert.setTitle("Same Color Warning!");
        alert.setHeaderText(null);
        alert.setContentText("Another player has chosen the same color! Choose a different one or default will be set.");
        alert.showAndWait();}

    /**
     * Initialises TextFields For player Names
     */
    public  void initialiseTextFields(){
        if(!initialisedTextFields) {
            for (int i = 0; i < 8; i++) {
                playerNameInputs[i] = new TextField();
                playerNameInputs[i].setText("Player " + String.valueOf(i + 1));
            }
            initialisedTextFields = true;
        }
    }

    /**
     * Detects same color of orbs for two players in settings.
     */
    public boolean colorException(Color c , int index){
        for(int i = 0;i<8;i++){
            if(i!=index){
                Color playerColor = playersForSettings.get(i).getColor();
                if(c.getRed()==playerColor.getRed() && c.getGreen()==playerColor.getGreen() && c.getBlue()==playerColor.getBlue()){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Launches the GUI
     */
    public static void main(String[] args)

    {
        launch(args);
    }


    /**
     * Returns scene[] with layouts of player's individual settings.
     */
    public final Scene[] makeNameAndColorPickerPage(){


        for(int i = 0;i<8;i++){


            GridPane nameAndColorPicker = new GridPane();

            Label playerName = new Label("Player Name");
            playerName.setFont(Font.font("Cambria",FontWeight.BOLD,20));
            playerName.setPadding(new Insets(0,0,0,10));
            playerName.setStyle(
                    "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                    "-fx-padding: 5px;"+
                    "-fx-background-radius: 5px;"+
                    "-fx-font: 20px \"Cambria\";" +
                    "-fx-border-radius: 5px;"+
                    "-fx-text-fill: white");
            GridPane.setHalignment(playerName,HPos.CENTER);
            GridPane.setMargin(playerName,new Insets(20,20,0,0));
            GridPane.setColumnSpan(playerName,2);


            System.out.println(playerNameInputs[i].getText());
            playerNameInputs[i].setMinHeight(50);
            playerNameInputs[i].setPadding(new Insets(0,20,0,10));
            playerNameInputs[i].setStyle(
                    "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                    "-fx-background-radius: 5px;"+
                    "-fx-font: 15px \"Cambria\";" +
                    "-fx-border-radius: 5px;"+
                    "-fx-text-fill: black;"+
                    "-fx-border-color: #181818");
            GridPane.setHalignment(playerNameInputs[i],HPos.LEFT);
            GridPane.setMargin(playerNameInputs[i],new Insets(20,20,0,20));
            GridPane.setColumnSpan(playerNameInputs[i],2);



//        ColorPicker colorPicker2 = new ColorPicker(Color.BLACK);
            GridPane.setMargin(colorPickers[i],new Insets(20,20,0,20));

            GridPane.setColumnSpan(colorPickers[i],2);


            Button saveNameBtn = new Button("Save");
            saveNameBtn.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
            saveNameBtn.setFont(new Font("Arial",20));
            saveNameBtn.setStyle("-fx-background-color: rgba(0,0,0,0.75),\n" +
                    "                           rgba(255,255,255,0.75),\n" +
                    "                           linear-gradient(to bottom,#383838 0%,#181818 100%);"+
                    "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                    "-fx-padding: 5px;"+
                    "-fx-background-radius: 5px;"+
                    "-fx-font: 20px \"Cambria\";" +
                    "-fx-border-radius: 5px;"+
                    "-fx-text-fill: white");
            GridPane.setMargin(saveNameBtn, new Insets(10,10,10,10));
            GridPane.setHalignment(saveNameBtn, HPos.CENTER);
//            Players p = playersForSettings[i];
            int index =  i;
            saveNameBtn.setOnMouseClicked(e->
                                {
                                    System.out.println(index);
                                    playersForSettings.get(index).setName(playerNameInputs[index].getText());//fix null pointer issue here
                                    playerNameInputs[index].setText(playersForSettings.get(index).getName());
                                    System.out.println(playerNameInputs[index].getText());


                                    if(colorException(colorPickers[index].getValue(),index)){
                                        setupAlert();
                                        colorPickers[index] = new ColorPicker(defaultColorList[index]);

                                    }
                                    else{
                                        playersForSettings.get(index).setColor(colorPickers[index].getValue());

                                    }


                                    pstage.setScene(scene3);

                                });
            //define onclick

            Label orbColor = new Label("Colour of Orb");
            orbColor.setFont(Font.font("Cambria",FontWeight.BOLD,20));
            orbColor.setPadding(new Insets(0,0,0,20));
            orbColor.setStyle(
                    "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                    "-fx-padding: 5px;"+
                    "-fx-background-radius: 5px;"+
                    "-fx-font: 20px \"Cambria\";" +
                    "-fx-border-radius: 5px;"+
                    "-fx-text-fill: white");
            GridPane.setColumnSpan(orbColor,2);
            GridPane.setHalignment(orbColor,HPos.CENTER);

            backToSettings = new Button("Back");
            GridPane.setMargin(backToSettings, new Insets(10,10,10,10));
            backToSettings.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
            backToSettings.setFont(new Font("Arial",20));
            backToSettings.setStyle("-fx-background-color: rgba(0,0,0,0.75),\n" +
                    "                           rgba(255,255,255,0.75),\n" +
                    "                           linear-gradient(to bottom,#383838 0%,#181818 100%);"+
                    "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                    "-fx-padding: 5px;"+
                    "-fx-background-radius: 5px;"+
                    "-fx-font: 20px \"Cambria\";" +
                    "-fx-border-radius: 5px;"+
                    "-fx-text-fill: white");
            GridPane.setHalignment(backToMenuBtn, HPos.CENTER);
            backToSettings.setOnMouseClicked(e->{
                pstage.setScene(scene3);
            });

            headingSettings = new Label("Player "+(i+1)+" Settings");
            headingSettings.setFont(Font.font("Cambria",FontWeight.BOLD,20));
            headingSettings.setStyle(
                    "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                    "-fx-padding: 5px;"+
                    "-fx-background-radius: 5px;"+
                    "-fx-font: 20px \"Cambria\";" +
                            "-fx-font-weight: bold;"+
                    "-fx-border-radius: 5px;"+
                    "-fx-text-fill: white");
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
            nameAndColorPicker.add(colorPickers[i],2,2);

//        GridPane.setColumnSpan(playerName,2);
//        GridPane.setColumnSpan(playerNameInput,2);




            StackPane root = new StackPane();
            root.setStyle("-fx-background-color: black");
            root.getChildren().add(nameAndColorPicker);
        nameAndColorPickerScenes[i] = new Scene(root,720,720);
//        nameAndColorPickerScenes[i].setFill(Color.BLACK);
        }

        return nameAndColorPickerScenes;

    }

    /**
     * Returns scene with layout of the home screen of the game.
     */
    public Scene makeInitialPage()
    {
        initialiseSettingsPlayers();
        initialiseTextFields();
        initialiseColorPicker();


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
        gameName.setTextFill(Color.WHITE);
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
        resumeBtn.setPrefSize(200,60);
//        resumeBtn.setFont(new Font("Cambria",20));
//        resumeBtn.setTextFill(Color.WHITE);
        GridPane.setHalignment(resumeBtn, HPos.CENTER);
        resumeBtn.setStyle("-fx-background-color: rgba(0,0,0,0.75),\n" +
                "                           rgba(255,255,255,0.75),\n" +
                "                           linear-gradient(to bottom,#484848 0%,#181818 100%);"+
                "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                "-fx-padding: 5px;"+
                "-fx-background-radius: 5px;"+
                "-fx-font: 20px \"Cambria\";" +
                "-fx-border-radius: 5px;"+
                "-fx-text-fill: white");
//        resumeBtn.setStyle("-fx-background-color: #383838");
        pageContents.add(resumeBtn,0,1);

        playGame =  new Button("New Game");
        playGame.setPrefSize(200,60);
        playGame.setFont(new Font("Cambria",20));
        playGame.setTextFill(Color.WHITE);
        playGame.setStyle("-fx-background-color: rgba(0,0,0,0.75),\n" +
                "                           rgba(255,255,255,0.75),\n" +
                "                           linear-gradient(to bottom,#484848 0%,#181818 100%);"+
                        "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                        "-fx-padding: 5px;"+
                        "-fx-background-radius: 5px;"+
                        "-fx-font: 20px \"Cambria\";" +
                        "-fx-border-radius: 5px;"+
                        "-fx-text-fill: white");
        GridPane.setHalignment(playGame, HPos.CENTER);
        pageContents.add(playGame,1,1);


        Label GridSizeLabel = new Label("Grid Size");
        GridSizeLabel.setFont(Font.font("Arial",FontWeight.BOLD,25));
        GridSizeLabel.setTextFill(Color.WHITE);
        GridPane.setHalignment(GridSizeLabel, HPos.CENTER);
        pageContents.add(GridSizeLabel,0,2);


        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Small","Big");
        comboBox.getSelectionModel().selectFirst();
        comboBox.setPrefSize(200,40);

        comboBox.setStyle("-fx-background-color:  #ffffff;"+
                "-fx-border-color: #383838;"+
                "-fx-border-width: 4px;"+
                "-fx-text-fill: white;"+
                "-fx-background-radius: 3px, 3px, 2px, 1px;"+
                "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
            "-fx-padding: 5px;"+

            "-fx-font: 20px \"Cambria\";" +
            "-fx-border-radius: 5px");
        GridPane.setHalignment(comboBox, HPos.CENTER);
        pageContents.add(comboBox,1,2);
        comboBox.setOnAction(e -> {
                    if (comboBox.getValue().equals("Big")) {
                        rows = 15;
                        cols = 10;
                        array_after_explosion=new int[15][10];
//                        if(Grid.list!=null){
//                        Grid.list.remove(0,Grid.list.size());}
                    }
                    else{
                        rows = 9;
                        cols = 6;
                    }
                });

        spinner = new Spinner<>();
        Label playersLabel = new Label("Players");
        playersLabel.setFont(new Font("Arial",25));
        playersLabel.setTextFill(Color.WHITE);
        playersLabel.setFont(Font.font("Arial",FontWeight.BOLD,25));
        GridPane.setHalignment(playersLabel, HPos.CENTER);
        pageContents.add(playersLabel,0,3);


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(players);
        valueFactory.setValue(2);                               // Default value
        spinner.setValueFactory(valueFactory);
        spinner.setPrefSize(200,50);
//        spinner.setStyle("-fx-font: 20px \"Serif\";");
        spinner.setStyle("-fx-background-color: rgba(0,0,0,0.75),\n" +
            "                           rgba(255,255,255,0.75),\n" +
            "                           linear-gradient(to bottom,#484848 0%,#181818 100%);"+
            "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
            "-fx-padding: 5px;"+
            "-fx-background-radius: 5px;"+
            "-fx-font: 20px \"Cambria\";" +
            "-fx-border-radius: 5px;"+
            "-fx-text-fill: white");
        GridPane.setHalignment(spinner, HPos.CENTER);
        pageContents.add(spinner,1,3);


        settingsBtn = new Button("Player Settings");
        settingsBtn.setPrefSize(200,60);
//        settingsBtn.setFont(new Font("Cambria",20));
//        settingsBtn.setTextFill(Color.WHITE);
//        settingsBtn.setStyle("-fx-background-color: #383838");
        settingsBtn.setStyle("-fx-background-color: rgba(0,0,0,0.75),\n" +
            "                           rgba(255,255,255,0.75),\n" +
            "                           linear-gradient(to bottom,#484848 0%,#181818 100%);"+
            "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
            "-fx-padding: 5px;"+
            "-fx-background-radius: 5px;"+
            "-fx-font: 20px \"Cambria\";" +
            "-fx-border-radius: 5px;"+
            "-fx-text-fill: white");
        GridPane.setColumnSpan(settingsBtn,3);
        GridPane.setHalignment(settingsBtn, HPos.CENTER);
        pageContents.add(settingsBtn,0,4);


        settingsBtn.setOnAction(event -> {
            try {
                ButtonClick(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        playGame.setOnAction(event -> {
            try {
                ButtonClick(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resumeBtn.setOnAction(event -> {
            try {
                ButtonClick(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
            undo_click=1;
        });


//        pageContents.gridLinesVisibleProperty().set(true);
        rootpane.getChildren().addAll(pageContents);
//        rootpane.setStyle("-fx-background-color: #000;");
        scene1 = new Scene(rootpane,720,720);       //scene1 -> initial page
        rootpane.setStyle("-fx-background-color: #000000");

        return scene1;
    }

    /**
     * Defines the functionality of each button which takes place on button click.
     */
    public void ButtonClick(ActionEvent event) throws IOException{
        //Grid g=new Grid(rows,cols);
        if(event.getSource()==resumeBtn)
        {

            try {initialisedInGamePlayers = false;
                initialiseInGamePlayers(playersInGame);
                System.out.println("playersInGame "+playersInGame);
                s1 = deserialize();
//                if(s1.mouse==0){
//                    resumeBtn.setDisable(true);}
                scene2 = Grid_resume(rows, cols, s1);
//                   initialiseInGamePlayers(s1.players_in_game);
            } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            pstage.setScene(scene2);}


        else if(event.getSource()==playGame){
            mouseClicks = 0;
            playerIndex1 = 0;
            colorIndex1 = 0;
            r  = 1;
            try {
                playersInGame = spinner.getValue(); //playersInGame receiving value correctly
                initialisedInGamePlayers = false;
                initialiseInGamePlayers(playersInGame);
                scene2 = Grid_GUI();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            pstage.setScene(scene2);

        }
        else if(event.getSource()==settingsBtn){
            scene3 = makeSettingsPage();
            pstage.setScene(scene3);
            settingsView.getChildren().get(2).setOnMouseClicked(e -> LabelClick(e,0));
            settingsView.getChildren().get(3).setOnMouseClicked(e -> LabelClick(e,1));
            settingsView.getChildren().get(4).setOnMouseClicked(e -> LabelClick(e,2));
            settingsView.getChildren().get(5).setOnMouseClicked(e -> LabelClick(e,3));
            settingsView.getChildren().get(6).setOnMouseClicked(e -> LabelClick(e,4));
            settingsView.getChildren().get(7).setOnMouseClicked(e -> LabelClick(e,5));
            settingsView.getChildren().get(8).setOnMouseClicked(e -> LabelClick(e,6));
            settingsView.getChildren().get(9).setOnMouseClicked(e -> LabelClick(e,7));
        }
        else if(event.getSource()==backToMenuBtn){
            System.out.println(event.getSource());
            scene1 = makeInitialPage();
            pstage.setScene(scene1);
        }
    }

    /**
     * Returns scene with the previous saved state of the game so that players can resume play.
     */
    public Scene Grid_resume(int rows, int cols, Serial obj)
    {
        rows=obj.row;
        cols=obj.column;

        Cell[][] cellsArray = new Cell[rows][cols];
        System.out.println(rows+" "+cols);
        Cell c = new Cell(cellsArray);
        for(int i =0 ;i<rows ; i++){
            for(int j =0 ;j<cols;j++){
                cellsArray[i][j] = new Cell(0);
            }
        }
        String change_color[]=obj.player_color;
        for(int i=0; i<playersInGame; i++)
        {
            String s=change_color[i];

            String bgr[]=s.split(" ");
            double b1 = Double.parseDouble(bgr[0]);
            double g1 = Double.parseDouble(bgr[1]);
            double r1 = Double.parseDouble(bgr[2]);
            Color c1= new Color(r1,g1,b1,1);
            playersInGameArray.get(i).setColor(c1);
        }
        Grid g = new Grid(rows,cols);
        System.out.println(playersInGameArray.get(0).getColor());
        g.createGrid(rows,cols,playersInGameArray.get(0).getColor());
        int dummy_array[][]= new int[cols][rows];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                if( obj!=null && obj.array[j][i]>0)
                {
                    for(int k=0; k<obj.array[j][i]; k++)
                    {
                        g.createSphere_undo(j,i,c,dummy_array,obj.player_color_grid);
                    }
                }
            }
        }
        serial_color=obj.player_color;
        String[][] colorsOfPlayers = g.color(rows,cols);
        try {
//            for(int i=0; i<rows; i++) {
//                for (int j = 0; j < cols; j++) {
//                    System.out.print(g.array[j][i]);
//                }
//                System.out.println();
//            }
            serialize(rows,cols,g.array,colorsOfPlayers, mouseClicks, playersInGame, serial_color);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Serialized1");

        mouseClicks=obj.mouse;
        playersInGame=obj.players_in_game;
        playerIndex1=  (mouseClicks)%(playersInGame);
        colorIndex1 = (mouseClicks)%playersInGame;
        g.changeGridColor(playersInGameArray.get(colorIndex1).getColor());

        scene2 = new Scene(g.root, 720, 720);
        scene2.setFill(Color.BLACK);
        after(g,c);
        return scene2;

    }

    /**
     * Defines the functionality of drop down menu inside the game and the undo button
     */
    public void after(Grid g, Cell c)
    {
        g.comboBox.setOnAction(e -> {
            if(g.comboBox.getValue().equals("New Game")){
                try {
                    playersInGame= spinner.getValue();
                    initialisedInGamePlayers = false;
                    initialiseInGamePlayers(playersInGame);

                    scene2 = Grid_GUI();
                    pstage.setScene(scene2);
                    mouseClicks = 0;
                    playerIndex1 = 0;
                    colorIndex1 = 0;
                    r  = 1;
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
            else if(g.comboBox.getValue().equals("Go to Main Menu")){
//                scene1 = makeInitialPage();
                pstage.setScene(scene1);
//                g.comboBox.setValue("Options");
            }
        });

        g.undoBtn.setOnAction(event -> {
            if(undo_click==0)
            {
                if(mouseClicks>1) {
                    //System.out.println("mouseClicks1 "+mouseClicks);
                    mouseClicks-=1;
                    scene2 = Grid_resume(rows, cols, s2);
                    pstage.setScene(scene2);

                }
                else {
                    Scene scene_start = null;
                    try {
                        scene_start = Grid_GUI();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    pstage.setScene(scene_start);
                    mouseClicks = 0;
                    playerIndex1 = 0;
                    colorIndex1 = 0;
                    r  = 1;
                }
                undo_click=1;
            }

        });


        scene2.setOnMouseClicked(event -> {
            try {
                explosionEvent(event, g, c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }initialiseInGamePlayers(playersInGame);
        });

    }

    /**
     * Returns scene with layout of game.
     */
    public Scene Grid_GUI() throws IOException, ClassNotFoundException
    {


        Grid g = new Grid(rows,cols);
        g.createGrid(rows,cols,playersInGameArray.get(0).getColor());
        System.out.println("Rows "+rows+" Cols "+cols);
        Cell[][] cellsArray = new Cell[rows][cols];
        System.out.println(rows+" "+cols);
        Cell c = new Cell(cellsArray);
        for(int i =0 ;i<rows ; i++){
            for(int j =0 ;j<cols;j++){
                cellsArray[i][j] = new Cell(0);
            }
        }

        scene2 = new Scene(g.root, 720, 720);
        scene2.setFill(Color.BLACK);

        after(g,c);

        return scene2;
    }

    /**
     * Used to serialize the state of the game.
     */
    public static void serialize(int rows, int cols, int[][] c, String[][] a, int mouseClicks, int playersInGame, String[] serial_color)throws IOException
    {
        Serial serial1= new Serial(rows,cols, a,c, mouseClicks,playersInGame, serial_color );
        serial1.dummy =2;
        ObjectOutputStream out1= new ObjectOutputStream( new FileOutputStream("out.txt"));
            out1.writeObject(serial1);
            out1.close();
    }

    /**
     * Used to de-serialize the state of the game.
     */
    public static Serial deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in= new ObjectInputStream( new FileInputStream("out.txt"));
            Serial s1= (Serial)in.readObject();
            s1.display();
            in.close();
            return s1;
    }


    /**
     * Handles the event of explosion which might occur on a mouse click.
     */
    public void explosionEvent(MouseEvent event,Grid g, Cell c) throws IOException, ClassNotFoundException {
        int cellSize, xGridStart, yGridStart;
        if (rows == 9 && cols == 6) {
            cellSize = 70;
            xGridStart = 20;
            yGridStart = 70;
        } else {
            cellSize = 44;
            xGridStart = 20;
            yGridStart = 44;
        }
        double x = event.getSceneX();
        double y = event.getSceneY();
        System.out.println(x);
        System.out.println(y);
        x = x - xGridStart;
        y = y - yGridStart;
        x = x / cellSize;
        y = y / cellSize;
        x = Math.floor(x);
        y = Math.floor(y);

        if (x < cols + 1 && y < rows + 1) {
            if (mouseClicks > 0) {
                s2 = deserialize();
                System.out.println("Deserialized");
            }


//            g.changeGridColor(playersForSettings[colorIndex1].getColor());
            System.out.println(playerIndex1 + " " + playersInGameArray.get(playerIndex1).getColor());
            System.out.println("this" + (int) x + " " + (int) y);
            System.out.println(rows + " " + cols);

//            System.out.println("Checking "+checkIfWon(g,playersForSettings,playerIndex1));

            r = c.explosion((int) y, (int) x, g, rows, cols, playerIndex1, playersInGameArray, this);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(g.array[j][i] + "      ");
                }
                System.out.println();
            }

//            for(int i =0 ;i<1000000000;i++);


            System.out.println("After animation");
//            if(mouseClicks==4){
//                Sphere sph = (Sphere)g.root1[1][0].getChildren().get(0);
//                PhongMaterial ppp = (PhongMaterial)sph.getMaterial();
//                System.out.println("Color of i 1 j 0 = "+ppp.getDiffuseColor().getRed()+" "+ppp.getDiffuseColor().getGreen()+
//                                    " "+ppp.getDiffuseColor().getBlue());}
            //c.matchExistingOrbsToPlayers(playerIndex1, playersInGameArray, this, rows, cols, g);
//            System.out.println("Final players = "+playersInGame);


            //         serial1.initialize(g.array);
            if (r == 1)
                undo_click = 0;

            mouseClicks += r;
            playerIndex1 = (mouseClicks) % (playersInGame);
            colorIndex1 = (mouseClicks) % playersInGame;
            g.changeGridColor(playersInGameArray.get(colorIndex1).getColor());
            System.out.println("r = " + r);
            System.out.println("MouseClicks on addition of balls= " + mouseClicks);

            String[][] colorsOfPlayers = g.color(rows, cols);
            serialize(rows, cols, g.array, colorsOfPlayers, mouseClicks, playersInGame, serial_color);
            System.out.println("Serialized");

        }



    }

    /**
     * Method to initialise settings of players.
     */
    public void initialiseSettingsPlayers() {
        if (!initialisedPlayers) {
            for (int i = 0; i < 8; i++) {
                String x = "Player " + String.valueOf(i + 1);
                playersForSettings.add(new Players(x, defaultColorList[i]));
            }
            initialisedPlayers = true;
        }
    }

    /**
     * Method to initialise current players in the game.
     */
    public void initialiseInGamePlayers(int playerNumber) {
        if (!initialisedInGamePlayers) {
            playersInGameArray = new ArrayList<>();
            serial_color= new String[playerNumber];
            for (int i = 0; i < playerNumber; i++) {
                playersInGameArray.add(playersForSettings.get(i));
                Color co=playersForSettings.get(i).getColor();
                serial_color[i]=co.getBlue()+" "+co.getGreen()+" "+co.getRed();
            }
            initialisedInGamePlayers = true;
        }
    }

    /**
     * Returns scene with layout of Settings page.
     */
    public Scene makeSettingsPage(){



        settingsView = new GridPane();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(80);
        settingsView.getColumnConstraints().addAll(col1,col2);

        RowConstraints[] playerRows = new RowConstraints[9];
        playerRows[0] = new RowConstraints();
        playerRows[0].setPercentHeight(5);
        settingsView.getRowConstraints().add(playerRows[0]);
        for(int i = 1; i<9;i++){
            playerRows[i] = new RowConstraints();
            playerRows[i].setPercentHeight(96/8);
            settingsView.getRowConstraints().add(playerRows[i]);
        }

        backToMenuBtn = new Button("Back To Menu");
        backToMenuBtn.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        backToMenuBtn.setFont(new Font("Cambria",13));
        backToMenuBtn.setStyle("-fx-background-color: #181818;"+
                "-fx-padding: 5px;"+
                "-fx-background-radius: 0px;"+
                "-fx-font: 14px \"Cambria\";" +
                "-fx-border-radius: 0px;"+
                "-fx-text-fill: white;"+
                "-fx-border-color: #282828;"+
                "-fx-border-width: 2px;"+
                "-fx-font-weight : 300");
        backToMenuBtn.setOnAction(e -> {
            try {
                ButtonClick(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        GridPane.setHalignment(backToMenuBtn, HPos.LEFT);
//        GridPane.setHalignment(resumeBtn, HPos.CENTER);
        settingsView.add(backToMenuBtn,0,0);
//        GridPane.setMargin(backToMenuBtn,new Insets(5,0,5,20));

        Label preferences = new Label("                    Chain Reaction Preferences");
        preferences.setStyle("-fx-background-color: #181818;"+
                "-fx-background-radius: 2px;"+
                "-fx-font: 20px \"Cambria\";" +
                "-fx-border-radius: 0px;"+
                "-fx-text-alignment: center;"+
                "-fx-text-fill: red;"+
                "-fx-font-weight: 700");
        preferences.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
        GridPane.setHalignment(preferences,HPos.CENTER);
        settingsView.add(preferences,1,0);



        playerSettings = new Label[8];
        for( int i = 0; i<8 ; i++) {
            playerSettings[i] = new Label("Player " + (i+1) + " Settings"+"\n");
            playerSettings[i].setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
            settingsView.add(playerSettings[i],0,i+1);
            playerSettings[i].setFont(Font.font("Cambria", FontWeight.SEMI_BOLD, 20));
//            playerSettings[i].setPadding(new Insets(0,0,0,20));
//            playerSettings[i].setTextFill(Color.WHITE);
//            playerSettings[i].setStyle("-fx-border-color : white");
            playerSettings[i].setStyle("-fx-background-color: rgba(0,0,0,0.75),\n" +
                    "                           rgba(255,255,255,0.75),\n" +
                    "                           linear-gradient(to bottom,#282828 0%,#181818 100%);"+
                    "-fx-background-insets: 5 5 5 5, 5 5 5 5, 1;"+
                    "-fx-padding: 0px 0px 0px 20px;"+
                    "-fx-background-radius: 5px;"+
                    "-fx-font: 20px \"Cambria\";" +
                    "-fx-border-radius: 2px;"+
                    "-fx-text-fill: white;"+
                    "-fx-border-colot : white");
            GridPane.setFillHeight(playerSettings[i],true);
            GridPane.setFillWidth(playerSettings[i],true);
            GridPane.setColumnSpan(playerSettings[i],2);
          
//            playerSettings[i].setStyle("-fx-border-color: black");
        }
//        settingsView.setGridLinesVisible(true);
//        settingsView.set
//        settingsView.getStyleClass().add("myGridStyle");


//        scene3.setFill(Color.BLACK);
        StackPane rootpane = new StackPane();
        rootpane.getChildren().add(settingsView);
        rootpane.setStyle("-fx-background-color: #000000");
        scene3 = new Scene(rootpane,720,720);
        return scene3;
    }


    /**
     * Defines functionality of Player's Settings Label when Clicked
     */
    public void LabelClick(MouseEvent event, int i){
        Scene[] scenes = makeNameAndColorPickerPage();
        pstage.setScene(scenes[i]);
    }

    /**
     * Sets up the primary Stage with title and initial scene.
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        pstage = primaryStage;
        primaryStage.setTitle("Chain Reaction");
        scene1 = makeInitialPage();
        primaryStage.setResizable(false);


        primaryStage.setScene(scene1);


        primaryStage.getIcons().add(new Image("https://lh5.ggpht.com/xUefJ0Wrh0pyUuRnCAkRggnJbCGcMoKzARwxtGN1rZEK8cUvwHCLh7DPZbUv059aoA=w300"));
        primaryStage.show();
    }
}
