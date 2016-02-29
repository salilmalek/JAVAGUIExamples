
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class JTableDemo extends JApplet {
	
	  private String[] columnNames =
	    {"Country", "Capital", "Population in Millions", "Democracy"};

	  private Object[][] rowData = {
	    {"USA", "Washington DC", 280, true},
	    {"Canada", "Ottawa", 32, true},
	    {"United Kingdom", "London", 60, true},
	    {"Germany", "Berlin", 83, true},
	    {"France", "Paris", 60, true},
	    {"Norway", "Oslo", 4.5, true},
	    {"India", "New Delhi", 1046, true}
	  };

	  private JTable jTable1 = new JTable(rowData, columnNames);

	  private JSpinner jspiRowHeight =
	    new JSpinner(new SpinnerNumberModel(16, 1, 50, 1));
	  
	  private JCheckBox jchkShowGrid = new JCheckBox("showGrid", true);
	  
	  private TableRowSorter<TableModel> sorter =
	      new TableRowSorter<TableModel>(jTable1.getModel());

	public JTableDemo() {
		jTable1.setRowSorter(sorter);
		JPanel panel1 = new JPanel();
	    panel1.add(new JLabel("rowHeight"));
	    panel1.add(jspiRowHeight);
	    panel1.add(jchkShowGrid);
	    add(panel1, BorderLayout.SOUTH);
	    add(new JScrollPane(jTable1));
	    jspiRowHeight.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent e) {
	        jTable1.setRowHeight(
	          ((Integer)(jspiRowHeight.getValue())).intValue());
	      }
	    });
	    jchkShowGrid.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        jTable1.setShowGrid(jchkShowGrid.isSelected());
	      }
	    });
	}
	 public static void main(String[] args) {
		    JTableDemo applet = new JTableDemo();
		    JFrame frame = new JFrame();
		    frame.setDefaultCloseOperation(3);
		    frame.setTitle("Table sort and control");
		    frame.getContentPane().add(applet, BorderLayout.CENTER);
		    applet.init();
		    applet.start();
		    frame.setSize(500,300);
		    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		    frame.setLocation((d.width - frame.getSize().width) / 2,(d.height - frame.getSize().height) / 2);
		    frame.setVisible(true);
		  }  
}
