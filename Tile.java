package VWorld;

public class Tile {
protected Position tilePos;
private Animal animalOccupant;
private Plant plantOccupant;
private char plantSymbol;
private char animalSymbol;
private boolean isOccupiedByAnimal;
private boolean isOccupiedByPlant;

public Tile(int x, int y)
{
this.tilePos=new Position(x,y);
this.animalOccupant=null;
this.plantOccupant=null;
this.plantSymbol='0';
this.animalSymbol='0';
this.isOccupiedByAnimal=false;
this.isOccupiedByPlant=false;
}
public Animal getAnimalOnTile(){
	return animalOccupant;
}
public Position getTilePosition() {
	return tilePos;
}
public Plant getPlantOnTile(){
	return plantOccupant;
}
public void setPlantOccupant(Plant plant) {
	isOccupiedByPlant = true;
	this.plantOccupant=plant;
	setPlantSymbol(plant.symbol);
}
public void setAnimalOccupant(Animal animal) {
	isOccupiedByAnimal = true;
	this.animalOccupant=animal;
	setAnimalSymbol(animal.symbol);
}
public void setAnimalFree()
{
	isOccupiedByAnimal=false;
	this.animalOccupant=null;
	setAnimalSymbol('0');
}
public void setPlantFree() {
	isOccupiedByPlant=false;
	this.plantOccupant=null;
	setPlantSymbol('0');
}
public void setPlantSymbol(char symbol) {
	this.plantSymbol=symbol;	
}
public void setAnimalSymbol(char symbol) {
	this.animalSymbol=symbol;	
}
public char getPlantSymbol() {
	return plantSymbol;
}
public char getAnimalSymbol() {
	return animalSymbol;
}
public void printTheTile() {
	if(isOccupiedByPlant && isOccupiedByAnimal)
	{System.out.print("["+this.getPlantSymbol()+"-"+this.getAnimalSymbol()+"]");}
	if(isOccupiedByPlant && !isOccupiedByAnimal)
	{System.out.print("["+this.getPlantSymbol()+"--]");}
	if(!isOccupiedByPlant && isOccupiedByAnimal)
	{System.out.print("[--"+this.getAnimalSymbol()+"]");}
	if(!isOccupiedByPlant && !isOccupiedByAnimal)
	{System.out.print("[---]");}
}
public boolean getPlantOccupation()
{
	return isOccupiedByPlant;
}
public boolean getAnimalOccupation()
{
	return isOccupiedByAnimal;
}

}
