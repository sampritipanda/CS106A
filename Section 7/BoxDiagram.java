import acm.program.*;
import acm.graphics.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class BoxDiagram extends GraphicsProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void run(){
		add(new JLabel("Name "), SOUTH);
		
		textField = new JTextField(25);
		add(textField, SOUTH);
		textField.setActionCommand("Add");
		textField.addActionListener(this);
		
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		
		addActionListeners();
		addMouseListeners();
	}
	
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		if(cmd.equals("Add")){
			String text = textField.getText();
			GLabelledRect box = new GLabelledRect(BOX_WIDTH, BOX_HEIGHT, text);
			add(box, (getWidth() - BOX_WIDTH)/2, (getHeight() - BOX_HEIGHT)/2);
			boxes.put(text, box);
		}
		else if(cmd.equals("Remove")){
			String text = textField.getText();
			GLabelledRect box = boxes.get(text);
			boxes.remove(box);
			remove(box);
		}
		else if(cmd.equals("Clear")){
			removeAll();
			boxes.clear();
			textField.setText("");
		}
	}
	
	public void mousePressed(MouseEvent e ){
		obj = getElementAt(e.getX(), e.getY());
		lastX = e.getX();
		lastY = e.getY();
	}
	
	public void mouseDragged(MouseEvent e){
		int dx = e.getX() - lastX;
		int dy = e.getY() - lastY;
		if(obj != null){
			obj.move(dx, dy);
		}
		lastX = e.getX();
		lastY = e.getY();
	}
	
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	private JTextField textField;
	private HashMap<String, GLabelledRect> boxes = new HashMap<String, GLabelledRect>();
	private int lastX;
	private int lastY;
	private GObject obj;
	
}
