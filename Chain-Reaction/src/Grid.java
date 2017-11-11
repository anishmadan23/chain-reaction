import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;
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
import java.util.concurrent.TimeUnit;

public class Grid
{

	ComboBox<String> comboBox;
	public int cellOffset, xLeftShift, yBottomShift,xGridStart,yGridStart,cellSize;
	PathTransition path1,path2,path3;
	Group[][] root1;
	Group root = new Group();
	ObservableList<Node> list;
	int array[][];

	GUI grc = new GUI();
	int rows = grc.rows;
	int cols = grc.cols;



	public Grid(int rows, int cols){
		root1 = new Group[rows][cols];
		list = root.getChildren();
		array = new int[cols][rows];
		Cell[][] cellArray = new Cell[rows][cols];
		for(int i = 0 ;i<rows; i++){
			for(int j = 0;j<cols ;j++){

				root1[i][j] = new Group();
				cellArray[i][j] = new Cell(0);
			}
		}

	}


	public void calculateOffsetsForGrid(int rows, int cols){
		if(rows == 9 && cols == 6){
			cellSize = 50;
			xGridStart = 20;
			yGridStart = 50;
			xLeftShift = 12;
			yBottomShift = 18;
			cellOffset = 4;
		}
		else
		{
			cellSize = 45;
			xGridStart = 20;
			yGridStart = 45;
			xLeftShift = 10;
			yBottomShift = 16;	
			cellOffset =2;	//xLeftShift, yBottomShift and CellOffset are proportional, eg XL=15,YBS=24,CO=3
		}
	}

	Duration DURATION = Duration.seconds(4);
	Animation animation, animation1;
	Sphere sphere11;
	Line line;

	Transition createTransition1(Shape path, Sphere node)
	{
		PathTransition t = new PathTransition(Duration.seconds(0.5), path, node);
		t.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		t.setCycleCount(1);
		t.setInterpolator(Interpolator.LINEAR);
		return t;
	}

	public void move(int inX, int inY, int toX, int toY)
	{
		line= new Line();
		line.setStartX((inY + 1) * cellSize);
		line.setStartY((inX + 1) * cellSize + (cellSize / 2));
    	line.setEndX((toY + 1) * cellSize);
    	line.setEndY((toX + 1) * cellSize + (cellSize / 2));
    	line.setStroke(Color.TRANSPARENT);

    	sphere11 = new Sphere();
		sphere11.setRadius(9);
		sphere11.setTranslateX((inX + 1) * cellSize);
		sphere11.setTranslateY((inY + 1) * cellSize + (cellSize / 2));
		PhongMaterial p1 = new PhongMaterial();
		p1.setDiffuseColor(Color.BLUE);
		sphere11.setMaterial(p1);
		
		root1[toX][toY].getChildren().add(sphere11);
		//root1[toX][toY].getChildren().add(line);

    	animation1 = new ParallelTransition(createTransition1(line, sphere11));
		animation1.play();
	}

	public void shiftOrbs(int inX, int inY, int toX, int toY,Cell c)
	{

		move(inX, inY,toX,toY);

		System.out.println("Array val :"+array[inY][inX]);
		if(array[inY][inX]>0) 
		{
			System.out.println("Shifting");
			System.out.println(root1[inX][inY].getChildren().size() + " is the size");
			System.out.println("coords are " + inX + " " + inY + " " + toX + " " + toY);
			Node x = root1[inX][inY].getChildren().get(0);
			root1[inX][inY].getChildren().remove(x);
			array[inY][inX]--;


		}
	}

	Transition createTransition(Shape path, Sphere node)
	{
		PathTransition t = new PathTransition(DURATION, path, node);
		t.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		t.setCycleCount(Timeline.INDEFINITE);
		t.setInterpolator(Interpolator.LINEAR);
		return t;
	}

