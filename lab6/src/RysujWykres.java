import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class RysujWykres extends JFrame{

	
	ChartPanel chartPanel;
	XYSeries series;
	XYSeriesCollection xySeriesCollection;
	JFreeChart lineGraph;
	File from;
	//File to;
	JTextArea pole;
	static boolean live;
	
	void onZyje()
	{
		xySeriesCollection.getSeries(0).clear();

		int i=0;
		while(live)
		{
		series.add(i,i*i);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i++;
		}
	}
	
	
	ActionListener closeListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			//Wykres w1=new Wykres();
			
			
			//if(chartPanel!=null){remove(chartPanel);};
			if(chartPanel!=null){remove(chartPanel);};

			series= new XYSeries("seria 1");
			
			for(double i=-6, j=0; i <= 6; i+=0.01)
			{
				j=Math.exp(i);
				series.add(i,j);
			}

			xySeriesCollection = new XYSeriesCollection(series);
	
	lineGraph = ChartFactory.createXYLineChart
		     ("f(x)=exp(x)",  // Title
				       "x",           // X-Axis label
				       "f(x)",           // Y-Axis label
		       xySeriesCollection,          // Dataset
		       PlotOrientation.VERTICAL,        //Plot orientation
		       false,                //show legend
		       true,                // Show tooltips
		       false               //url show
		);
	
	
			
	chartPanel = new ChartPanel(lineGraph);
	add(chartPanel);
	
	//RysujWykres.this.repaint();
	chartPanel.repaint();
	setVisible(true);
	
		//	add(w1);
		//	setvisible(true);
			
			
			
	/*
	try {
		TimeUnit.SECONDS.sleep(1);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
			*/
		
		}	
	};
	
	ActionListener closeListener2 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if(chartPanel!=null){remove(chartPanel);};

			series= new XYSeries("seria 1");
			
			for(double i=-2*Math.PI, j=0; i <= 2*Math.PI; i+=0.01)
			{
				j=Math.sin(i);
				series.add(i,j);
			}

			xySeriesCollection = new XYSeriesCollection(series);
	
	lineGraph = ChartFactory.createXYLineChart
		     ("f(x)=sin(x)",  // Title
				       "x",           // X-Axis label
				       "f(x)",           // Y-Axis label
		       xySeriesCollection,          // Dataset
		       PlotOrientation.VERTICAL,        //Plot orientation
		       false,                //show legend
		       true,                // Show tooltips
		       false               //url show
		);
	
	
			
	chartPanel = new ChartPanel(lineGraph);
	add(chartPanel);
	
	//RysujWykres.this.repaint();
	chartPanel.repaint();
	setVisible(true);
			
		}
		
	
	};
	
	
	ActionListener closeListener3 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(chartPanel!=null){remove(chartPanel);};

			series= new XYSeries("seria 1");
			for(double i=-2*Math.PI, j=0; i <= 2*Math.PI; i+=0.01)
			{
				j=Math.cos(i);
				series.add(i,j);
			}

			xySeriesCollection = new XYSeriesCollection(series);
	
	lineGraph = ChartFactory.createXYLineChart
		     ("f(x)=cos(x)",  // Title
		       "x",           // X-Axis label
		       "f(x)",           // Y-Axis label
		       xySeriesCollection,          // Dataset
		       PlotOrientation.VERTICAL,        //Plot orientation
		       false,                //show legend
		       true,                // Show tooltips
		       false               //url show
		);
	
	
			
	chartPanel = new ChartPanel(lineGraph);
	add(chartPanel);
	
	//RysujWykres.this.repaint();
	chartPanel.repaint();
	setVisible(true);
		}};
	ActionListener closeListener4 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(chartPanel!=null){remove(chartPanel);};

			series= new XYSeries("seria 1");
			for(double i=-4, j=0; i <= 4; i+=0.01)
			{
				j=Math.exp(-i*i);
				series.add(i,j);
			}

			xySeriesCollection = new XYSeriesCollection(series);
	
	lineGraph = ChartFactory.createXYLineChart
		     ("f(x)=exp(-x^2)",  // Title
				       "x",           // X-Axis label
				       "f(x)",           // Y-Axis label
		       xySeriesCollection,          // Dataset
		       PlotOrientation.VERTICAL,        //Plot orientation
		       false,                //show legend
		       true,                // Show tooltips
		       false               //url show
		);
	
	
			
	chartPanel = new ChartPanel(lineGraph);
	add(chartPanel);
	
	//RysujWykres.this.repaint();
	chartPanel.repaint();
	setVisible(true);
		}};
	ActionListener closeListener5 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(chartPanel!=null){remove(chartPanel);};

			series= new XYSeries("seria 1");
			
			for(double i=0.1, j=0; i <= 4; i+=0.01)
			{
				j=Math.log(i);
				series.add(i,j);
			}

			xySeriesCollection = new XYSeriesCollection(series);
	
	lineGraph = ChartFactory.createXYLineChart
		     ("f(x)=ln(x)",  // Title
				       "x",           // X-Axis label
				       "f(x)",           // Y-Axis label
		       xySeriesCollection,          // Dataset
		       PlotOrientation.VERTICAL,        //Plot orientation
		       false,                //show legend
		       true,                // Show tooltips
		       false               //url show
		);
	
	
			
	chartPanel = new ChartPanel(lineGraph);
	add(chartPanel);
	
	//RysujWykres.this.repaint();
	chartPanel.repaint();
	setVisible(true);
		}};
		
		
		ActionListener closeListener6 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e6) {
			
				
				if(chartPanel!=null){remove(chartPanel);};

				series= new XYSeries("seria 1");
				
				
				
				 JFileChooser chooser = new JFileChooser();
			        chooser.setDialogTitle("Wybierz miejsce skad wczytac plik.");
			        int result = chooser.showOpenDialog(null);
			        if (result != JFileChooser.APPROVE_OPTION){
			            return;
			        }
			        File from = chooser.getSelectedFile();
				
			    
			        
			        
			        try {
						@SuppressWarnings("resource")
						Scanner s = new Scanner(from);
						
						
						double iks=0;
						double igrek=0;
						
				        while (s.hasNextFloat()) {
				            iks = s.nextFloat();
				            igrek = s.nextFloat();
				            
				            System.out.println(iks);
					        System.out.println(igrek);
					        System.out.println("");
					        
					        series.add(iks,igrek);
				        }
				        
				        
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
			        xySeriesCollection = new XYSeriesCollection(series);
			    	
			    	lineGraph = ChartFactory.createXYLineChart
			    		     ("Dane z pliku",  // Title
			    				       "x",           // X-Axis label
			    				       "f(x)",           // Y-Axis label
			    		       xySeriesCollection,          // Dataset
			    		       PlotOrientation.VERTICAL,        //Plot orientation
			    		       false,                //show legend
			    		       true,                // Show tooltips
			    		       false               //url show
			    		);
			    	
			    	
			    			
			    	chartPanel = new ChartPanel(lineGraph);
			    	add(chartPanel);
			    	
			    	//RysujWykres.this.repaint();
			    	chartPanel.repaint();
			    	setVisible(true);
			    	
			    	
			    	
			    	
			}
		};
		
		
		ActionListener closeListener7 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub


				//chartPanel = new ChartPanel(lineGraph);
				if(chartPanel!=null){remove(chartPanel);};
				//live=true;
				
				series.clear();
				//XYSeries series= new XYSeries("seria 1");
				

				xySeriesCollection = new XYSeriesCollection(series);
		
		lineGraph = ChartFactory.createXYLineChart
			     ("f(x)=x^2",  // Title
					       "x",           // X-Axis label
					       "f(x)",           // Y-Axis label
			       xySeriesCollection,          // Dataset
			       PlotOrientation.VERTICAL,        //Plot orientation
			       false,                //show legend
			       true,                // Show tooltips
			       false               //url show
			    );
		
		//chartPanel.repaint();
		
		chartPanel = new ChartPanel(lineGraph);
		//chartPanel.repaint();

		add(chartPanel);
		live=true;
		chartPanel.repaint();

		//RysujWykres.this.repaint();
		//chartPanel.repaint();
		setVisible(true);
		
		
			      
 
			
		
		
				
		
			}};
			
			ActionListener closeListener8 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					live=false;
				}};
				
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -5321697678440103303L;


	public RysujWykres(){
		super("Rysuj wykres");
		//okienko
		setSize(640,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//MENU
		
				JMenuBar menuBar;
				JMenu menu;
				JMenuItem menuItem;

				menuBar = new JMenuBar();
				
				menu = new JMenu("Wczytaj");
				//menuItem.addActionListener(closeListener9);
				menuBar.add(menu);

				menuItem = new JMenuItem("Z pliku tekstowego");
				menuItem.addActionListener(closeListener6);
				menu.add(menuItem);
				
				
				menu = new JMenu("F. matematyczne");
				menuBar.add(menu);
						
				
				menuItem = new JMenuItem("Exp");
								menuItem.addActionListener(closeListener);
				menu.add(menuItem);
				
				menuItem = new JMenuItem("Exp(-x^2)");
				menuItem.addActionListener(closeListener4);
				menu.add(menuItem);	
			
				menuItem = new JMenuItem("Ln");
				menuItem.addActionListener(closeListener5);
				menu.add(menuItem);	
				
				menuItem = new JMenuItem("Sin");
								menuItem.addActionListener(closeListener2);
				menu.add(menuItem);	
				
				
				menuItem = new JMenuItem("Cos");
				menuItem.addActionListener(closeListener3);
				menu.add(menuItem);	
				
				menu = new JMenu("Live chart");
				menuBar.add(menu);
						
				menuItem = new JMenuItem("Start");
				menuItem.addActionListener(closeListener7);
				menu.add(menuItem);	
			
				menuItem = new JMenuItem("Stop");
				menuItem.addActionListener(closeListener8);
				menu.add(menuItem);	

				add(menuBar, BorderLayout.NORTH);
			
				
			
				
		//wykres		
				
		
				series= new XYSeries("f(x)");
				//series.add(0.1, 4.4);
				xySeriesCollection = new XYSeriesCollection(series);
		
		lineGraph = ChartFactory.createXYLineChart
			     ("f(x)",  // Title
			       "x",           // X-Axis label
			       "f(x)",           // Y-Axis label
			       xySeriesCollection,          // Dataset
			       PlotOrientation.VERTICAL,        //Plot orientation
			       false,                //show legend
			       true,                // Show tooltips
			       false               //url show
			);
		
		
		chartPanel = new ChartPanel(lineGraph);
		add(chartPanel);
		
		
		setVisible(true);

		
	};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			@SuppressWarnings("unused")
			RysujWykres rw= new RysujWykres();
			
			while(true)
			{
				if(rw.live)
				{
				rw.onZyje();
				}
			}
			
			
			
		
	
	}	       
}
	
