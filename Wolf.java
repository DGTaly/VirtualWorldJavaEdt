package VWorld;

public class Wolf extends Animal {
	Wolf(int x, int y, boolean sex,World world){
		super(x,y,sex,world);
		this.defense=7;
		this.strenght =7;
		this.initiative = 6;
		this.numberOfChilds = 3;
		this.lenghtOfPregnacy = 6;
		this.symbol ='W';
		this.spec = "Wolf";
		this.typeOfOrganism = 4;
	}
}
