package application;




import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;


public class move extends JButton {
	private static final ArrayList<Integer> VALID = new ArrayList<Integer> (initValid());
	public int row;
	public int col;
	private boolean modifiable;//to make change in the value we entered
	//private static final long serialVersionUID = 1; 
	
	//constructor
	public move ( int row, int col) {
		this.row = row;
		this.col = col;
		this.modifiable = true;
	}

	//if the cell equals 0 it makes the cell empty else sets the value to i and returns i
	public int getValue() {
		int i = 0;
		
		if(super.getText().equals(""))
		{
			i = 0;
		}
		else
		{
			i = Integer.parseInt(super.getText());//returns button text and turns it to int
		}
		
		return i; 
	}
	
	
	public boolean isModifiable() {
		return modifiable;
	}

	//sets the modifiable false and uses this if the square is used when creating the sudoku table 
	public void setUnMod(){
		modifiable = false;
	}
	
	//it makes the modifiable squares in other words the squares which user can enter the numbers empty
	public void clear(){
		
		if(modifiable)
		{
			super.setText("");//abstract button
		}
	}

	//if we enter 0 to the box the square becomes empty else it sets the value to that box if the value is valid
	public void setValue(int i) {
		
		if(modifiable)
		{
			if(i == 0)
			{
				super.setText("");
			}
			else if(VALID.contains(i))
			{
				super.setText(String.valueOf(i));
			}
		}
	}
	
	//if we can modify the square in other words if we can enter numbers on that box that numbers will appear as blue 
	public void reset() {
		
		modifiable = true;
		this.setForeground(Color.blue);
		clear();
		
	}
	
	//creates an array list to hold the valid numbers from 1 to 9 . It uses the ArrayList class for this  
	public static ArrayList<Integer> initValid(){
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i = 1; i <= 9; i++)
		{
			temp.add(i);
		} return temp;
		 
	}
}

