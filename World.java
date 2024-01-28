package VWorld;

import java.util.Random;

public class World {

	private int rows;
	private int cols;
	private Tile[][] worldTiles;
	private String name;
	private int round;
	private int animalFullness;
	private int plantFullness;
	private int maxFullness;
	private int startFullness;
	private int worldFullness;
	private Organism[] allOrganisms;
	private String collector;

	public World(int x, int y, String word)
	{
		this.round=1;
		this.rows=y;
		this.cols=x;
		this.animalFullness=0;
		this.plantFullness=0;
		this.maxFullness=rows*cols;
		this.startFullness=(rows*cols*2/5);
		this.collector="Start";
		this.name=word;
		this.worldTiles = new Tile[y][x];

		for (int i= 0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				worldTiles[i][j] = new Tile(j,i);		
			}
		}

		int size=2*getWorldX()*getWorldY();
		this.allOrganisms=new Organism[size];
		for (int i =0; i<size; i++) 
		{
			allOrganisms[i]= new Organism(0,0,false,this);
		}
		Random random = new Random();
		for (int i=0; i<startFullness;i++) 
		{
			int type = 7;
			
			if(i<(startFullness-2))
			{
				type = random.nextInt(7);
			}
			worldFullfill(type);			
		}
	}

public int getStartFullness() {return startFullness;}//stała wartość wynosząca  ok. 20 procent wszelkich mozliwych organizmów w świecie
public int getWorldX() {return cols;}
public int getWorldY() {return rows;}
public int getWorldFullness() {return worldFullness;}
public void setWorldFullness(int full) {this.worldFullness=full;}
public int getAnimalFullness() {return animalFullness;}
public void setAnimalFullness(int full) {this.animalFullness=full;}
public int getPlantFullness() {return plantFullness;}
public void setPlantFullness(int full) {this.plantFullness=full;}
public void setAllOrganisms(Organism[]organisms) {
	this.allOrganisms=organisms;
}
public String getCollector() {return collector;}
public void setCollector(String string) {this.collector = string;}
public void addToCollector(String string ) { this.collector += string;}
public Organism[] getAllOrganisms() {
	return allOrganisms;
}
public void setOrganism(Organism organism, int i) {
	this.allOrganisms[i]=organism;
}
public Organism getOrganism(int i) {
	return allOrganisms[i];
}
public void setName(String word) {
	this.name=word;
}
public String getName() {
	return name;
}
public Tile getTile(int x,int y)
{
	return worldTiles[y][x];
}

public void increaseWorldRound() {this.round+=1;}
public int getRound() {	return round;}

