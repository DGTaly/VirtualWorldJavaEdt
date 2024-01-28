package VWorld;

import java.util.Random;

public class Turtle  extends Animal {
	Turtle(int x, int y, boolean sex,World world){
		super(x,y,sex,world);
		this.defense=5;
		this.strenght =2;
		this.initiative = 2;
		this.numberOfChilds = 4;
		this.lenghtOfPregnacy = 3;
		this.isFeared = true;
		this.symbol ='T';
		this.spec = "Turtle";
		this.typeOfOrganism = 5;
	}

@Override public void moving(int x, int y) {
	Random random=new Random();
	int randmove=random.nextInt(4);
	if (randmove == 0)	{super.moving(x, y);}
	else {move.setX(0);move.setY(0);}
}
}
