package VWorld;

import java.util.Random;

public class Animal extends Organism {
public Animal(int x, int y, boolean sex, World world ){
	super( x,  y, sex,world);
	this.spec="animal";
	this.initiative=1;
	this.defense=1;
	this.strenght=1;
	this.lenghtOfPregnacy=3;
	this.numberOfChilds=1;
	this.symbol='A';
	
	
	
}
@Override public void moving(int x, int y) {
	
	
	if (this.isFeared) 
	{
		this.neighbours=this.neighboursCount();
		this.neighboursPos=this.neighboursSerching();
		Random random=new Random();
		int check=0;
		while(check==0) {				
			int randnum = random.nextInt(this.neighbours);
			Animal other = getWorldOfOrganism().getTile(this.neighboursPos[randnum].getX(),this.neighboursPos[randnum].getY()).getAnimalOnTile();
			if(other==null) {
				this.move.setX(this.neighboursPos[randnum].getX()-this.actual.getX());
				this.move.setY(this.neighboursPos[randnum].getY()-this.actual.getY());
				check=1;					
			}
			if(other!=null)
			{
				if(!other.isAlive||(other.isAlive&&!other.isKing)) {
					this.move.setX(this.neighboursPos[randnum].getX()-this.actual.getX());
					this.move.setY(this.neighboursPos[randnum].getY()-this.actual.getY());
					check=1;
					}					
				if(other.isAlive&&other.isKing) {
					if (randnum<(this.neighbours-1)) {
						Position temp= new Position(this.neighboursPos[randnum].getX(),this.neighboursPos[randnum].getY());
						this.neighboursPos[randnum].setX(this.neighboursPos[this.neighbours-1].getX());
						this.neighboursPos[randnum].setY(this.neighboursPos[this.neighbours-1].getY());
						
						this.neighboursPos[this.neighbours-1].setX(temp.getX());
						this.neighboursPos[this.neighbours-1].setY(temp.getY());
						}
					this.neighbours-=1;
					if(this.neighbours==0) {
						check=1;
						this.move.setX(0);
						this.move.setY(0);
					}
				}					
			}
		}
	}
	
	else if(!this.isFeared)
	{	
		int xxx=0;
		int yyy=0;
		Random random=new Random();
		while ((xxx==0 && yyy== 0)||((this.actual.getX()+xxx)<0)||((this.actual.getX()+xxx)>=getWorldOfOrganism().getWorldX())||((this.actual.getY()+yyy)<0)||((this.actual.getY()+yyy)>=getWorldOfOrganism().getWorldY()))
		{		
			xxx= random.nextInt(3)-1;
			yyy= random.nextInt(3)-1;
		}
		move.setX(xxx);
		move.setY(yyy);
	}
}
@Override public String collisionWithAnimal(Animal other,int x,int y) {
	String output="";
	if(other==null) {
		this.changePos(x,y);
		System.out.println(this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+")");
		output+=this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+").\n";
	}	
	if (other!=null)
	{
			if(!other.isAlive) {
				this.changePos(x,y);
				System.out.println(this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+")");
				output+=this.spec+" has moved from ("+this.previous.getX()+","+this.previous.getY()+") to ("+ this.actual.getX()+","+this.actual.getY()+").\n";
			}	
			else if (other.isAlive)
			{
				output+=other.affection(this);
				if(!this.isBlind&&!this.isFriendly)
				{
					if ((other.typeOfOrganism==this.typeOfOrganism)&&(this.isFemale!=other.isFemale)){
						output+=copulation(other);
						this.previous.setX(other.actual.getX());
						this.previous.setY(other.actual.getY());
					}
					else {
						output+=domination(other,x,y);
					}
				}
				else if(!this.isBlind&&this.isFriendly)
				{
					if ((other.typeOfOrganism==this.typeOfOrganism)&&(this.isFemale!=other.isFemale)) {
						output+=copulation(other);
					}
					else {
						output+=meetingCommunicate(other);
					}
					this.previous.setX(other.actual.getX());
					this.previous.setY(other.actual.getY());
				}
				else if (this.isBlind)
				{
					output+=this.spec+" gone away to his startpoint!\n";
					this.previous.setX(other.actual.getX());
					this.previous.setY(other.actual.getY());
				}
			}
	}
	return output;
}
@Override public String collisionWithPlant(Plant other)
{
	String output="";
	if (other != null) {
       output=other.affection(this);
    }
	return output;
}
public String copulation(Animal other) {
	String output = "";
	if (other.isAdult&&this.isAdult)
	{
		if(this.isFemale&&!this.isPregnant) {
			this.isPregnant=true;
			this.pregnacyCounter=this.lenghtOfPregnacy;
		}
		
		else if(other.isFemale&&!other.isPregnant)
		{
			other.isPregnant=true;
			other.pregnacyCounter=other.lenghtOfPregnacy;
		}
	}
	System.out.println(this.spec+" copulated with "+other.spec);
	output = this.spec + " copulated with "+other.spec + "\n";
	return output;
}
public String domination(Animal other,int x,int y) {
	String output="";
	if(other.typeOfOrganism==this.typeOfOrganism)
	{
		output=interiorDominationCommunicate();
		this.changePos(x,y);
		other.changePos(-x,-y);
	}
	if(other.typeOfOrganism!=this.typeOfOrganism)
	{
		if(other.isBlind) {
			output+=eating(other);
			this.changePos(x,y);
		}
		if(!other.isBlind) {
			if(this.strenght>=other.defense)
			{
				output+=eating(other);
				this.changePos(x,y);
			}
			if((this.strenght<other.defense)&&(this.strenght>=other.strenght)) 
			{
				output+=other.externalDominationCommunicate(this);
			}
			if((this.strenght<other.defense)&&(this.strenght<other.strenght)) 
			{
				output+=other.eating(this);
			}
		}
	}
	
	return output;
}
public String meetingCommunicate(Animal other) 
{
	System.out.println("Animals "+this.spec+" and "+ other.spec +" part ways in peace.");
	String output = "Animals "+this.spec+" and "+ other.spec +" part ways in peace.\n";
	return output;
}
public String externalDominationCommunicate(Animal other)
{
	System.out.println(this.spec+" dominated other animal: "+ other.spec +", and force it to leave the territory.");
	String output = this.spec+" dominated other animal: "+ other.spec +", and force it to leave the territory.\n";
	return output;
}
public String interiorDominationCommunicate() 
{
	System.out.println("One "+ this.spec +" forced the other one to leave this place.");
	String output = "One "+ this.spec +" forced the other one to leave this place.\n";
	return output;
}
public String eating(Animal other) 
{
	System.out.println(this.spec +" ate " +other.spec);
	other.death();
	String output = this.spec +" ate " +other.spec+".\n";
	return output;

} 

}