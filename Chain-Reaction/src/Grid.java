import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class Grid extends Application 
{

	Group root= new Group();
	ObservableList<Node> list= root.getChildren();
	int array[][]= new int[6][9];

	public static void main(String[] args) 
	{
	launch(args);
	}


	@Override
	public void start(Stage primaryStage) 
	{
		primaryStage.setTitle("MyJavaFX");
		createGrid();
		//createSphere();

		Scene scene = new Scene(root, 450, 600);
		primaryStage.setScene(scene);
		scene.setFill(Color.BLACK);
		// PerspectiveCamera camera = new PerspectiveCamera(true);
  //       camera.setFarClip(300 * 6);
  //       camera.setTranslateZ(-3 * 300);
        // scene.setCamera(camera);
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() 
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
	        	createSphere(x,y);
	    	}
		});
		
		primaryStage.show();
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
		sphere.setRadius(10);
		PhongMaterial p= new PhongMaterial();
	    p.setDiffuseColor(Color.BLUE);
	    sphere.setMaterial(p);
		if(array[(int)x-1][(int)y-1]==0)
	    {
    		sphere.setTranslateX(x*48+3);
			sphere.setTranslateY(y*48+36);
    		array[(int)x-1][(int)y-1]=1;
    		list.add(sphere);
	    }
	    else if(array[(int)x-1][(int)y-1]==1)
	    {
    		sphere.setTranslateX(x*48+12);
			sphere.setTranslateY(y*48+36);
    		array[(int)x-1][(int)y-1]=2;
    		list.add(sphere);

    		Circle circle= new Circle(12);
			circle.setFill(Color.TRANSPARENT);
			//circle.setStroke(Color.BLUE);
			list.add(circle);
			circle.setTranslateX(x*48+4);
			circle.setTranslateY(y*48+36);
			Rotate rotate= new Rotate();
	        rotate.setAngle(30);
	        rotate.setAxis(Rotate.X_AXIS);
	        circle.getTransforms().addAll(rotate);

    		animation = new ParallelTransition(createTransition(circle, sphere));
	    	animation.play();
	    }
	    else if(array[(int)x-1][(int)y-1]==2)
	    {
    		sphere.setTranslateX(x*48);
			sphere.setTranslateY(y*48+27);
    		array[(int)x-1][(int)y-1]=3;
    		list.add(sphere);

    		Circle circle= new Circle(12);
			circle.setFill(Color.TRANSPARENT);
			//circle.setStroke(Color.BLACK);
			list.add(circle);
			circle.setTranslateX(x*48);
			circle.setTranslateY(y*48+36);
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
	{
		for(int i=0; i<10; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			line.setStartX(20); 
			line.setStartY(50+(50*i));         
			line.setEndX(320); 
			line.setEndY(50+(50*i));
			list.add(line);
		}

		for(int i=0; i<10; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			line.setStartX(32); 
			line.setStartY(68+(46*i));         
			line.setEndX(308); 
			line.setEndY(68+(46*i));
			list.add(line);
		}

		for(int i=0; i<7; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			line.setStartX(20+(50*i)); 
			line.setStartY(50);         
			line.setEndX(20+(50*i)); 
			line.setEndY(500);
			list.add(line);
		}

		for(int i=0; i<7; i++)
		{
			Line line= new Line();
			line.setStroke(Color.BLUE);
			line.setStartX(32+(46*i)); 
			line.setStartY(68);         
			line.setEndX(32+(46*i)); 
			line.setEndY(482);
			list.add(line);
		}

		for(int i=0; i<10; i++)
		{
			for(int j=0; j<7; j++)
			{
				Line line= new Line();
				line.setStroke(Color.BLUE);
				line.setStartX(20+(50*j)); 
				line.setStartY(50+(50*i));         
				line.setEndX(32+(46*j)); 
				line.setEndY(68+(46*i));
				list.add(line);
			}
		}
	}

	
}