	public Cell[][] createSphere(double x, double y,Players[] p, int playerIndex,Cell c)
	{
		if(c.grid[(int)y][(int)x].getOrbs()==c.getCriticalMass((int)y,(int)x,rows,cols)-1)
			DURATION = Duration.seconds(2);
		else
			DURATION = Duration.seconds(4);
		
		Sphere sphere = new Sphere();
		sphere.setRadius(9);
		PhongMaterial ph = new PhongMaterial();
		ph.setDiffuseColor(p[playerIndex].getColor());
		sphere.setMaterial(ph);
		
		if (array[(int) x][(int) y] == 0) 
		{
			System.out.println((int) x + " " + (int) y);
			sphere.setTranslateX((x + 1) * cellSize);
			sphere.setTranslateY((y + 1) * cellSize + (cellSize / 2));
			array[(int) x][(int) y] = 1;
    		c.grid[(int)y][(int)x].setOrbs(1);
			root1[(int)y][(int)x].getChildren().add(sphere);

		} 

		else if (array[(int) x][(int) y] == 1 ) {
			sphere.setTranslateX((x + 1) * cellSize);
			sphere.setTranslateY((y + 1) * cellSize + (cellSize / 2));
			array[(int) x][(int) y] = 2;
			c.grid[(int)y][(int)x].setOrbs(2);

			
			animate((int)x,(int)y, sphere);

			root1[(int)y][(int)x].getChildren().add(sphere);

		} 

		else if (array[(int) x][(int) y] == 2) 
		{
			sphere.setTranslateX((x + 1) * cellSize);
			sphere.setTranslateY((y + 1) * cellSize + (cellSize / 2));
			array[(int) x][(int) y] = 3;
			c.grid[(int)y][(int)x].setOrbs(3);

			animate((int)x,(int)y, sphere);			

			root1[(int)y][(int)x].getChildren().add(sphere);

		}

		

		return c.grid;
	}

	public void animate(int x, int y, Sphere sphere)
		{
			if(array[x][y]==2)
			{
				Circle circle = new Circle(12);
				circle.setFill(Color.TRANSPARENT);
				circle.setTranslateX((x + 1) * cellSize);
				circle.setTranslateY((y + 1) * cellSize + (cellSize / 2));
				Rotate rotate = new Rotate();
				rotate.setAngle(180);
				rotate.setPivotX(0);
				rotate.setPivotY(0);
				rotate.setAxis(Rotate.Y_AXIS);
				circle.getTransforms().addAll(rotate);
				
				animation = new ParallelTransition(createTransition(circle, sphere));
				animation.play();
			}
			else if(array[x][y]==3)
			{
				Circle circle = new Circle(12);
				circle.setFill(Color.TRANSPARENT);
				circle.setTranslateX((x + 1) * cellSize);
				circle.setTranslateY((y + 1) * cellSize + (cellSize / 2));
				Rotate rotate = new Rotate();
				rotate.setAngle(180);
				rotate.setAxis(Rotate.X_AXIS);
				rotate.setPivotX(5);
				rotate.setPivotY(0);
				circle.getTransforms().addAll(rotate);

				animation = new ParallelTransition(createTransition(circle, sphere));
				animation.play();
			}
		}



	public void createGrid(int rows, int cols)
	{	Button undoBtn  = new Button("Undo");
		undoBtn.setPrefSize(120,40);
		undoBtn.setFont(Font.font("Arial", FontWeight.BOLD,20));
		undoBtn.setLayoutX(450);
		undoBtn.setLayoutY(100);
		list.add(undoBtn);

		comboBox = new ComboBox<>();
		comboBox.setLayoutX(450);
		comboBox.setLayoutY(300);
		comboBox.getItems().addAll("New Game","Go to Main Menu");
		comboBox.setValue("Options");
		comboBox.setPrefSize(150,40);
		comboBox.setStyle("-fx-font: 15px \"Serif\";");
		list.add(comboBox);

		calculateOffsetsForGrid(rows,cols);

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
		for(int i=0; i<rows; i++) {
			for (int j = 0; j < cols; j++) {
				list.add(root1[i][j]);
			}
		}

	}



}