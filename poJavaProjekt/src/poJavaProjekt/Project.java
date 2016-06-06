package poJavaProjekt;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
	static ChartPanel chartPanel;
	
	static XYSeries series;
	XYSeriesCollection xySeriesCollection;
	static JFreeChart lineGraph;
	static ChartPanel chartPanel2;
	
	static XYSeries series2;
	XYSeriesCollection xySeriesCollection2;
	JFreeChart lineGraph2;
	
	File from;
	static JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf10, jtf11;
	
	static double time=0;
	static boolean simulationStart=false;
	static boolean simulationReset=false;
	
	static File fout;
	static FileOutputStream fos;
 
	static BufferedWriter bw;
 
	
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
	
	ActionListener saveEnergyAsPngListener=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser();
			 chooser.setDialogTitle("Wybierz miejsce gdzie zapisać plik.");
		        int result = chooser.showSaveDialog(null);
		        if (result != JFileChooser.APPROVE_OPTION){
		            return;
		        }
		        @SuppressWarnings("unused")
				File to = chooser.getSelectedFile();
		        
		    OutputStream output = null;
			try {
				output = new FileOutputStream(chooser.getSelectedFile());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			writeEnergyAsPNG(lineGraph, output, 1280, 960);
	}};
		
	ActionListener savePowerAsPngListener=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser();
			 chooser.setDialogTitle("Wybierz miejsce gdzie zapisać plik.");
		        int result = chooser.showSaveDialog(null);
		        if (result != JFileChooser.APPROVE_OPTION){
		            return;
		        }
		        @SuppressWarnings("unused")
				File to = chooser.getSelectedFile();
		        
		    OutputStream output = null;
			try {
				output = new FileOutputStream(chooser.getSelectedFile());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			writePowerAsPNG(lineGraph2, output, 1280, 960);
	}};
	
	ActionListener saveAsTextListener=new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try {
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JFileChooser chooser = new JFileChooser();
	        chooser.setDialogTitle("Wybierz miejsce gdzie zapisać plik.");
	        
	        File from = new File("log.txt");
	        int result = chooser.showSaveDialog(null);
	        if (result != JFileChooser.APPROVE_OPTION){
	            return;
	        }
	        File to = chooser.getSelectedFile();
	        try {
				copy(from, to);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	        fout = new File("log.txt");
			try {
				fos = new FileOutputStream(fout);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			
	}};
	
	
	    
	   public static void copy(File from, File to) throws IOException {
	        // Uwaga! nie ma obsługi błędów
	        //Tworzymy buforowany strumień do odczytu
	        BufferedInputStream bufferedInputStream
	                = new BufferedInputStream(new FileInputStream(from));
	        //Tworzymy buforowany strumień do zapisu
	        BufferedOutputStream bufferedOutputStream
	                = new BufferedOutputStream(new FileOutputStream(to));
	        int read = bufferedInputStream.read();
	        while (read != -1){ // read() zwróci -1 jeśli plik się skończył
	            bufferedOutputStream.write(read);
	            read = bufferedInputStream.read();
	        }
	        bufferedOutputStream.close();
	        bufferedInputStream.close();
	    }
	
	
	public static void writeEnergyAsPNG( JFreeChart chart, OutputStream out, int width, int height )
		{
		try
			{
			BufferedImage chartImage = chart.createBufferedImage( width, height, null);
			ImageIO.write( chartImage, "png", out );
			}
			catch (Exception e)
			{
				System.out.println("error xD");
			}
	} 
	
	public static void writePowerAsPNG( JFreeChart chart, OutputStream out, int width, int height )
	{
	try
		{
		BufferedImage chartImage = chart.createBufferedImage( width, height, null);
		ImageIO.write( chartImage, "png", out );
		}
		catch (Exception e)
		{
			System.out.println("error xD");
		}
	} 
	

	
	public Project() throws HeadlessException {
		super("Symulacja 2000");
		
	setSize(1200,900);
	//setResizable(false);
	setMinimumSize(new Dimension(1100, 900));
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();

	
	
	series= new XYSeries("seria 1");
	xySeriesCollection = new XYSeriesCollection(series);
	lineGraph = ChartFactory.createXYLineChart
     ("Energia",  // Title
		       "czas [s]",           // X-Axis label
		       "E [J]",           // Y-Axis label
       xySeriesCollection,          // Dataset
       PlotOrientation.VERTICAL,        //Plot orientation
       false,                //show legend
       true,                // Show tooltips
       false               //url show
);
	
	series2= new XYSeries("seria 1");
	xySeriesCollection2 = new XYSeriesCollection(series2);
	lineGraph2 = ChartFactory.createXYLineChart
     ("Moc",  // Title
		       "czas [s]",           // X-Axis label
		       "P [W]",           // Y-Axis label
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
			menuItem.addActionListener(saveAsTextListener);
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Wykres energii do pliku .png");
			menuItem.addActionListener(saveEnergyAsPngListener);
			menu.add(menuItem);
			
			menuItem = new JMenuItem("Wykres mocy do pliku .png");
			menuItem.addActionListener(savePowerAsPngListener);
			menu.add(menuItem);
			
			
			/*
			menu = new JMenu("Wybor jezyka");
			menuBar.add(menu);
			JRadioButtonMenuItem jRadioButtonMenuItem = new  JRadioButtonMenuItem("Angielski", false);
			//menuItem.addActionListener(startListener);
			menu.add(jRadioButtonMenuItem);
			
			jRadioButtonMenuItem = new JRadioButtonMenuItem("Polski", true);
			//menuItem.addActionListener(startListener);
			menu.add(jRadioButtonMenuItem);
			*/
			
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.gridwidth=3;
			gbc.gridheight=1;
			gbc.weightx=1;
			gbc.weighty=1;
			gbc.fill=GridBagConstraints.BOTH;
			gbc.insets= new Insets(1,1,1,1);
			
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

jl1= new JLabel("Predkosc neutronu [m/s]");
gbc.gridy=3;
add(jl1, gbc);

jl1= new JLabel("dt - krok symulacji [s]");
gbc.gridy=4;
add(jl1, gbc);

jl1= new JLabel("Promien jadra atomu [m]");
gbc.gridy=5;
add(jl1, gbc);

jl1= new JLabel("Energia pojedynczego rozpadu [J]");
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

jl1= new JLabel("Odleglosc pomiedzy atomami [m]");
gbc.gridy=10;
add(jl1, gbc);


gbc.gridx=2;
gbc.gridy=1;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1;
gbc.weighty=1;



jtf1=new JTextField("0.000001");
add(jtf1, gbc);

jtf2=new JTextField("0.5");
gbc.gridy=2;
add(jtf2, gbc);

jtf3=new JTextField("1");
gbc.gridy=3;
add(jtf3, gbc);

jtf4=new JTextField("0.03");
gbc.gridy=4;
add(jtf4, gbc);

jtf5=new JTextField("0.1");
gbc.gridy=5;
add(jtf5, gbc);

jtf6=new JTextField("1");
gbc.gridy=6;
add(jtf6, gbc);

jtf7=new JTextField("100");
gbc.gridy=7;
add(jtf7, gbc);

jtf8=new JTextField("100");
gbc.gridy=8;
add(jtf8, gbc);

jtf9=new JTextField("100");
gbc.gridy=9;
add(jtf9, gbc);

jtf10=new JTextField("1");
gbc.gridy=10;
add(jtf10, gbc);


JPanel jp2;
for(int i=0;i<30;i++)
{
	jp2=new JPanel();
	gbc.gridx=1;
	gbc.gridy=11+i;
	gbc.gridwidth=2;
	gbc.gridheight=1;
	gbc.weightx=1;
	gbc.weighty=1;

add(jp2, gbc);
}


setVisible(true);

}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unused")
		Project project= new Project();

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
				
				fout = new File("log.txt");
				fos = new FileOutputStream(fout);
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				
				
				
					bw.write("Symulacja 2000");
					bw.newLine();
					bw.write("Prawdopodobienstwo naturalnego rozpadu "+jtf1.getText());
					bw.newLine();
					bw.write("Prawdopodobienstwo odbicia "+jtf2.getText());
					bw.newLine();
					bw.write("Predkosc neutronu [m/s] "+jtf3.getText());
					bw.newLine();
					bw.write("dt - krok symulacji [s] "+jtf4.getText());
					bw.newLine();
					bw.write("Promien jadra atomu [m] "+jtf5.getText());
					bw.newLine();
					bw.write("Energia pojedynczego rozpadu [J] "+jtf6.getText());
					bw.newLine();
					bw.write("Ilosc atomow we wspolrzednej x "+jtf7.getText());
					bw.newLine();
					bw.write("Ilosc atomow we wspolrzednej y "+jtf8.getText());
					bw.newLine();
					bw.write("Ilosc atomow we wspolrzednej z "+jtf9.getText());
					bw.newLine();
					bw.write("Odleglosc pomiedzy atomami [m] "+jtf10.getText());
					bw.newLine();
					bw.newLine();
					bw.write("Czas [s] "+ "Energia [J]" + "Moc [W]");
			 
				 
				 
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

				fout = new File("log.txt");
				fos = new FileOutputStream(fout);
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				
				
				
					bw.write("Symulacja 2000");
					bw.newLine();
					bw.write("Prawdopodobienstwo naturalnego rozpadu "+jtf1.getText());
					bw.newLine();
					bw.write("Prawdopodobienstwo odbicia "+jtf2.getText());
					bw.newLine();
					bw.write("Predkosc neutronu [m/s] "+jtf3.getText());
					bw.newLine();
					bw.write("dt - krok symulacji [s] "+jtf4.getText());
					bw.newLine();
					bw.write("Promien jadra atomu [m] "+jtf5.getText());
					bw.newLine();
					bw.write("Energia pojedynczego rozpadu [J] "+jtf6.getText());
					bw.newLine();
					bw.write("Ilosc atomow we wspolrzednej x "+jtf7.getText());
					bw.newLine();
					bw.write("Ilosc atomow we wspolrzednej y "+jtf8.getText());
					bw.newLine();
					bw.write("Ilosc atomow we wspolrzednej z "+jtf9.getText());
					bw.newLine();
					bw.write("Odleglosc pomiedzy atomami [m] "+jtf10.getText());
					bw.newLine();
					bw.newLine();
					bw.write("Czas [s] "+ "Energia [J]" + " "+"Moc [W]");
					bw.newLine();

					
				simulationReset=false;
		}	
		
		
		
		while(simulationStart)
		{
			
			s.energyAfterIterationEqualsZero();
			s.NaturalFission();
			s.NeutronsPlusTime();
			s.CheckIfHit();
			time+=s.getTime();
			series.add(time,s.getTotalEnergy());
			series2.add(time,s.getInstantaneousPower());
			bw.write(String.valueOf(time)+" "+ String.valueOf(s.getTotalEnergy()) +" "+ String.valueOf(s.getInstantaneousPower()));
			bw.newLine();
			
			
		}
		
	}
		
	}

}
