package VWorld;

public class Grass extends Plant {
	Grass(int x, int y , boolean sex,World world){
		super(x,y,sex,world);
		this.numberOfChilds=1;
		this.eatableTimes=2;
		this.symbol='G';
		this.spec="Grass";
		this.typeOfOrganism=0;
		
		
	}
}
