package VWorld;

public class Sheep extends Animal {
	Sheep(int x, int y, boolean sex,World world ){
		super(x,y,sex,world);
		this.defense=4;
		this.strenght =4;
		this.initiative = 5;
		this.numberOfChilds = 5;
		this.lenghtOfPregnacy = 6;
		this.adolescenseAge=1;
		this.isFeared = true;
		this.symbol ='S';
		this.spec = "Sheep";
		this.typeOfOrganism = 3;		
	}
}
