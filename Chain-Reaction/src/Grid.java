import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.collections.ObservableList;

public class Grid extends Application 
{
	public static void main(String[] args) 
	{
	launch(args);
	}
	
	//Override the start method in the Application class
	@Override
	public void start(Stage primaryStage) 
	{
		primaryStage.setTitle("MyJavaFX");
		Group root= new Group();
		ObservableList<Node> list= root.getChildren();

		for(int i=0; i<10; i++)
		{
			Line line= new Line();
			line.setStartX(20); 
			line.setStartY(50+(50*i));         
			line.setEndX(320); 
			line.setEndY(50+(50*i));
			list.add(line);
		}

		for(int i=0; i<10; i++)
		{
			Line line= new Line();
			line.setStartX(32); 
			line.setStartY(68+(46*i));         
			line.setEndX(308); 
			line.setEndY(68+(46*i));
			list.add(line);
		}

		for(int i=0; i<7; i++)
		{
			Line line= new Line();
			line.setStartX(20+(50*i)); 
			line.setStartY(50);         
			line.setEndX(20+(50*i)); 
			line.setEndY(500);
			list.add(line);
		}

		for(int i=0; i<7; i++)
		{
			Line line= new Line();
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
				line.setStartX(20+(50*j)); 
				line.setStartY(50+(50*i));         
				line.setEndX(32+(46*j)); 
				line.setEndY(68+(46*i));
				list.add(line);
			}
		}

		Scene scene = new Scene(root, 450, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}