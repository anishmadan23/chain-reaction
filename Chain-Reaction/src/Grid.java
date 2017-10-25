import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Grid
{
	public int cellOffset, xLeftShift, yBottomShift,xGridStart,yGridStart,cellSize;
	Group root;
	ObservableList<Node> list;
	int array[][];
	GUI grc = new GUI();
	int rows = grc.rows;
	int cols = grc.cols;


	public Grid(){
		root = new Group();
		list = root.getChildren();
		array = new int[cols][rows];
	}

	public void calculateOffsetsForGrid(){
		if(rows == 9 && cols == 6){
			cellSize = 50;
			xGridStart = 20;
			yGridStart = 40;
			xLeftShift = 12;
			yBottomShift = 18;
			cellOffset = 2;
		}
		else{
			cellSize = 45;
			xGridStart = 20;
			yGridStart = 30;
			xLeftShift = 10;
			yBottomShift = 16;
			cellOffset =2;	//xLeftShift, yBottomShift and CellOffset are proportional, eg XL=15,YBS=24,CO=3
		}
	}
	Duration DURATION = Duration.seconds(4);
    Animation animation;

	Transition createTransition(Circle path, Sphere node) 
	{
        PathTransition t = new PathTransition(DURATION, path, node);
        t.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        t.setCycleCount(Timeline.INDEFINITE);
        t.setInterpolator(Interpolator.LINEAR);
        return t;
    }

	public void createSphere(double x, double y)
	{
		Sphere sphere= new Sphere();
		sphere.setRadius(9);
		PhongMaterial p= new PhongMaterial();
	    p.setDiffuseColor(Color.BLUE);
	    sphere.setMaterial(p);
		if(array[(int)x][(int)y]==0)
	    {
    		sphere.setTranslateX((x+1)*cellSize);
			sphere.setTranslateY((y+1)*cellSize+(cellSize/4));
    		array[(int)x][(int)y]=1;
    		list.add(sphere);
	    }
	    else if(array[(int)x][(int)y]==1)
	    {
    		sphere.setTranslateX((x+1)*cellSize);
			sphere.setTranslateY((y+1)*cellSize+(cellSize/4));
    		array[(int)x][(int)y]=2;
    		list.add(sphere);

    		Circle circle= new Circle(12);
			circle.setFill(Color.TRANSPARENT);
			//circle.setStroke(Color.BLUE);
			list.add(circle);
			circle.setTranslateX((x+1)*cellSize);
			circle.setTranslateY((y+1)*cellSize+(cellSize/4));
			Rotate rotate= new Rotate();
	        rotate.setAngle(30);
	        rotate.setAxis(Rotate.X_AXIS);
	        circle.getTransforms().addAll(rotate);

    		animation = new ParallelTransition(createTransition(circle, sphere));
	    	animation.play();
	    }
	    else if(array[(int)x][(int)y]==2)
	    {
    		sphere.setTranslateX((x+1)*cellSize);
			sphere.setTranslateY((y+1)*cellSize+(cellSize/4));
    		array[(int)x][(int)y]=3;
    		list.add(sphere);

    		Circle circle= new Circle(12);
			circle.setFill(Color.TRANSPARENT);
			//circle.setStroke(Color.BLACK);
			list.add(circle);
			circle.setTranslateX((x+1)*cellSize);
			circle.setTranslateY((y+1)*cellSize+(cellSize/4));
			Rotate rotate= new Rotate();
	        rotate.setAngle(30);
	        rotate.setAxis(Rotate.Y_AXIS);
	        circle.getTransforms().addAll(rotate);

    		animation = new ParallelTransition(createTransition(circle, sphere));
	    	animation.play();
	    }
		//System.out.println(array[1][1]);
	}

	public void createGrid()
	{	Button undoBtn  = new Button("Undo");
		undoBtn.setPrefSize(120,40);
		undoBtn.setFont(Font.font("Arial", FontWeight.BOLD,20));
		undoBtn.setLayoutX(450);
		undoBtn.setLayoutY(100);
		list.add(undoBtn);


		final ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setLayoutX(450);
		comboBox.setLayoutY(300);
		comboBox.getItems().addAll("New Game","Go to Main Menu");
		comboBox.setValue("Options");
		comboBox.setPrefSize(150,40);
		comboBox.setStyle("-fx-font: 15px \"Serif\";");
		list.add(comboBox);

		calculateOffsetsForGrid();

		for(int i=0; i<rows+1; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			line.setStartX(xGridStart);
			line.setStartY(yGridStart+(cellSize*i));
			line.setEndX(xGridStart+(cellSize*cols));
			line.setEndY(yGridStart+(cellSize*i));
			list.add(line);
		}

		for(int i=0; i<rows+1; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			line.setStartX(xGridStart+xLeftShift);
			int effCell = cellSize - cellOffset;
			line.setStartY(yGridStart+yBottomShift+(effCell*i));
			line.setEndX((effCell*(cols)+(xLeftShift+xGridStart)));
			line.setEndY(yGridStart+yBottomShift+(effCell*i));
			list.add(line);
		}

		for(int i=0; i<cols+1; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			line.setStartX(xGridStart+(cellSize*i));
			line.setStartY(yGridStart);
			line.setEndX(xGridStart+(cellSize*i));
			line.setEndY(cellSize*(rows+1));
			list.add(line);
		}

		for(int i=0; i<cols+1; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			int effCell = cellSize - cellOffset;
			line.setStartX(xGridStart+xLeftShift+(effCell*i));
			line.setStartY(yGridStart+yBottomShift);
			line.setEndX(xGridStart+xLeftShift+(effCell*i));
			line.setEndY(effCell*(rows)+yGridStart+yBottomShift);
			list.add(line);
		}

		for(int i=0; i<rows+1; i++)
		{
			for(int j=0; j<cols+1; j++)
			{
				Line line= new Line();
				line.setStroke(Color.BLUE);
				line.setStartX(xGridStart+(cellSize*j));
				line.setStartY(yGridStart+(cellSize*i));
				int effCell = cellSize - cellOffset;
				line.setEndX(xGridStart+xLeftShift+(effCell*j));
				line.setEndY(yGridStart+yBottomShift+(effCell*i));
				list.add(line);
			}
		}
	}

	
}