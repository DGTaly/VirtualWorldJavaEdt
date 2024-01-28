package VWorld;

public class Unicorn  extends Animal {
	Unicorn(int x, int y, boolean sex,World world){
		super(x,y,sex,world);
		this.defense=25;
		this.strenght =25;
		this.initiative = 15;
		this.numberOfChilds = 1;
		this.lenghtOfPregnacy = 1;
		this.adolescenseAge=2;
		this.isFriendly = true;
		this.symbol ='U';
		this.spec = "Unicorn";
		this.typeOfOrganism = 7;
	}
@Override public String affection(Animal other) {
	String output = "0";
	if(!other.isFriendly)
	{
		other.isBlind=true;
		other.isFiredUp=false;
		System.out.println(other.spec+" has been blinded by "+ this.spec);
		output=other.spec+" has been blinded by "+ this.spec+".\n";
	}
	else {
		System.out.println(other.spec+" meet "+ this.spec);
		output=other.spec+" meet "+ this.spec+".\n";
		}
	return output;
}
@Override public String collisionWithAnimal(Animal other, int x, int y)
{
	String output ="";
	this.isFemale=false;
	if(other==null) {
		this.changePos(x,y);
		System.out.println(this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+")");
		output+=this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+")\n";
	}
	if(other!=null)
	{
	if(other.isAlive&&!other.isBlind) {
		output+=other.affection(this);
		if(other.isFemale==this.isFemale){this.isFemale=!this.isFemale;}
		this.typeOfOrganism=other.typeOfOrganism;
		this.isPregnant=true;
		this.pregnacyCounter=this.lenghtOfPregnacy;
		System.out.println(this.spec+" has coppuling with "+other.spec);
		output+=this.spec+" has coppuling with "+other.spec+".\n";
		this.previous.setX(other.actual.getX());
		this.previous.setY(other.actual.getY());
		if (other.isFemale) {this.isFemale=true;}
		}
	if(other.isAlive&&other.isBlind) {
		this.typeOfOrganism=7;
		System.out.println(this.spec+" raped "+other.spec);
		output+=this.spec+" raped "+other.spec+".\n";
		this.previous.setX(other.actual.getX());
		this.previous.setY(other.actual.getY());
	}
	if(!other.isAlive) {
		this.changePos(x,y);
		System.out.println(this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+")");
		output+=this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+").\n";
	}	
	}
	return output;
}
}
