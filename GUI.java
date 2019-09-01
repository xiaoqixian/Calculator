package Calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel inputPanel;
	JTextArea displayArea;
	JTextField resultField;
	GridBagLayout gbl;
	
	public GUI() {
		inputPanel = new JPanel();
		displayArea = new JTextArea();
		resultField = new JTextField();
		gbl = new GridBagLayout();
		init();
		this.setLayout(gbl);
		this.add(displayArea);
		this.add(resultField);
		this.add(inputPanel);
		this.setTitle("My Calculator");
		this.setSize(400,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void init() {
		resultField.setEditable(false);
		displayArea.setEditable(true);
		displayArea.setFont(new Font("Arial",Font.BOLD,30));
		displayArea.setBackground(Color.PINK);
		resultField.setBackground(Color.ORANGE);
		resultField.setFont(new Font("Arial",Font.BOLD,30));
		
		GridBagConstraints s= new GridBagConstraints();//定义一个GridBagConstraints，GridBagLayout进行布局的关键
		s.fill = GridBagConstraints.BOTH;
		s.gridwidth = 0;
		s.gridheight = 3;
		s.weightx = 1;
		s.weighty = 0.3;
		gbl.setConstraints(displayArea, s);
		
		s.gridwidth = 0;
		s.gridheight = 4;
		s.weightx = 1;
		s.weighty = 0.2;
		gbl.setConstraints(resultField, s);
		
		s.gridwidth = 0;
		s.gridheight = 0;
		s.weightx = 1;
		s.weighty = 0.5;
		gbl.setConstraints(inputPanel, s);
		//gridLayout
        GridLayout gridLayout = new GridLayout(4,4,3,3);
        inputPanel.setLayout(gridLayout);
        
        //add buttons
        String [] buttonNames = new String []{"7", "8","9","/","4","5","6","*","1","2","3","-","0","^","+"};
        for (String string : buttonNames) {
            inputPanel.add(new JButton(string));
        }
        JButton equals = new JButton("=");
        equals.addActionListener(new myActionListener());
        inputPanel.add(equals);
        
	}
	
	private class myActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("=")) {
				Calculate cal = new Calculate();
				resultField.setText(String.valueOf(cal.calculate(displayArea.getText())));
				displayArea.append("=");
			}
		}
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
