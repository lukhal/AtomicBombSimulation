package poJavaProjekt;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class Project extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6562322592755468781L;

	JTextArea pole;
	ChartPanel chartPanel;
	
	static XYSeries series;
	XYSeriesCollection xySeriesCollection;
	JFreeChart lineGraph;
	ChartPanel chartPanel2;
	
	static XYSeries series2;
	XYSeriesCollection xySeriesCollection2;
	JFreeChart lineGraph2;
	
	File from;
	static JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11;
	
	static double time=0;
	static boolean simulationStart=false;
	static boolean simulationReset=false;
	
	
	
	
	
	
	
	ActionListener startListener=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		simulationStart=true;
			
			
		}};
		
	ActionListener stopListener=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		simulationStart=false;
				
				
		}};	
	
	ActionListener resetListener=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
		simulationStart=false;
		simulationReset=true;
		time=0;
		xySeriesCollection.getSeries(0).clear();
		xySeriesCollection2.getSeries(0).clear();
		
		
		}};	
	
			
	
	
	public Project() throws HeadlessException {
		super("Simulation 2000");
		
	setSize(1200,900);
	//setResizable(false);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();

	
	
	series= new XYSeries("seria 1");
	xySeriesCollection = new XYSeriesCollection(series);
	lineGraph = ChartFactory.createXYLineChart
     ("Energy",  // Title
		       "x",           // X-Axis label
		       "f(x)",           // Y-Axis label
       xySeriesCollection,          // Dataset
       PlotOrientation.VERTICAL,        //Plot orientation
       false,                //show legend
       true,                // Show tooltips
       false               //url show
);
	
	series2= new XYSeries("seria 1");
	xySeriesCollection2 = new XYSeriesCollection(series2);
	lineGraph2 = ChartFactory.createXYLineChart
     ("Power",  // Title
		       "x",           // X-Axis label
		       "f(x)",           // Y-Axis label
       xySeriesCollection2,          // Dataset
       PlotOrientation.VERTICAL,        //Plot orientation
       false,                //show legend
       true,                // Show tooltips
       false               //url show
);

	//MENU
			JMenuBar menuBar;
			JMenu menu;
			JMenuItem menuItem;

			menuBar = new JMenuBar();

			menu = new JMenu("Symulacja");
			menuBar.add(menu);
					
			menuItem = new JMenuItem("Nowa symulacja");
			menuItem.addActionListener(resetListener);
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Start");
							menuItem.addActionListener(startListener);
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Pauza");
							menuItem.addActionListener(stopListener);
			menu.add(menuItem);

							
			
			
			menu = new JMenu("Eksport");
			menuBar.add(menu);
			
			menuItem = new JMenuItem("Do pliku tekstowego");
			//menuItem.addActionListener(startListener);
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Do pliku .png");
			//menuItem.addActionListener(startListener);
			menu.add(menuItem);
			
			menu = new JMenu("Wybor jezyka");
			menuBar.add(menu);
			JRadioButtonMenuItem jRadioButtonMenuItem = new  JRadioButtonMenuItem("Angielski", false);
			//menuItem.addActionListener(startListener);
			menu.add(jRadioButtonMenuItem);
			
			jRadioButtonMenuItem = new JRadioButtonMenuItem("Polski", true);
			//menuItem.addActionListener(startListener);
			menu.add(jRadioButtonMenuItem);
			
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.gridwidth=3;
			gbc.gridheight=1;
			gbc.weightx=1;
			gbc.weighty=1;
			gbc.fill=GridBagConstraints.BOTH;
			
			
			add(menuBar, gbc);
			
	
chartPanel = new ChartPanel(lineGraph);
//gbc.anchor=GridBagConstraints.FIRST_LINE_START;
gbc.gridx=0;
gbc.gridy=1;
gbc.gridwidth=1;
gbc.gridheight=20;
gbc.weightx=1;
gbc.weighty=1;
gbc.fill=GridBagConstraints.BOTH;

add(chartPanel, gbc);

chartPanel2 = new ChartPanel(lineGraph2);
gbc.gridx=0;
gbc.gridy=21;
gbc.gridwidth=1;
gbc.gridheight=20;
gbc.weightx=1;
gbc.weighty=1;
//gbc.gridwidth=3;
gbc.fill=GridBagConstraints.BOTH;

add(chartPanel2, gbc);
//
//labele
gbc.gridx=1;
gbc.gridy=1;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1;
gbc.weighty=1;
//gbc.fill=GridBagConstraints.BOTH;

JLabel jl1= new JLabel("Prawdopodobienstwo naturalnego rozpadu");
gbc.gridy=1;
add(jl1, gbc);

jl1= new JLabel("Prawdopodobienstwo odbicia");
gbc.gridy=2;
add(jl1, gbc);

jl1= new JLabel("Predkosc neutronu");
gbc.gridy=3;
add(jl1, gbc);

jl1= new JLabel("dt - krok symulacji");
gbc.gridy=4;
add(jl1, gbc);

jl1= new JLabel("Promien jadra atomu");
gbc.gridy=5;
add(jl1, gbc);

jl1= new JLabel("Energia pojedynczego rozpadu");
gbc.gridy=6;
add(jl1, gbc);

jl1= new JLabel("Ilosc atomow we wspolrzednej x");
gbc.gridy=7;
add(jl1, gbc);

jl1= new JLabel("Ilosc atomow we wspolrzednej y");
gbc.gridy=8;
add(jl1, gbc);

jl1= new JLabel("Ilosc atomow we wspolrzednej z");
gbc.gridy=9;
add(jl1, gbc);

jl1= new JLabel("Odleglosc pomiedzy atomami");
gbc.gridy=10;
add(jl1, gbc);

//jl1= new JLabel("Zatrzymaj symulacje gdy część atomów ulegnie rozpadowi");
//gbc.gridy=11;
//add(jl1, gbc);
//jl1= new JLabel("asd");


//add(jl1, gbc);



gbc.gridx=2;
gbc.gridy=1;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1;
gbc.weighty=1;



jtf1=new JTextField("0.0001");
add(jtf1, gbc);

jtf2=new JTextField("0.5");
gbc.gridy=2;
add(jtf2, gbc);

jtf3=new JTextField("1");
gbc.gridy=3;
add(jtf3, gbc);

jtf4=new JTextField("0.1");
gbc.gridy=4;
add(jtf4, gbc);

jtf5=new JTextField("0.05");
gbc.gridy=5;
add(jtf5, gbc);

jtf6=new JTextField("1");
gbc.gridy=6;
add(jtf6, gbc);

jtf7=new JTextField("10");
gbc.gridy=7;
add(jtf7, gbc);

jtf8=new JTextField("10");
gbc.gridy=8;
add(jtf8, gbc);

jtf9=new JTextField("10");
gbc.gridy=9;
add(jtf9, gbc);

jtf10=new JTextField("1");
gbc.gridy=10;
add(jtf10, gbc);

//jtf11=new JTextField("0.8");
//gbc.gridy=11;
//add(jtf11, gbc);


//JButton jb=new JButton("Kliknij");
//gbc.gridy=16;
//gbc.gridx=2;
//gbc.gridwidth=1;
//gbc.gridheight=1;
//add(jb, gbc);

JPanel jp2;
for(int i=0;i<30;i++)
{
jp2=new JPanel();
gbc.gridx=2;
gbc.gridy=11+i;
gbc.gridwidth=2;
gbc.gridheight=1;
gbc.weightx=1;
gbc.weighty=1;

add(jp2, gbc);
}





/*
JPanel jp=new JPanel();
gbc.gridx=1;
gbc.gridy=0;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1;
gbc.weighty=1;
//gbc.gridwidth=3;
gbc.fill=GridBagConstraints.BOTH;
add(jp, gbc);
*/
//RysujWykres.this.repaint();
//chartPanel.repaint();
setVisible(true);

}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Project project= new Project();
		//project.setVisible(true);
		Simulation s;
		s=new Simulation(
				Double.parseDouble(jtf1.getText()),//double v_probabilityOfNaturalFission
				Double.parseDouble(jtf2.getText()),//double v_probabilityOfReflection
				Double.parseDouble(jtf3.getText()),//double v_velocityOfNeutron
				Double.parseDouble(jtf4.getText()), //double v_dt
				Double.parseDouble(jtf5.getText()), //double v_maxDistanceFromAtomThatCausesFission
				Double.parseDouble(jtf6.getText()), //double v_energyOfFission
				0, //double v_energyAfterIteration
				0, //double v_totalEnergy,
				0, //double v_instantaneousPower
				Integer.parseInt(jtf7.getText()), //int 
				Integer.parseInt(jtf8.getText()), //int
				Integer.parseInt(jtf9.getText()), //int
				Double.parseDouble(jtf10.getText())); //double
		
				s.letThereBeAtoms();

		while(true)
		{
		
		
		if(simulationReset==true)
		{
		s=new Simulation(
				Double.parseDouble(jtf1.getText()),//double v_probabilityOfNaturalFission
				Double.parseDouble(jtf2.getText()),//double v_probabilityOfReflection
				Double.parseDouble(jtf3.getText()),//double v_velocityOfNeutron
				Double.parseDouble(jtf4.getText()), //double v_dt
				Double.parseDouble(jtf5.getText()), //double v_maxDistanceFromAtomThatCausesFission
				Double.parseDouble(jtf6.getText()), //double v_energyOfFission
				0, //double v_energyAfterIteration
				0, //double v_totalEnergy,
				0, //double v_instantaneousPower
				Integer.parseInt(jtf7.getText()), //int 
				Integer.parseInt(jtf8.getText()), //int
				Integer.parseInt(jtf9.getText()), //int
				Double.parseDouble(jtf10.getText())); //double
				s.letThereBeAtoms();

				simulationReset=false;
		}	
		
		
	
		
		//project.jtf1.paramString();
		//s.letThereBeAtoms();
		//System.out.print(s.atomContainer.size());
		
		while(simulationStart)
		{
			s.energyAfterIterationEqualsZero();
			s.NaturalFission();
			s.NeutronsPlusTime();
			s.CheckIfHit();
			time+=s.getTime();
			
			//System.out.print(s.getInstantaneousPower());
			series.add(time,s.getTotalEnergy());
			series2.add(time,s.getInstantaneousPower());

			//System.out.println(" " +s.getTotalEnergy());
			
			
		}
		
	}
		
		
	}

}
