package VWorld;

public class Plant extends Organism {
	protected int eatableTimes;
	
	public Plant(int x, int y, boolean sex,World world)
	{
		super( x,  y, sex, world);
		this.spec="plant";
		this.numberOfChilds=1;
		this.symbol='P';
		this.adolescenseAge=0;
		this.eatableTimes=1;
		this.isAdult=true;
		
		
}
	@Override public String affection(Animal other) {
		String output = this.spec+" has been eaten by "+other.spec+".\n";
		System.out.println(this.spec+" has been eaten by "+other.spec);
		this.eatableTimes-=1;
		if (this.eatableTimes==0)
		{
			this.numberOfChilds=0;
			this.isPregnant=false;
			output+=this.death();
		}
		return output;
	}
	
	@Override public void moving(int x, int y)
	{
		if (this.round==1)
		{
			this.neighbours=neighboursCount();
			this.neighboursPos=neighboursSerching();
		}
		System.out.println(this.spec + " is a plant, it doesn't moving.");
		
	}
	@Override public String collisionWithPlant(Plant other) 
	{
		String output = "";
		if (other!=null)
		{
			if ((this.typeOfOrganism==other.typeOfOrganism)&&(this.isFemale!=other.isFemale))
			{
				if(!this.isFemale&&!other.isPregnant){
					other.isPregnant=true;
					other.pregnacyCounter=other.lenghtOfPregnacy;
					System.out.println(this.spec +" pollinated "+other.spec);
					output=this.spec +" pollinated "+other.spec+".\n";
				}
				else if(this.isFemale&&!this.isPregnant)
				{
					this.isPregnant=true;
					this.pregnacyCounter=other.lenghtOfPregnacy;
					System.out.println(this.spec +"has been pollinated by "+other.spec);
					output=this.spec +" has been pollinated by "+other.spec+".\n";
				}
				else {output="Polination acomplished!\n";}
			}
			System.out.println(this.spec +" and "+ other.spec +" has meet eachother.");
			output+=this.spec +" and "+ other.spec +" has meet eachother.\n";
		}
		if (other==null)
		{
			System.out.println("There is no plant in this neighbour of "+this.spec);
			output="There is no plant in this neighbour of "+this.spec+".\n";
		}
		return output;
	}
	
}
