package application;




import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

//for inheritance extends the JPanel(for layout) and implements ActiionListener interface
public class sudoku extends JPanel implements ActionListener
{
	//Public Static Final Fields
	public static final int N = 9;
	public static final int HARD = N;
	public static final int MED = 2 * N;
	public static final int EASY = 4*N;
	public static final int serialVersionUID = 1;//for content and help button
	 
	
	
	//Public Fields
	public move[][] board = new move[N][N];
	public JFrame frame;//JFrame provides a window on screen. It is a base for other components such as MenuBar 
	public Container con;
	public menu menu;
	public int clickCount = 0;
	
	//Creates sudoku obj
	public static void main(String[] args){
		new sudoku();//invokes sudoku method
	}
	
	//Creates a sudoku game
	public sudoku() {
		init();//invokes the method which sets up the board
		
		frame = new JFrame("Sudoku");
		con = frame.getContentPane(); //layout (Jframe)
		menu = new menu(this);             
		frame.setJMenuBar(menu.createMenuBar());//layout
		con.add(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
		frame.setSize(700, 700);
		frame.setResizable(false);//frame is not Resizable
		frame.setVisible(true);	//shows window(or hides)
		
		GridLayout grid = new GridLayout(N, N); //num of rows and columns
		grid.setVgap(2); //vertical gap between components
		grid.setHgap(2);//horizontal gap between components

		this.setLayout(grid);//sets the layout for this container related to the grid

	}
	
	//actionPerformer method comes from action listener interface. When we perform the action we invoke this method without this method we cannot enter a num to the box.
	//it checks every move we make and if we try to enter something like 45 method is throwing an error and not accepting the entry
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() instanceof move)
		{
			move temp = (move)e.getSource();
			String in = JOptionPane.showInputDialog(null);
		
			clickCount++;
		
			try
			{
				temp.setValue(Integer.parseInt(in));
			}
			catch (Exception err)
			{
			
			}
		}
	}
	
	//what will be in the about menu when we click. In menu class we invoked this method and we also added a shortcut to the about button
	public void about()
	{
		String str = "Author: TEDU   \nVersion date: 2022  ";
		String title = "About - Sudoku v" + serialVersionUID;
		
		JOptionPane.showMessageDialog(this, str, title, JOptionPane.INFORMATION_MESSAGE);
	}
	

	//what will be in the help menu when we click. In menu class we invoked this method and we also added a shortcut to the content button
	public void help()
	{
		String str = "A number game in which you have to write a number  \n "
				+ "between 1 and 9 in each small box of a 9x9 square. \n " + 
	                  "Fill in the grid so that\n     every row,\n" +
				"     every column, and\n     every 3 x 3 box\ncontains" +
				" the digits 1 through 9.\n";
		String title = "Sudoku v" + serialVersionUID + " - Help";
		
		JOptionPane.showMessageDialog(this, str, title, JOptionPane.INFORMATION_MESSAGE);		
	}
	
	
	//creates a new game board. We invoke this method in menu class
	public void newGame(int diff)
	{
		reset(); // calls reset method which clears the board including game squares itself
		
		ArrayList<Integer> valid = new ArrayList<Integer>(spaceCollection());
		int count = 0;
		
		if (diff == 1)
			count = EASY;
		
		else if(diff == 2)
			count = MED;
		
		else if (diff == 3)
			count = HARD;
		
		for(int conut = 0; count > 0 ; count--)	{//it sets the numbers randomly in other words it creates the sudoku table
			
			int randRow = valid.get((int)(Math.random() * N));//gets a random row
			int randCol = valid.get((int)(Math.random() * N));//gets a random column
			int randVal = 1 + (int)(Math.random() * N);//gets a random value
			
			if(validMove(randRow, randCol, randVal))
				//invokes the validMOve method which returns true or false weather the box is modifiable or not. If the box is not  modifiable it puts the rand value on that box 				
			{
				board[randRow][randCol].setForeground(Color.black);
				board[randRow][randCol].setValue(randVal);
				board[randRow][randCol].setUnMod();//it disables the changes
			}
		}
	}

	
	public void reset() {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				board[row][col].reset();
			}
		}
	}
	
	
	
	
	//return int array list N to N-1 for rows, columns and cubs
	private ArrayList<Integer> spaceCollection ()//help
	{
		ArrayList<Integer> spaceList = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			spaceList.add(i);//creates the spaceList 
		} return spaceList;
	}

	//checks if the move is valid
	private boolean validMove(int row, int col, int move) {
		int orig = 0;
		boolean result = false;
		
		if(board[row][col].isModifiable()) {//move class method which shows us if we can write a number on that box
			orig = board[row][col].getValue();//if it is modifiable invokes getValue method and gets value on that box
					
			board[row][col].setValue(move);//invokes the setValue method and sets the random value on that index
		
			if(validRow(row) && validCol(col) && validCube(findCube(row, col))) {//checks row, column and 3x3 square if it is valid or not and if it is the method returns true
				result = true;
			}
		
			board[row][col].setValue(orig);//if row, column and 3x3 square is not valid it does not change the square and returns false
		} return result;
	}
	
	//checks if the row is valid to not assign same number again when building the table
	private boolean validRow(int row) {
		ArrayList<Integer> num = new ArrayList<Integer> (move.initValid());
		boolean valid = true;
		
		for(int i = 0; i < 9; i++) {
			Integer temp = board[row][i].getValue();
			if(num.contains(temp)){//returns true if the list contains the number
				num.remove(temp);//removes the element from the list if it is present
			}
			else if (temp != 0) {
				valid = false;
				break;
			}
		}
		
		return valid;
	}
	
	//checks if the column is valid
	private boolean validCol(int col)
	{
		ArrayList<Integer> num = new ArrayList<Integer> (move.initValid());
		boolean valid = true;
		
		for(int i = 0; i < 9; i++) {
			Integer temp = board[i][col].getValue();
			if(num.contains(temp))//returns true if the list contains the number
				num.remove(temp);//removes the element from the list if it is present
			
			else if (temp != 0)	{
				valid = false;
				break;
			}
		}
		
		return valid;
	}
	
	//checks if the 3x3 square is valid
	private boolean validCube(int cube) {
		ArrayList<Integer> num = new ArrayList<Integer> (move.initValid());
		int enterRow = 0, enterCol = 0;
		boolean valid = true;
		
		if (cube == 1)
			enterCol=3;
		
		else if(cube == 2)
			enterCol =6;
		
		else if (cube ==3)
			enterRow = 3;
		
		else if (cube == 4) {
				enterRow = 3;
				enterCol = 3;
		}

		else if (cube == 5) {
				enterRow = 3;
				enterCol = 6;
		}

		else if (cube == 6) 
				enterRow = 6;
		
		
		else if (cube == 7) { 
			enterRow = 6;
			enterCol = 3;
	}
		else if (cube == 8) {
			enterRow = 6;
			enterCol = 6;
	}
	
				
			
		//looks the row and column
		for(int i = enterRow; i < enterRow + 3 ; i++) {
			for(int j = enterCol; j < enterCol + 3 ; j++) {
				Integer temp = board[i][j].getValue();
				if(temp != 0)	{
					if(num.contains(temp)) //if the num list contains the number returns true
						num.remove(temp);//removes the number from num list
					
					else 
						valid = false;
						
				}
			}
		}
		
		return valid;
	}
	
	//determine the cube depending one the row and column
	private int findCube(int r, int c)
	{
		int cube = 0;
		
		if(r < 3 && c < 3)
			cube = 0;
		
		else if (r < 3 && c < 6)
			cube = 1;
	
		else if (r < 3)
			cube = 2;
		
		else if(r < 6 && c < 3)
			cube = 3;
		
		else if (r < 6 && c < 6)
			cube = 4;
		
		else if (r < 6)
			cube = 5;
	
		else if(c < 3)
			cube = 6;
		
		else if (c < 6)
			cube = 7;
		
		else
			cube = 8;
		
		return cube; 
		
	}
	

	//sets up the board when it is called
	private void init()
	{
		Font font = new Font("Ariel", Font.PLAIN, 31);
		move m;
		
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 9; col++) {//which boxes are white which boxes are pink
				m = new move(row, col);
				m.setBackground(Color.white);
				
				if (row <= 2 && (col <= 2 || col > 5))
					m.setBackground (Color.pink);
				
				if (row >2 && row <= 5 &&  col > 2 && col  <= 5)
					m.setBackground (Color.pink);
				
				if (row > 5 && ((col <= 2) || (col > 5)))
					m.setBackground (Color.pink);
				
				
				m.setValue(0);
				m.addActionListener(this);
				m.setFont(font);
				//m.setForeground(Color.white);
				m.setFocusable(false);
				
				add(m);
				board[row][col] = m;
			}
		}
	}
}