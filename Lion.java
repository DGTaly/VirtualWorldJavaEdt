package VWorld;

public class Lion extends Animal {
	Lion(int x, int y, boolean sex, World world){
		super(x,y,sex,world);
		this.defense=12;
		this.strenght =12;
		this.initiative = 8;
		this.numberOfChilds = 3;
		this.lenghtOfPregnacy = 9;
		this.adolescenseAge=2;
		this.symbol ='L';
		this.spec = "Lion";
		this.typeOfOrganism = 6;
		this.isKing=true;
	}
}
