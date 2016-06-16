package poJavaProjekt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class ToolPanel extends JPanel {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10;

	/*
	//Double.parseDouble(jtf1.getText()),//double v_probabilityOfNaturalFission
	//Double.parseDouble(jtf2.getText()),//double v_probabilityOfReflection
	//Double.parseDouble(jtf3.getText()),//double v_velocityOfNeutron
	//Double.parseDouble(jtf4.getText()), //double v_dt
	//Double.parseDouble(jtf5.getText()), //double v_maxDistanceFromAtomThatCausesFission
	//Double.parseDouble(jtf6.getText()), //double v_energyOfFission
	0, //double v_energyAfterIteration
	0, //double v_totalEnergy,
	0, //double v_instantaneousPower
	Integer.parseInt(jtf7.getText()), //int 
	Integer.parseInt(jtf8.getText()), //int
	Integer.parseInt(jtf9.getText()), //int
	Double.parseDouble(jtf10.getText())); //double
	*/
	
	double getJtf1()
	{
		return Double.parseDouble(jtf1.getText());
	}
	
	double getJtf2()
	{
		return Double.parseDouble(jtf2.getText());
	}
	double getJtf3()
	
	{
		return Double.parseDouble(jtf3.getText());
	}
	
	double getJtf4()
	{
		return Double.parseDouble(jtf4.getText());
	}
	
	double getJtf5()
	{
		return Double.parseDouble(jtf5.getText());
	}
	
	double getJtf6()
	{
		return Double.parseDouble(jtf6.getText());
	}
	
	int getJtf7()
	{
		return Integer.parseInt(jtf7.getText());
	}
	
	int getJtf8()
	{
		return Integer.parseInt(jtf8.getText());
	}
	
	int getJtf9()
	{
		return Integer.parseInt(jtf9.getText());
	}
	
	double getJtf10()
	{
		return Double.parseDouble(jtf10.getText());
	}
	
	ToolPanel()
	{
	
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx=1;
		gbc.weighty=1;
		
				
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
		"Ustawienia symulacji"));
		
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		//gbc.insets
				
		gbc.ipadx=20;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		
		
		JLabel jl1= new JLabel("Prawdopodobienstwo naturalnego rozpadu");
		gbc.gridy=0;
		add(jl1, gbc);

		jl1= new JLabel("Prawdopodobienstwo odbicia");
		gbc.gridy=1;
		add(jl1, gbc);

		jl1= new JLabel("Predkosc neutronu [m/s]");
		gbc.gridy=2;
		add(jl1, gbc);

		jl1= new JLabel("dt - krok symulacji [s]");
		gbc.gridy=3;
		add(jl1, gbc);

		jl1= new JLabel("Promien jadra atomu [m]");
		gbc.gridy=4;
		add(jl1, gbc);

		jl1= new JLabel("Energia pojedynczego rozpadu [J]");
		gbc.gridy=5;
		add(jl1, gbc);

		jl1= new JLabel("Ilosc atomow we wspolrzednej x");
		gbc.gridy=6;
		add(jl1, gbc);

		jl1= new JLabel("Ilosc atomow we wspolrzednej y");
		gbc.gridy=7;
		add(jl1, gbc);

		jl1= new JLabel("Ilosc atomow we wspolrzednej z");
		gbc.gridy=8;
		add(jl1, gbc);

		jl1= new JLabel("Odleglosc pomiedzy atomami [m]");
		gbc.gridy=9;
		add(jl1, gbc);


		gbc.gridx=1;
		gbc.ipadx=0;

		jtf1=new JTextField("0.000001",12);
		gbc.gridy=0;

		add(jtf1, gbc);

		jtf2=new JTextField("0.5",12);
		gbc.gridy=1;
		add(jtf2, gbc);

		jtf3=new JTextField("1",12);
		gbc.gridy=2;
		add(jtf3, gbc);

		jtf4=new JTextField("0.03",12);
		gbc.gridy=3;
		add(jtf4, gbc);

		jtf5=new JTextField("0.1",12);
		gbc.gridy=4;
		add(jtf5, gbc);

		jtf6=new JTextField("1",12);
		gbc.gridy=5;
		add(jtf6, gbc);

		jtf7=new JTextField("100",12);
		gbc.gridy=6;
		add(jtf7, gbc);

		jtf8=new JTextField("100",12);
		gbc.gridy=7;
		add(jtf8, gbc);

		jtf9=new JTextField("100",12);
		gbc.gridy=8;
		add(jtf9, gbc);

		jtf10=new JTextField("1",12);
		gbc.gridy=9;
		add(jtf10, gbc);
		
		
		
		
		
		
	}
}
