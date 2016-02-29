
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class ProcessTree extends JApplet {

  private JButton jbtAdd = new JButton("Add Node");
  private JButton jbtRemove = new JButton("Remove Selected Node");

  private JTree jTree1;
  private JTextArea jtaMessage = new JTextArea();
  public ProcessTree() {

    DefaultMutableTreeNode root, europe, northAmerica, us;

    europe = new DefaultMutableTreeNode("Europe");
    europe.add(new DefaultMutableTreeNode("UK"));
    europe.add(new DefaultMutableTreeNode("Germany"));
    europe.add(new DefaultMutableTreeNode("France"));
    europe.add(new DefaultMutableTreeNode("Norway"));

    northAmerica = new DefaultMutableTreeNode("North America");
    us = new DefaultMutableTreeNode("US");
    us.add(new DefaultMutableTreeNode("California"));
    us.add(new DefaultMutableTreeNode("Texas"));
    us.add(new DefaultMutableTreeNode("New York"));
    us.add(new DefaultMutableTreeNode("Florida"));
    us.add(new DefaultMutableTreeNode("Illinois"));
    northAmerica.add(us);
    northAmerica.add(new DefaultMutableTreeNode("Canada"));

    root = new DefaultMutableTreeNode("World");
    root.add(europe);
    root.add(northAmerica);

    JPanel p1 = new JPanel();

    p1.add(jbtAdd);
    p1.add(jbtRemove);
    JSplitPane jSplitPane1 = new JSplitPane(
    	      JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.add(new JScrollPane(jTree1 = new JTree(root)),JSplitPane.LEFT);
    jSplitPane1.add(new JScrollPane(jtaMessage), JSplitPane.RIGHT);

    getContentPane().add(p1, BorderLayout.NORTH);
    getContentPane().add(jSplitPane1, BorderLayout.CENTER);

    jTree1.addTreeSelectionListener(new TreeSelectionListener() {
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                               jTree1.getLastSelectedPathComponent();
 
            if (node == null) return;
 
            Object nodeInfo = node.getUserObject();
            jtaMessage.append("Node " + nodeInfo.toString() +
                    " is Selected \n");
            
        }
    });


    jbtAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)
          jTree1.getLastSelectedPathComponent();

        if (parent == null) {
          JOptionPane.showMessageDialog(null,
            "No node in the first tree is selected");
          return;
        }

        String nodeName = JOptionPane.showInputDialog(
          null, "Enter a name for this new node", "Add a Child",
          JOptionPane.QUESTION_MESSAGE);

        parent.add(new DefaultMutableTreeNode(nodeName));
        jtaMessage.append("Node " + nodeName +
                " is Added \n");
        
        ((DefaultTreeModel)(jTree1.getModel())).reload();
      }
    });

    jbtRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        TreePath[] paths = jTree1.getSelectionPaths();

        if (paths == null) {
          JOptionPane.showMessageDialog(null,
            "No node in the left tree is selected");
          return;
        }

        for (int i = 0; i < paths.length; i++) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode)
              (paths[i].getLastPathComponent());

          if (node.isRoot()) {
            JOptionPane.showMessageDialog(null,
              "Cannot remove the root");
          }
          else
            node.removeFromParent();
          jtaMessage.append("Node " + node +
                  " is Remove \n");
        }
        ((DefaultTreeModel)(jTree1.getModel())).reload();
      }
    });
  }

  public static void main(String[] args) {
    ProcessTree applet = new ProcessTree();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ProcessTree");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(650, 420);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
                      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}

