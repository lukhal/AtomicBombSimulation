package poJavaProjekt;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	boolean simulationStart=false;
	boolean simulationReset=false;
	
	static File fout;
	static FileOutputStream fos;
 
	static BufferedWriter bw;
	//ToolPanel toolPanel;
	
	
	void setSimulationStart(boolean b)
	{
		simulationStart=b;
	}
	
	void setSimulationReset(boolean b)
	{
		simulationReset=b;
	}
	
	boolean getSimulationStart()
	{
		return simulationStart;
	}
	
	boolean getSimulationReset()
	{
		return simulationReset;
	}
	
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
	
	static void printHeader(BufferedWriter bw, ToolPanel toolPanel)
	{

		try {
			bw.write("Symulacja 2000");
			bw.newLine();
			bw.write("Prawdopodobienstwo naturalnego rozpadu "+toolPanel.getJtf1());
			bw.newLine();
			bw.write("Prawdopodobienstwo odbicia "+toolPanel.getJtf2());
			bw.newLine();
			bw.write("Predkosc neutronu [m/s] "+toolPanel.getJtf3());
			bw.newLine();
			bw.write("dt - krok symulacji [s] "+toolPanel.getJtf4());
			bw.newLine();
			bw.write("Promien jadra atomu [m] "+toolPanel.getJtf5());
			bw.newLine();
			bw.write("Energia pojedynczego rozpadu [J] "+toolPanel.getJtf6());
			bw.newLine();
			bw.write("Ilosc atomow we wspolrzednej x "+toolPanel.getJtf7());
			bw.newLine();
			bw.write("Ilosc atomow we wspolrzednej y "+toolPanel.getJtf8());
			bw.newLine();
			bw.write("Ilosc atomow we wspolrzednej z "+toolPanel.getJtf9());
			bw.newLine();
			bw.write("Odleglosc pomiedzy atomami [m] "+toolPanel.getJtf10());
			bw.newLine();
			bw.newLine();
			bw.write("Czas [s] "+ "Energia [J]" + "Moc [W]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Project(ToolPanel TempToolPanel) throws HeadlessException {
		super("Symulacja 2000");
		
	//setResizable(false);
	//setMinimumSize(new Dimension(1100, 900));
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
			gbc.gridwidth=2;
			gbc.gridheight=1;
			gbc.weightx=1;
			gbc.weighty=0;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.anchor=GridBagConstraints.CENTER;
			//gbc.insets= new Insets(1,1,1,1);
			
			add(menuBar, gbc);
			
	
chartPanel = new ChartPanel(lineGraph);
//chartPanel.setSize(200, 200);
//gbc.anchor=GridBagConstraints.FIRST_LINE_START;
gbc.fill=GridBagConstraints.BOTH;

gbc.gridx=0;
gbc.gridy=1;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1;
gbc.weighty=1;
//gbc.fill=GridBagConstraints.BOTH;

add(chartPanel, gbc);

chartPanel2 = new ChartPanel(lineGraph2);
gbc.gridx=0;
gbc.gridy=2;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1;
gbc.weighty=1;
//gbc.gridwidth=3;
//gbc.fill=GridBagConstraints.BOTH;

add(chartPanel2, gbc);


gbc.fill=GridBagConstraints.HORIZONTAL;

gbc.gridx=1;
gbc.gridy=1;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=0;
gbc.weighty=0;

//TempToolPanel= new ToolPanel();




add(TempToolPanel, gbc);



}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ToolPanel toolPanel = new ToolPanel();
		Project project= new Project(toolPanel);
		project.setSize(1300,950);
		Dimension dimension=new Dimension(1300,950);
		project.setMinimumSize(dimension);
		project.setVisible(true);

		Simulation s;
		s=new Simulation(
				toolPanel.getJtf1(),//double v_probabilityOfNaturalFission
				toolPanel.getJtf2(),//double v_probabilityOfReflection
				toolPanel.getJtf3(),//double v_velocityOfNeutron
				toolPanel.getJtf4(), //double v_dt
				toolPanel.getJtf5(), //double v_maxDistanceFromAtomThatCausesFission
				toolPanel.getJtf6(), //double v_energyOfFission
				0, //double v_energyAfterIteration
				0, //double v_totalEnergy,
				0, //double v_instantaneousPower
				toolPanel.getJtf7(), //int 
				toolPanel.getJtf8(), //int
				toolPanel.getJtf9(), //int
				toolPanel.getJtf10()); //double
		
				s.letThereBeAtoms();
				
				fout = new File("log.txt");
				fos = new FileOutputStream(fout);
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				
				
				printHeader(bw,toolPanel);
		
		
				 
		while(true)
		{
		
		
		if(project.getSimulationReset())
		{
			s=new Simulation(
					toolPanel.getJtf1(),//double v_probabilityOfNaturalFission
					toolPanel.getJtf2(),//double v_probabilityOfReflection
					toolPanel.getJtf3(),//double v_velocityOfNeutron
					toolPanel.getJtf4(), //double v_dt
					toolPanel.getJtf5(), //double v_maxDistanceFromAtomThatCausesFission
					toolPanel.getJtf6(), //double v_energyOfFission
					0, //double v_energyAfterIteration
					0, //double v_totalEnergy,
					0, //double v_instantaneousPower
					toolPanel.getJtf7(), //int 
					toolPanel.getJtf8(), //int
					toolPanel.getJtf9(), //int
					toolPanel.getJtf10()); //double
				s.letThereBeAtoms();

				fout = new File("log.txt");
				fos = new FileOutputStream(fout);
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				
				//toolPanel.getJtf1();
				
					
				printHeader(bw,toolPanel);

					
				project.setSimulationReset(false);
		}	
		
		
		
		while(project.getSimulationStart())
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
