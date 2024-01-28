package VWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class GameWindow extends JPanel {
	
	private Main main;
    private JPanel WorldAnimalPanel; 
    private JLabel roundLabel;
    private WorldMap worldMap;    
    private JTextArea textArea;
    private GameMenu gameMenu; 
    private World world;
    Color sandColor = new Color(255, 255, 153);
    
    public GameWindow(World world, GameMenu gameMenu, Main main) {
        
    	this.main=main;
    	this.world=world;
    	this.gameMenu = gameMenu;
    	setLayout(new BorderLayout());
    	
    	worldMap = new WorldMap (world); 
    	
        add(worldMap, BorderLayout.CENTER);
        
        JPanel WorldAnimalPanel = new JPanel();
        
        JButton Grass = new JButton ("Grass");
        Grass.setBackground(getWorldMap().darkGreen);
        JButton Milkweed = new JButton ("Milkweed");
        Milkweed.setBackground(Color.yellow);
        JButton Coca = new JButton ("Coca");
        Coca.setBackground(Color.green);
        JButton Sheep= new JButton ("Sheep");
        Sheep.setBackground(Color.white);
        JButton Wolf= new JButton ("Wolf");
        Wolf.setBackground(Color.gray);
        JButton	Turtle= new JButton ("Turtle");
        Turtle.setBackground(Color.BLUE);
        JButton Lion= new JButton ("Lion");
        Lion.setBackground(getWorldMap().lightBrown);
        JButton Unicorn= new JButton ("Unicorn");
        Unicorn.setBackground(getWorldMap().brightPink);
        this.roundLabel = new JLabel("Round: " + world.getRound() + " World: "+ world.getName()); // Inicjalizacja komponentu JLabel
        
        WorldAnimalPanel.add(roundLabel);
        WorldAnimalPanel.add(Grass);
        WorldAnimalPanel.add(Milkweed);
        WorldAnimalPanel.add(Coca);
        WorldAnimalPanel.add(Sheep);
        WorldAnimalPanel.add(Wolf);
        WorldAnimalPanel.add(Turtle);
        WorldAnimalPanel.add(Lion);
        WorldAnimalPanel.add(Unicorn);
        add(WorldAnimalPanel, BorderLayout.NORTH);  //Dodanie komponentu do okna
        
        textArea = new JTextArea(); // Inicjalizacja komponentu JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);     
        // Dostosowanie ustawień dotyczących paska przewijania
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);        
        add(scrollPane, BorderLayout.EAST); // Dodanie komponentu do okna        

        JPanel buttonPanel = new JPanel();
        JButton nextRoundButton = new JButton("Next Round");
        JButton saveButton = new JButton("Save");
        JButton saveAndExitButton = new JButton("Save and Exit");
        JButton exitButton = new JButton("Exit");
        
        buttonPanel.add(nextRoundButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(saveAndExitButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        nextRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	playRound();
            	getWorldMap().setWorldTiles(); 
                
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodaj logikę zapisu stanu gry
                saveGameState();
            }
        });

        saveAndExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dodaj logikę zapisu stanu gry
                saveGameState();
                // Zamknij okno
                main.exitGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zamknij okno
                main.exitGame();
            }
        });

        
        textArea.append("Welcome in Virtual World. You called it: "+ getWorldMap().getWorld().getName()+"Let's start the game!!!\n");
        worldMap.setWorldTiles();
    }

    public void playRound() {
        // Aktualizacja komponentu JLabel z informacją o rundzie
        roundLabel.setText("Round: " + getWorldMap().getWorld().getRound() + " World: "+ getWorldMap().getWorld().getName());
        textArea.append("Runda: " + getWorldMap().getWorld().getRound() + "\n"); // Dodanie tekstu do JTextArea 
        
        //1.Sortowanie i czyszczenie tablicy allOrganisms z "martwych" organizmów.
        int newNumberOfOrganisms=getWorldMap().getWorld().getWorldFullness();
        worldMap.getWorld().setAllOrganisms(worldMap.getWorld().primalSorting(worldMap.getWorld().getAllOrganisms(),newNumberOfOrganisms));
        int alives = getWorldMap().getWorld().liveOrganismCounter( getWorldMap().getWorld().getAllOrganisms(),newNumberOfOrganisms);
        worldMap.getWorld().setAllOrganisms(getWorldMap().getWorld().deadCleaner(getWorldMap().getWorld().getAllOrganisms(),newNumberOfOrganisms));
        int animals = getWorldMap().getWorld().animalCounter(getWorldMap().getWorld().getAllOrganisms(), alives);
        int plants= alives - animals;
        worldMap.getWorld().setWorldFullness(alives);
        worldMap.getWorld().setAnimalFullness(animals);
        worldMap.getWorld().setPlantFullness(plants); 
      
        
        //3. Rozgrywanie tur kolejnych zwierząt i roślin według ustalonych reguł gry ( inicjatywa, wiek,)
        int numberOfOrganisms=getWorldMap().getWorld().getWorldFullness();
        for (int i = 0; i< numberOfOrganisms; i++)
        {
        	Organism org = getWorldMap().getWorld().getOrganism(i);
        	String textOut=getWorldMap().getWorld().organismRound(org);  
        	textArea.append(textOut);
        }
               
        getWorldMap().getWorld().increaseWorldRound();
        
    }
    private void saveGameState() {
        // Dodaj kod zapisu stanu gry
        // To może obejmować zapis aktualnej rundy, stanu mapy, itp.
    	textArea.append("Zapisano stan gry.\n"); // Dodanie tekstu do JTextArea    	
    }

    
    public WorldMap getWorldMap() {return worldMap;}
    public void setWorld(World world) {this.world=world;}
}
