package application;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

//implements ActionListener and ItemListener interfaces
public class menu implements ActionListener, ItemListener {
    JTextArea output;
    JScrollPane scrollPane;
    sudoku s;
    
    public menu (sudoku s)
    {
    	this.s = s;
    }

    public JMenuBar createMenuBar() {
    	
        JMenuBar menuBar;
        JMenu menu;
        JMenu submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the file menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);
        
        //New Game Sub Menu
        submenu = new JMenu("New Game");
        submenu.setMnemonic(KeyEvent.VK_N);

        menuItem = new JMenuItem("Easy");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,  ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem("Medium");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        
        menuItem = new JMenuItem("Hard");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);  
        
        menu.add(submenu);   
        
         
        //Build the Help menu.
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuItem.addActionListener(this);
        menuBar.add(menu);
        
        //Help 
        menuItem = new JMenuItem("Contents",KeyEvent.VK_F1);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        //About Option
        menuItem = new JMenuItem("About",KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        return menuBar;
    }

	public void actionPerformed(ActionEvent e) 
	{
		JMenuItem source = (JMenuItem)(e.getSource());
		String act = source.getActionCommand();
		
		if(act.equalsIgnoreCase("easy"))
			s.newGame(1);
		 
		else if(act.equalsIgnoreCase("medium"))
			s.newGame(2);
		
		else if(act.equalsIgnoreCase("hard"))
			s.newGame(3);
		
		else if(act.equalsIgnoreCase("contents"))
			s.help();

		else if(act.equalsIgnoreCase("about"))
			s.about();
		
		else {}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
