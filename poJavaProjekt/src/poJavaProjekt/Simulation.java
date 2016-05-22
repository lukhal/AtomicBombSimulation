package poJavaProjekt;

import java.util.Vector;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Atom> atomContainer= new Vector<Atom>(1000);
		Vector<Neutron> neutronContainer= new Vector<Neutron>(1000);

		double probabilityOfNaturalFission=0.05;
		double probabilityOfReflection=0.5;
		double velocityOfNeutron=1;
		double dt=0.1;
		double maxDistanceFromAtomThatCausesFission=0.1;
		double energyOfFission=1;
		double energyAfterIteration=0;
		double totalEnergy=0;
		double instantaneousPower=0;
		
		
		//rozmiary siatki atomow
		int maxNumberOfAtomsInX=10;
		int maxNumberOfAtomsInY=10;
		int maxNumberOfAtomsInZ=10;
		
		
		
		//stworzenie atomów
		//współrzędne zmieniają się co 1
		
		for(double ii=0; ii<maxNumberOfAtomsInX; ii++)
		{
			for(double jj=0; jj<maxNumberOfAtomsInY; jj++)
			{
				for(double kk=0; kk<maxNumberOfAtomsInZ; kk++)
				{
					atomContainer.add(new Atom(ii, jj, kk, true));
				}
			}
		}
		
		System.out.println("elo");

		
		for(int lll=0;lll<1000;lll++)
		{
			energyAfterIteration=0;
			
			//losowy rozpad
			for(int ii=0;ii<atomContainer.size();ii++)
			{
				
				
				if(Math.random()<probabilityOfNaturalFission&&atomContainer.elementAt(ii).exists==true)
				{
					atomContainer.elementAt(ii).exists=false;
					
					neutronContainer.add(new Neutron(atomContainer.elementAt(ii).x, atomContainer.elementAt(ii).y, atomContainer.elementAt(ii).z, velocityOfNeutron));
					energyAfterIteration+=energyOfFission;
	
				}
			}
			//System.out.println("1");
			//System.out.println(Energy);

			//neutrony + czas
			for(int ii=0;ii<neutronContainer.size();ii++)
			{
				neutronContainer.get(ii).Move(dt);
				//sprawdz czy poza granicami, jesli tak to usun z wektora
				if(neutronContainer.elementAt(ii).outOfBorders(maxNumberOfAtomsInX,maxNumberOfAtomsInY,maxNumberOfAtomsInZ))
				{
					neutronContainer.remove(ii);
				}
				
			}

			//sprawdzam czy trafiłem
			
			int superVariable= neutronContainer.size();
			for(int ii=0;ii<superVariable;ii++)
			{
				//atomContainer.size()
				for(int jj=0;jj<atomContainer.size();jj++)
				{
					//&&atomContainer.elementAt(jj).exists==true
					if(neutronContainer.elementAt(ii).distanceFromAtom(atomContainer.elementAt(jj))<maxDistanceFromAtomThatCausesFission&&atomContainer.elementAt(jj).exists==true)
						{
						//System.out.println(neutronContainer.elementAt(ii).distanceFromAtom(atomContainer.elementAt(jj)));

						//System.out.println("ii" +ii);
						//System.out.println("jj" +jj);
						if(Math.random()<probabilityOfReflection)
							{
							//odbicie neutronu
							neutronContainer.add(ii, new Neutron(atomContainer.elementAt(jj).x, atomContainer.elementAt(jj).y, atomContainer.elementAt(jj).z, velocityOfNeutron));
							neutronContainer.remove(ii++);
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
		
		//System.out.println("Energy after iteration:" + energyAfterIteration);
		
		//"Power:" + 
		System.out.print(instantaneousPower+ " ");
		
		//"Total energy after an iteration:" + 
		System.out.println(totalEnergy);

		
		}
		//System.out.println("Total energy:" + totalEnergy);
		
		
	}
}

