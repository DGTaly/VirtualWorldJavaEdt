package VWorld;

public class Organism {
	protected int strenght;
	protected int defense;
	protected int initiative;
	protected int numberOfChilds;
	protected int lenghtOfPregnacy;
	protected int pregnacyCounter;
	protected int adolescenseAge;
	protected int round;
	protected int typeOfOrganism;
	protected int age;
	protected char symbol;
	protected String spec;
	protected boolean isAlive;
	protected boolean isAdult;
	protected boolean isFemale;
	protected boolean isBlind;
	protected boolean isFriendly;
	protected boolean isFiredUp;
	protected boolean isFeared;
	protected boolean isPregnant;
	protected boolean isKing;
	protected Position actual;
	protected Position previous;
	protected Position move;	
	protected Position[] neighboursPos;
	protected int neighbours;
	private World worldOfOrganism;
	
	public Organism(int x,int y, boolean sex,World world)
	{
		this.move=new Position(0,0);
		this.actual=new Position(x,y);
		this.previous=new Position(x,y);
		this.strenght=0;
		this.defense=0;
		this.initiative=0;
		this.numberOfChilds=0;
		this.lenghtOfPregnacy=1;
		this.pregnacyCounter=0;
		this.round=1;
		this.adolescenseAge=1;
		this.age=0;
		this.typeOfOrganism=100;
		this.symbol='O';
		this.spec="organism";
		this.isAlive=true;
		this.isAdult=false;
		this.isBlind=false;
		this.isFeared=false;
		this.isFemale=sex;
		this.isFiredUp=false;
		this.isFriendly=false;
		this.isPregnant=false;
		this.isKing=false;
		this.neighbours=0;
		this.neighboursPos = new Position[8];
		this.worldOfOrganism=world;
		
		
	}
	
	public String aging()
	{
		String output = "";
		this.age=this.round/12;
		if ((this.age>=this.adolescenseAge)&& !this.isAdult)
		{
			this.isAdult=true;
			output=this.spec+" is Adult now.\n";
			
		}
		return output;
	}
	public String collisionWithAnimal(Animal other,int x, int y) {String output="";return output;}
	public String collisionWithPlant(Plant other) {String output="";return output;}
	public String affection(Animal other) {String output="";return output;}
	void changePos(int x, int y) {
		previous.setX(actual.getX());
		previous.setY(actual.getY());
		actual.setX(actual.getX()+ x);
		actual.setY(actual.getY()+ y);
	}
	void moving(int x, int y) {	}
	public String death() {
		this.isAlive=false;
		System.out.println(this.spec + " is dead!");
		String output = this.spec + " is dead!\n";
		return output;
		
	}
	public Position[] neighboursSerching()
	{
		Position[] pos=new Position[neighbours];
		int xX=0;int yY=0;
		for(int i=0; i<neighbours; i++)
		{			
			if (i == 0 || i == 2 || i == 6) {
	            yY = Math.max(0, this.actual.getY() - 1);
	        } else if (i == 1 || i == 5) {
	            yY = this.actual.getY();
	        } else if (i == 3 || i == 4) {
	            yY = Math.min(worldOfOrganism.getWorldY() - 1, this.actual.getY() + 1);
	        }

	        if (i == 0 || i == 3 || i == 7) {
	            xX = Math.max(0, this.actual.getX() - 1);
	        } else if (i == 1 || i == 6) {
	            xX = this.actual.getX();
	        } else if (i == 2 || i == 4 || i == 5) {
	            xX = Math.min(worldOfOrganism.getWorldX() - 1, this.actual.getX() + 1);
	        }			
			pos[i]=new Position(xX,yY);		
		}
		return pos;
	}
	public int neighboursCount()
	{
		int neighbours = 0;
		if (((this.actual.getX()==0)||(this.actual.getX()==(worldOfOrganism.getWorldX()-1)))&&((this.actual.getY()==0)||(this.actual.getY()==(worldOfOrganism.getWorldY()-1))))
		{neighbours = 3;}
		if (((this.actual.getX()==0)&&(this.actual.getY()!=(worldOfOrganism.getWorldY()-1))&&(this.actual.getY()!=0))||((this.actual.getY()==0)&&(this.actual.getX()!=(worldOfOrganism.getWorldX()-1))&&(this.actual.getY()!=0)))
		{neighbours = 5;}
		else {neighbours=8;}
		return neighbours;
	}
	public World getWorldOfOrganism() { return worldOfOrganism;}
	
}