public String newOrganism(int type, int x, int y, boolean sex )
{
	String output="";
	switch(type)
	{
	case 0:
		if (this.plantFullness<this.maxFullness)
		{
			if (sex)
			{
				System.out.println("It's a new female Grass.");
				output+="It's a new female Grass.\n";
			}
			if(!sex)
			{
				System.out.println("It's a new male Grass.");
				output+="It's a new male Grass.\n";
			}
			Grass grass = new Grass(x,y,sex,this);
			getTile(x,y).setPlantOccupant(grass);
			setPlantFullness(getPlantFullness()+1);
			setOrganism(grass,getWorldFullness());	
			setWorldFullness(getWorldFullness()+1);			
		}
		break;
	
	case 1:
		if (this.plantFullness<this.maxFullness)
			{
			if (sex)
			{
				System.out.println("It's a new female Milkweed.");
				output+="It's a new female Milkweed.\n";
			}
			if(!sex) 
			{
				System.out.println("It's a new male Milkweed.");
				output+="It's a new male Milkweed.\n";
			}
			Milkweed milkweed = new Milkweed(x,y,sex,this);
			getTile(x,y).setPlantOccupant(milkweed);
			setPlantFullness(getPlantFullness()+1);
			setOrganism(milkweed,getWorldFullness());	
			setWorldFullness(getWorldFullness()+1);
		}
		break;
	case 2:
		if (this.plantFullness<this.maxFullness)
		{
			if (sex)
			{
				System.out.println("It's a new female Coca.");
				output+="It's a new female Coca.\n";
			}
			if(!sex) 
			{
				System.out.println("It's a new male Coca.");
				output+="It's a new male Coca.\n";
			}		
			Coca coca = new Coca(x,y,sex,this);
			getTile(x,y).setPlantOccupant(coca);
			setPlantFullness(getPlantFullness()+1);
			setOrganism(coca,getWorldFullness());
			setWorldFullness(getWorldFullness()+1);
		}
		break;
	case 3:
		if (this.animalFullness<this.maxFullness)
		{
			if (sex)
			{
				System.out.println("It's a new female Sheep.");
				output+="It's a new female Sheep.\n";
			}
			if(!sex)
			{
				System.out.println("It's a new male Sheep.");
				output+="It's a new male Sheep.\n";
			}			
			Sheep sheep = new Sheep(x,y,sex,this);
			getTile(x,y).setAnimalOccupant(sheep);
			setAnimalFullness(getAnimalFullness()+1);
			setOrganism(sheep,getWorldFullness());
			setWorldFullness(getWorldFullness()+1);
		}
		break;
	case 4:
		if (this.animalFullness<this.maxFullness)
		{
			if (sex)
			{
				System.out.println("It's a new female Wolf.");
				output+="It's a new female Wolf.\n";
			}
			if(!sex) 
			{
				System.out.println("It's a new male Wolf.");
				output+="It's a new male Wolf.\n";
			}
			
			Wolf wolf = new Wolf(x,y,sex,this);
			getTile(x,y).setAnimalOccupant(wolf);
			setAnimalFullness(getAnimalFullness()+1);
			setOrganism(wolf,getWorldFullness());
			setWorldFullness(getWorldFullness()+1);
		}
		break;
		
	case 5:
		if (this.animalFullness<this.maxFullness)
		{
			if (sex)
			{
				System.out.println("It's a new female Turtle.");
				output+="It's a new female Turtle.\n";
			}
			if(!sex)
			{
				System.out.println("It's a new male Turtle.");
				output+="It's a new male Turtle.\n";
			}
			Turtle turtle = new Turtle(x,y,sex,this);
			getTile(x,y).setAnimalOccupant(turtle);			
			setAnimalFullness(getAnimalFullness()+1);
			setOrganism(turtle,getWorldFullness());
			setWorldFullness(getWorldFullness()+1);
		}
		break;		
	case 6:
		if (this.animalFullness<this.maxFullness)
		{
			if (sex)
			{
				System.out.println("It's a new female Lion.");
				output+="It's a new female Lion.\n";
			}
			if(!sex)
			{
				System.out.println("It's a new male Lion.");
				output+="It's a new male Lion.\n";
			}
			Lion lion = new Lion(x,y,sex,this);
			getTile(x,y).setAnimalOccupant(lion);		
			setAnimalFullness(getAnimalFullness()+1);
			setOrganism(lion,getWorldFullness());
			setWorldFullness(getWorldFullness()+1);
		}
		break;
	case 7:
		if (this.animalFullness<this.maxFullness)
		{
			if (sex)
			{
				System.out.println("It's a new female Unicorn.");
				output+="It's a new female Unicorn.\n";
			}
			if(!sex)
			{
				System.out.println("It's a new male Unicorn.");
				output+="It's a new male Unicorn.\n";
			}
			Unicorn unicorn = new Unicorn(x,y,sex,this);
			getTile(x,y).setAnimalOccupant(unicorn);
			setAnimalFullness(getAnimalFullness()+1);
			setOrganism(unicorn,getWorldFullness());
			setWorldFullness(getWorldFullness()+1);
		}
		break;
	default:
		System.out.println("Wrong type of organism. Try again.");
		break;
	}
	return output;
}
public String worldFullfill(int type)
{

	Position pos = freeTileCheck(this.cols, this.rows, type);
	Random random = new Random();
	boolean sex = random.nextBoolean();
	String output=newOrganism(type,pos.getX(),pos.getY(), sex);
	return output;
}

