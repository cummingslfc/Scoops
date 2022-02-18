import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.Arrays;


public class MainProgram 
{
	
	static JFrame theWindow;
	
	static JLabel passLabel,scoopLabel,priceLabel;
	static JPasswordField passWord;
	static JRadioButton [] optionsButtons;
	static String[] flavors = {"chocolate","strawberry","vanilla","cookie dough","chocolate chip"};
	static double [] prices = {1.25,1.25,1.25,1.75,1.50};
	static JComboBox<String> flavorBox;
	static JSlider scoopSlider;
	static JButton orderButton;
	static JTextArea report;
	
	static private class orderChanged implements ChangeListener, ActionListener
	{
		public void stateChanged(ChangeEvent e)
		{
			update();
		}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(orderButton))
			{
				String temp1 = "frozen";
				if(Arrays.equals(passWord.getPassword(), temp1.toCharArray()))
				recipt();
			}
			else
			{
				update();
			}
		}
		private void update()
		{
			double price = prices[flavorBox.getSelectedIndex()];
			price *= scoopSlider.getValue();
			if(optionsButtons[1].isSelected())
			{
				price *=1.1;
			}
			priceLabel.setText("$"+price);
		}
		private void recipt()
		{
			String str = "--- Your order is complete ----\n";
			if(optionsButtons[0].isSelected())
			{
				str+="Sugar cone\n";
			}
			else
			{
				str+="Waffle cone\n";
			}
			str += (flavors[flavorBox.getSelectedIndex()]+"\n");
			str += (scoopSlider.getValue()+" scoops\n");
			str += ("$"+prices[flavorBox.getSelectedIndex()]+" per scoop = $"+prices[flavorBox.getSelectedIndex()]*scoopSlider.getValue()+"\n");
			if(optionsButtons[1].isSelected())
			{
				str += ("Waffle cone extra fee = $"+prices[flavorBox.getSelectedIndex()]*scoopSlider.getValue()*0.1+"\n");
			}
			str+=("Total price: "+priceLabel.getText());
			
			report.setText(str);
		}
	}
	
	public static void main(String[] args) {
		orderChanged handler = new orderChanged();
		
		theWindow = new JFrame("Scoops");
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		//label
		passLabel = new JLabel("Enter password");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(passLabel,c);
		
		//pass word
		passWord = new JPasswordField(10);
		c.gridx = 1;
		c.gridy = 0;
		
		panel.add(passWord,c);
		
		//option buttons
		ButtonGroup answerGroup = new ButtonGroup();
		String [] options = {"sugar cone", "waffle cone"};
		optionsButtons = new JRadioButton[2];
		for (int i = 0; i < optionsButtons.length; i++)
		{
			optionsButtons[i] = new JRadioButton(options[i]);
			c.gridx = i;
			c.gridy = 1;
			panel.add(optionsButtons[i],c);
			answerGroup.add(optionsButtons[i]);
		}
		optionsButtons[0].setSelected(true);
		optionsButtons[0].addActionListener(handler);
		optionsButtons[1].addActionListener(handler);
		
		//flavor box
		flavorBox = new  JComboBox<String>(flavors);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		panel.add(flavorBox,c);
		flavorBox.addActionListener(handler);
		
		//label
		scoopLabel = new JLabel("Scoops!");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		panel.add(scoopLabel,c);
		//slider
		scoopSlider = new JSlider(1, 4, 1);
		scoopSlider.setMajorTickSpacing(1);
		scoopSlider.setPaintTicks(true);
		scoopSlider.setSnapToTicks(true);
		c.gridx = 1;
		c.gridy = 3;
		panel.add(scoopSlider,c);
		scoopSlider.addChangeListener(handler);
		
		//price
		double price = prices[flavorBox.getSelectedIndex()];
		price *= scoopSlider.getValue();
		if(optionsButtons[1].isSelected())
		{
			price *=1.1;
		}
		priceLabel = new JLabel("$"+price);
		c.gridx = 0;
		c.gridy = 4;
		panel.add(priceLabel,c);
		
		//order
		orderButton = new JButton("Order");
		c.gridx = 1;
		c.gridy = 4;
		panel.add(orderButton,c);
		orderButton.addActionListener(handler);
		
		//report
		report = new JTextArea(7,14);
		report.setEditable(false);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight = 4;
		panel.add(report,c);
		
		
		theWindow.add(panel);
		theWindow.setSize(300, 300);
		theWindow.setVisible(true);
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

  
    
    
}