package VWorld;

public class Coca extends Plant {
Coca(int x, int y , boolean sex,World world){
	super(x,y,sex,world );
	this.numberOfChilds=2;
	this.eatableTimes=3;
	this.spec="Coca";
	this.symbol='C';
	this.typeOfOrganism=2;
	this.lenghtOfPregnacy=2;
	
}
@Override public String affection(Animal other) {
	other.isFiredUp = true;
	String output = "After eating some leaves of "+this.spec+" "+other.spec+" is fired up!!! \n";
	System.out.println("After eating some of leaves of "+this.spec+" "+other.spec+" is fired up!!!");
	output+=super.affection(other);
	return output;
}
}