public Position freeTileCheck(int x, int y, int type) {
	
	int xx=0;
	int yy=0;
	Random random = new Random();
if (type<3)
{
	xx=random.nextInt(x);
	yy=random.nextInt(y);
	while (getTile(xx,yy).getPlantOccupation()) {
		xx=random.nextInt(x);
		yy=random.nextInt(y);
	}
}
else if (type>=3)
{
	xx=random.nextInt(x);
	yy=random.nextInt(y);
	while (getTile(xx,yy).getAnimalOccupation()) {
		xx=random.nextInt(x);
		yy=random.nextInt(y);
	}
}
Position pos = new Position(xx,yy);
return pos;
}

public void roundTileCleaner(Organism org, Animal otheranimal) {
	
	if(org instanceof Animal)
	{
		if((otheranimal==null || !otheranimal.isAlive)&& org.isAlive)
		{
				getTile(org.actual.getX(),org.actual.getY()).setAnimalOccupant((Animal)org);
				getTile(org.previous.getX(),org.previous.getY()).setAnimalFree();
		}
		else if(otheranimal!=null && otheranimal.isAlive && org.isAlive)
		{	
				getTile(org.actual.getX(),org.actual.getY()).setAnimalOccupant((Animal)org);
				getTile(org.previous.getX(),org.previous.getY()).setAnimalOccupant(otheranimal);
				if(otheranimal.actual.getX()!=org.previous.getX() || otheranimal.actual.getY()!=org.previous.getY())
				{
					otheranimal.actual.setX(org.previous.getX());
					otheranimal.actual.setY(org.previous.getY());
				}
		}
		else if(!org.isAlive)
		{
				getTile(org.actual.getX(),org.actual.getY()).setAnimalFree();
		}
	}
	if(org instanceof Plant)
	{
		if(!org.isAlive)
		{
			getTile(org.actual.getX(),org.actual.getY()).setPlantFree();
		}
	}
}

public int liveOrganismCounter(Organism[] input, int numberOfOrganisms)
{	int counter=0;
	for(int i=0; i < numberOfOrganisms; i++)
	{
		Organism org=input[i];		
		if (!org.isAlive) {break;}
		counter++;		
	}
	return counter;
}

public int animalCounter(Organism[] input, int organisms)
{
	int counter = 0;
	for (int i =0; i< organisms; i++)
	{
		if (input[i]!=null)
		{
			if (input[i] instanceof Animal)
				{counter++;}
		}
	}
	return counter;
}

public Organism[] primalSorting(Organism[] organisms, int size) {
	for (int i=0;i<size-1; i++)
	{
		
		Organism left=organisms[i];
		Organism right=	organisms[i+1];
		if (!left.isAlive&&right.isAlive) {
			organisms[i]=right;
			organisms[i+1]=left;
			i-=2;
			if (i<0) {i=-1;}
		}
		if (left.isAlive==right.isAlive) {		
			if (left.initiative==right.initiative)
			{
				if(left.round<right.round)
				{
					organisms[i]=right;
					organisms[i+1]=left;
					i-=2;
					if (i<0) {i=-1;}
				}
			}
			if (left.initiative<right.initiative)
			{
				organisms[i]=right;
				organisms[i+1]=left;
				i-=2;
				if (i<0) {i=-1;}
			}
		}
	}
		
	return organisms;
}

