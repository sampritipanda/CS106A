import acm.graphics.*;

public class GLabelledRect extends GCompound {
	
	public GLabelledRect(double width, double height, String text){
		GRect rect = new GRect(width, height);
		add(rect, -width/2, -height/2);
		
		GLabel label = new GLabel(text);
		add(label, -label.getWidth()/2, label.getAscent()/2);
	}
}
