package poJavaProjekt;

import java.util.Vector;

public class Simulation {
	Vector<Atom> atomContainer;
	Vector<Neutron> neutronContainer;

	double probabilityOfNaturalFission;
	double probabilityOfReflection;
	double velocityOfNeutron;
	double dt;
	double maxDistanceFromAtomThatCausesFission;
	double energyOfFission;
	double energyAfterIteration;
	double totalEnergy;
	double instantaneousPower;
	int maxNumberOfAtomsInX;
	int maxNumberOfAtomsInY;
	int maxNumberOfAtomsInZ;
	double lengthBetweenAtoms;
	

	
	public Simulation(double v_probabilityOfNaturalFission,double v_probabilityOfReflection, double v_velocityOfNeutron, double v_dt,
			double v_maxDistanceFromAtomThatCausesFission, double v_energyOfFission, double v_energyAfterIteration, double v_totalEnergy,
			double v_instantaneousPower, int v_maxNumberOfAtomsInX,int v_maxNumberOfAtomsInY,int v_maxNumberOfAtomsInZ, double v_lengthBetweenAtoms){
		
			atomContainer= new Vector<Atom>(10);
			neutronContainer= new Vector<Neutron>(10);
	
			probabilityOfNaturalFission=v_probabilityOfNaturalFission;
			probabilityOfReflection=v_probabilityOfReflection;
			velocityOfNeutron=v_velocityOfNeutron;
			dt=v_dt;
			maxDistanceFromAtomThatCausesFission=v_maxDistanceFromAtomThatCausesFission;
			energyOfFission=v_energyOfFission;
			energyAfterIteration=v_energyAfterIteration;
			totalEnergy=v_totalEnergy;
			instantaneousPower=v_instantaneousPower;
			lengthBetweenAtoms=v_lengthBetweenAtoms;
			
			//rozmiary siatki atomow
			maxNumberOfAtomsInX=v_maxNumberOfAtomsInX;
			maxNumberOfAtomsInY=v_maxNumberOfAtomsInY;
			maxNumberOfAtomsInZ=v_maxNumberOfAtomsInZ;
			};
		
	long getIndexOfClosestAtom(Neutron n)
	{
		long index=-1;
		long intX= Math.round((n.x)/(lengthBetweenAtoms));
		long intY= Math.round((n.y)/(lengthBetweenAtoms));
		long intZ= Math.round((n.z)/(lengthBetweenAtoms));
		
		
		index=intZ*(maxNumberOfAtomsInY)*(maxNumberOfAtomsInX)+intY*(maxNumberOfAtomsInX)+intX;

		if(intX>=maxNumberOfAtomsInX||intY>=maxNumberOfAtomsInY||intZ>=maxNumberOfAtomsInZ)
		{
			index=0;
		}
		
		return index;
		
	}
		
		
	//stworzenie atomów
	void letThereBeAtoms()
	{
		for(double ii=0; ii<maxNumberOfAtomsInZ; ii++)
		{
			for(double jj=0; jj<maxNumberOfAtomsInY; jj++)
			{
				for(double kk=0; kk<maxNumberOfAtomsInX; kk++)
				{
					atomContainer.add(new Atom(lengthBetweenAtoms*kk, lengthBetweenAtoms*jj, lengthBetweenAtoms*ii, true));
				}
			}
		}
	}
	

		
		
	void energyAfterIterationEqualsZero()
	{
		energyAfterIteration=0;
	}
	
	void NaturalFission()
	{
			//losowy rozpad
			for(int ii=0;ii<atomContainer.size();ii++)
			{
				
				if(atomContainer.elementAt(ii).exists==true)
				{
					if(Math.random()<probabilityOfNaturalFission)
					{
						atomContainer.elementAt(ii).exists=false;
						
						neutronContainer.add(new Neutron(atomContainer.elementAt(ii).x, atomContainer.elementAt(ii).y, atomContainer.elementAt(ii).z, velocityOfNeutron));
						energyAfterIteration+=energyOfFission;
		
					}
				}
			}
			
	}
	
	void NeutronsPlusTime()
	{
			//neutrony + czas
			for(int ii=0;ii<neutronContainer.size();ii++)
			{
				neutronContainer.get(ii).Move(dt);
				//sprawdz czy poza granicami, jesli tak to usun z wektora
				if(neutronContainer.elementAt(ii).outOfBorders(maxNumberOfAtomsInX*lengthBetweenAtoms,maxNumberOfAtomsInY*lengthBetweenAtoms,maxNumberOfAtomsInZ*lengthBetweenAtoms))
				{
					neutronContainer.remove(ii);
				}
				
			}
	}
	
	void CheckIfHit()
	{
			//sprawdz czy trafienie
			int jj=0;
			int superVariable= neutronContainer.size();
			for(int ii=0;ii<superVariable;ii++)
			{
				jj =(int) getIndexOfClosestAtom(neutronContainer.elementAt(ii));
				
					if(atomContainer.elementAt(jj).exists==true)
						{
					if(neutronContainer.elementAt(ii).distanceFromAtom(atomContainer.elementAt(jj))<maxDistanceFromAtomThatCausesFission)
						{
						if(Math.random()<probabilityOfReflection)
							{
							//odbicie neutronu
							neutronContainer.add(ii, new Neutron(atomContainer.elementAt(jj).x, atomContainer.elementAt(jj).y, atomContainer.elementAt(jj).z, velocityOfNeutron));
							neutronContainer.remove(ii+1);
							}
						else
							{
							//rozpad atomu
							atomContainer.elementAt(jj).exists=false;
							neutronContainer.add(new Neutron(atomContainer.elementAt(jj).x, atomContainer.elementAt(jj).y, atomContainer.elementAt(jj).z, velocityOfNeutron));
							energyAfterIteration+=energyOfFission;
							}
						}
				}
								
				
			}
			
		totalEnergy+=energyAfterIteration;
		instantaneousPower=energyAfterIteration/dt;
	

	}
		
	double getInstantaneousPower()
	{
		return instantaneousPower;
	}
	
	double getTotalEnergy()
	{
		return totalEnergy;
	}
	
	double getTime()
	{
		return dt;
	}
}