public Organism[] deadCleaner(Organism[]input,int organisms) {

	for(int i =0; i<organisms; i++)
	{
		if (input[i]!=null) {
			if(!input[i].isAlive)
			{input[i]=null;}
		}
	}
	return input;
}

 public String organismRound(Organism organism) 
{	
	String output = "Organism: "+ organism.spec + ", age/round: "+ organism.age+"/"+ organism.round + ", Start position: ("+organism.actual.getX()+","+organism.actual.getY()+")\n";
	if(organism instanceof Animal)
	{
	int counter = 1;
	if (organism.isAlive&&organism.isFiredUp&&!organism.isBlind) {counter=2;}
	if (!organism.isAlive||organism.isBlind) {counter=0;}
	organism.isFiredUp = false;
	organism.isBlind = false;
	while(counter>0)
	{		
		if (organism.isFeared) 
		{
			organism.neighbours=organism.neighboursCount();
			organism.neighboursPos=organism.neighboursSerching();
			Random random=new Random();
			int check=0;
			while(check==0) {				
				int randnum = random.nextInt(organism.neighbours);
				Animal other=getTile(organism.neighboursPos[randnum].getX(),organism.neighboursPos[randnum].getY()).getAnimalOnTile();
				if(other==null) {
					organism.move.setX(organism.neighboursPos[randnum].getX()-organism.actual.getX());
					organism.move.setY(organism.neighboursPos[randnum].getY()-organism.actual.getY());
					check=1;					
				}
				else if(other!=null)
				{
					if(!other.isAlive||(other.isAlive&&!other.isKing)) 
					{
						organism.move.setX(organism.neighboursPos[randnum].getX()-organism.actual.getX());
						organism.move.setY(organism.neighboursPos[randnum].getY()-organism.actual.getY());
						check=1;
					}				
					else if(other.isAlive&&other.isKing) 
					{
						if (randnum<(organism.neighbours-1)) 
						{
							Position temp= new Position(organism.neighboursPos[randnum].getX(),organism.neighboursPos[randnum].getY());
							organism.neighboursPos[randnum].setX(organism.neighboursPos[organism.neighbours-1].getX());
							organism.neighboursPos[randnum].setY(organism.neighboursPos[organism.neighbours-1].getY());							
							organism.neighboursPos[organism.neighbours-1].setX(temp.getX());
							organism.neighboursPos[organism.neighbours-1].setY(temp.getY());							
						}
					}
					
				}
				organism.neighbours-=1;
				if(organism.neighbours==0) {
					check=1; 
					organism.move.setX(0);
					organism.move.setY(0);
				}
			}
		}
		else {organism.moving(getWorldX(),getWorldY());}
		int newX = organism.actual.getX()+organism.move.getX();
		int newY = organism.actual.getY()+organism.move.getY();
		if(newX!=organism.actual.getX()||newY!=organism.actual.getY())
		{
		Animal otherAnimal = getTile(newX,newY).getAnimalOnTile();
		output+=organism.collisionWithAnimal(otherAnimal,organism.move.getX(),organism.move.getY());
		roundTileCleaner(organism, otherAnimal);
		}
		Plant otherPlant = getTile(organism.actual.getX(),organism.actual.getY()).getPlantOnTile();
		if(organism.isAlive&&!organism.isBlind)
		{
			output+=organism.collisionWithPlant(otherPlant);
		}
		if(organism.isBlind||!organism.isAlive) {counter=0;}
		counter-=1;
		
		if(otherPlant!=null)
		{
			if (!otherPlant.isAlive) 
			{
				getTile(organism.actual.getX(),organism.actual.getY()).setPlantFree();
			}
		}
	}
	
	}
	if(organism instanceof Plant) {
		organism.moving(getWorldX(),getWorldY());
		for (int i = 0; i< organism.neighbours; i++)
		{
		Plant otherPlant = getTile(organism.neighboursPos[i].getX(),organism.neighboursPos[i].getY()).getPlantOnTile();
		output+=organism.collisionWithPlant(otherPlant); 
		}
	}
	organism.round+=1;
	if(organism.isPregnant&&organism.isAlive)
	{
		organism.pregnacyCounter-=1;
		if(organism.pregnacyCounter==0) {
			
			if(organism instanceof Animal) {
				Random random= new Random();
				int childs = random.nextInt(organism.numberOfChilds)+1;
				for(int i=0;i<childs;i++) {
					worldFullfill(organism.typeOfOrganism);
				}
				if(organism instanceof Unicorn)
					{organism.typeOfOrganism=7;}
				}
			if(organism instanceof Plant)
			{
				for(int i=0;i<organism.numberOfChilds;i++) {
					worldFullfill(organism.typeOfOrganism);
				}
			}			
			organism.isPregnant=false;
			
		}
	}
	output+=organism.aging();
return output;	
}
}