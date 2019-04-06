package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleFX implements Comparable<CircleFX> {
	private double x, y; //these coordinates are given in Cartesian system where the origin is at (x, y) = (0, 0)
	private double radius;
	private Color strokeColor, fillColor;
	private double opacity;
	
	public CircleFX() {
		// Initialize data fields x, y and radius with default values of 0.0, 0.0 and 1.0
		x = 0;
		y = 0;
		radius = 1;
	}
	
	/** (x, y) are in Cartesian coordinate system */
	public CircleFX(double x, double y, double radius) {
		// Initialize data fields x, y and radius with given parameter values
		this.x = x;
		this.y = y;
		this.radius = radius;
		//as well as Green and White for stroke and fill color respectively		
		strokeColor = Color.GREEN;
		fillColor = Color.WHITE;
	}
	
	/** (x, y) are in Cartesian coordinate system */
	public CircleFX(double x, double y, double radius, Color strokeColor, Color fillColor, double opacity) {
		// Initialize data fields x, y, radius, strokeColor and Opacity with given parameters values
		// if fillColor equals null, set it to a random rgb color. Otherwise, set it to the fillColor parameter value
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.strokeColor = strokeColor;
		this.opacity = opacity;
		if (fillColor == null) {
			double red = Math.random(), green = Math.random(), blue = Math.random();
			this.fillColor = Color.color(red, green, blue, opacity);
		} else this.fillColor = fillColor;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getDistance(CircleFX c) {
		// Returns the distance from the current circle's center to the 
		// circle's center passed as parameter (c)
		double hypotenuse = Math.abs(Math.sqrt(Math.pow(c.getX() - x, 2) + Math.pow(c.getY() - y, 2)));
		return hypotenuse;
	}
	
	public double getDistanceToOrigin() {
		// Returns the distance from the current circle's center to the origin (0, 0)
		double hypotenuse = Math.sqrt(Math.pow(250 - x, 2) + Math.pow(250 - y, 2));
		return hypotenuse;
	}

	public void draw(Pane pane, double width, double height) {		
		//Convert Cartesian coordinates to screen coordinates
		double x0 = x + width/60; // horizontal screen coordinate. Uses values of x and width to calculate it.
		double y0 = y + height/15; // vertical screen coordinate. Uses values of y and height to calculate it.
		
		Circle circle = new Circle(x0, y0, radius);
		circle.setStrokeWidth(1.0);
		circle.setStroke(strokeColor);
		circle.setFill(fillColor);
		
		pane.getChildren().add(circle);
	}
	
	@Override
	public int compareTo(CircleFX circle) {
		// Compare the distance of the current circle to the origin
		//with that of the circle's parameter. 
		//Return -1 if the current circle is closer to the origin
		//Return 1 if the current circle is further away to the origin
		//Return 0 if both circles are at the same distance to the origin
		if (circle.getDistanceToOrigin() < getDistanceToOrigin()) return -1;
		else if (getDistanceToOrigin() < circle.getDistanceToOrigin()) return 1;
		else return 0;
	}
}
