package application;
	
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			double width = 500, height = 500;
			double ctrlPaneHeight = .05 * height;
			
			Pane root = new Pane();
			root.setMinSize(width, height); 
			
			//----- drawing pane -------
			Pane displayPane = new Pane();		
			
			ArrayList<CircleFX> allCircles = new ArrayList<CircleFX>();
			int n = 500;
			double maxRadius = 20;
			Label label = new Label("N: " + n + ", Max Radius: " + maxRadius);
		    label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));		    
			
			drawAxis(displayPane, width, height);
			generateRandomCircles(allCircles, n, -width/2.0 + maxRadius/2.0, -height/2.0 + maxRadius/2.0, width/2.0 - maxRadius/2.0, height/2.0 - maxRadius/2.0 - ctrlPaneHeight);		
			// Sort all the elements of allCircles based on distance to the origin.
			Collections.sort(allCircles);
			
			//set radius based on distance to origin
			for(int i = 0; i < n; i++) {
				// Set the radius of each circle in the allCircles list
				// based on distance to the origin. Circles closer to the origin should have smaller radius
				// than those further away. No circle should have a radius larger than maxRadius. 
				allCircles.get(i).setRadius(allCircles.get(i).getDistanceToOrigin() / 350 * maxRadius);
				System.out.println(allCircles.get(i).getRadius());
			}
			
			// Displays all circles in displayPane
			displayCircles(displayPane, allCircles, width, height);	
			
			//----- add panels and controls ------
			root.getChildren().addAll(label, displayPane);
			
			Scene scene = new Scene(root, width, height);		
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Distances From Origin");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void drawAxis(Pane pane, double width, double height) {
		// Draw horizontal and vertical axis on pane
		Line vertical = new Line(width/2, 0, width/2, height);
		Line horizontal = new Line(0, height/2, width, height/2);
		vertical.setStroke(Color.RED);
		horizontal.setStroke(Color.RED);
		pane.getChildren().addAll(vertical, horizontal);
	}
	
	public void generateRandomCircles(ArrayList<CircleFX> list, int n, double xMin, double yMin, double xMax, double yMax) {
		// Create random n circles with xMin <= x <= xMax, yMin <= y <= yMax
		// Give those circles random colors and a default radius of 1.0
		// Add all circles to list
		for (int i = 0; i < n; i++) {
			double x = Math.random() * (xMax - xMin), y = Math.random() * (yMax - yMin);
			double r = Math.random(), g = Math.random(), b = Math.random();
			double randomOpacity = Math.random();
			Color randomStroke = Color.color(r, g, b);
			CircleFX tempCircle = new CircleFX(x, y, 1, randomStroke, null, randomOpacity);
			list.add(tempCircle);
		}
	}
	
	public void displayCircles(Pane pane, ArrayList<CircleFX> list, double width, double height) {		
		// Display all circles of list into pane by 
		// iterating through and calling the draw method of each circle
		for (int i = 0; i < list.size(); i++) {
			list.get(i).draw(pane, width, height);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
