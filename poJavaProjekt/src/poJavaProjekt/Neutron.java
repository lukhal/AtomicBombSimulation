package poJavaProjekt;

public class Neutron {
	public double x, y, z;
	double v_x,v_y,v_z;
	
	public Neutron(double a, double b, double c, double v_r) {
		x=a; y=b; z=c;
		
		double phi=Math.random()*2*Math.PI;
		double theta=Math.random()*Math.PI;

		v_x=v_r*Math.sin(theta)*Math.cos(phi);
		v_y=v_r*Math.sin(theta)*Math.sin(phi);
		v_z=v_r*Math.cos(theta);
	}
	
	void Move(double time)
	{
		x+=v_x*time;
		y+=v_y*time;
		z+=v_z*time;
	}
	
	double distanceFromAtom(Atom a)
	{
		return Math.sqrt((a.x-x)*(a.x-x)+(a.y-y)*(a.y-y)+(a.z-z)*(a.z-z));
		
	}
	
	boolean outOfBorders(double a, double b, double c)
	{
		boolean d=false;
		if(x>a||x<0||y>b||y<0||z>c||z<0)
		{
			d=true;
		}
		return d;
	}
	
	
}