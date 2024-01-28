package VWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random; 

public class WorldMap extends JPanel {

    private JButton[][] buttons; // Tablica przycisków reprezentująca mapę
    private JComboBox<String> organismComboBox;//menu do wyboru organizmu do dodania na dany kafel 
    private int rows;
    private int cols;
    private World world;
    private Map<Integer, Color> organismColors;
    Color sandColor = new Color(255, 255, 153);
    Color brightPink = new Color(255, 0, 127);
    Color darkGreen = new Color(0, 100, 0);
    Color lightBrown = new Color(205, 133, 63);
    
    public WorldMap(World world) {
    	this.rows = world.getWorldY();
        this.cols = world.getWorldX();
        this.world = world;
        buttons = new JButton[rows][cols];

        setLayout(new GridLayout(rows, cols));        
        initializeOrganismColors();
        
        organismComboBox = new JComboBox<>();  // Dodaj inicjalizację pola organismComboBox
        initializeOrganismComboBox(organismComboBox,rows-1,cols-1 );        
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                JButton button = new JButton();
                buttons[j][i] = button;
                add(button); 
                
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Pobierz współrzędne przycisku, który został kliknięty
                        int clickedRow = -1;
                        int clickedCol = -1;
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < cols; j++) 
                            {
                                if (buttons[i][j] == e.getSource()) {
                                    clickedRow = i;
                                    clickedCol = j;
                                    
                                    break;
                                }
                            }
                            if (clickedRow != -1) {
                            break;
                            }
                        }
                        int col=clickedCol;
                        int row=clickedRow;
                     // Utwórz niemodalne okno dialogowe
                        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(WorldMap.this), "Wybierz organizm", true);
                        dialog.setLayout(new BorderLayout());

                        // Dodaj rozwijaną listę z organizmami do okna dialogowego
                        initializeOrganismComboBox(organismComboBox, clickedRow, clickedCol);
                        dialog.add(organismComboBox, BorderLayout.CENTER);

                        // Dodaj przycisk do akceptacji wyboru
                        JButton acceptButton = new JButton("Akceptuj");
                        acceptButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Pobierz wybrany organizm z rozwijanej listy
                                String selectedOrganism = (String) organismComboBox.getSelectedItem();
                                Random randomowa = new Random();
                                int rand = randomowa.nextInt(2);
                                
                                if(selectedOrganism=="Grass"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(0, col , row, sex);
                                }
                                else if (selectedOrganism=="Milkweed"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(1,col,row,sex);
                                }
                                else if(selectedOrganism=="Coca"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(2,col,row,sex);
                                }
                                else if(selectedOrganism=="Sheep"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(3,col,row,sex);
                                }
                                else if(selectedOrganism=="Wolf"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(4,col,row,sex);
                                }
                                else if(selectedOrganism=="Turtle"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(5,col,row,sex);
                                }
                                else if(selectedOrganism=="Lion"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(6,col,row,sex);
                                }
                                
                                else if(selectedOrganism=="Unicorn"){
                                	boolean sex=false;
                                	if(rand==1)
                                	{sex=true;}

                                getWorld().newOrganism(7,col,row,sex);
                                }
                                // Organism newOrganism = ...;

                                // Dodaj organizm do płytki
                                // world.getTile(clickedRow, clickedCol).setPlantOnTile(newOrganism);

                                // Zaktualizuj widok mapy
                                // updateButtonColor(clickedRow, clickedCol, getColorForOrganismType(newOrganism.getTypeOfOrganism()));

                                // Zamknij okno dialogowe po akceptacji
                                setWorldTiles();
                                dialog.dispose();
                            }
                        });
                        dialog.add(acceptButton, BorderLayout.SOUTH);
                        

                        // Ustawienia okna dialogowego
                        dialog.setSize(400, 100);
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    }}
            );
        }
     }
            
        
        
        
        setWorldTiles();
        
    }
    //Hashmapa kolorów
    private void initializeOrganismColors() {
        organismColors = new HashMap<>();
        organismColors.put(0, darkGreen);    
        organismColors.put(1, Color.YELLOW);    
        organismColors.put(2, Color.GREEN);    
        organismColors.put(3, Color.WHITE);   
        organismColors.put(4, Color.GRAY);   
        organismColors.put(5, Color.BLUE);   
        organismColors.put(6, lightBrown);   
        organismColors.put(7, brightPink);   
    }
    
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    public World getWorld(){
    	return world;
    }
    

    // Metoda do aktualizacji koloru przycisku na podstawie zawartości komórki
    public void updateButtonColor(int row, int col, Color color) {
        buttons[row][col].setBackground(color);
    }
 // Dodaj nową metodę do WorldMap, która ustawia płytki na podstawie danych z World
    public void setWorldTiles() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                // Ustaw odpowiedni kolor przycisku na podstawie rodzaju organizmu na płytkach
            	
            	Organism plant = getWorld().getTile(i,j). getPlantOnTile();
        		Organism animal = getWorld().getTile(i,j). getAnimalOnTile();
        		if(plant==null&&animal!=null)
        		{
        			Color animalColor = getColorForOrganismType(animal.typeOfOrganism);        			
        			paintWorld(j, i, animalColor, sandColor);
        		}
        		else if(plant!=null&&animal!=null) 
        		{
            		Color plantColor = getColorForOrganismType(plant.typeOfOrganism);
            		Color animalColor = getColorForOrganismType(animal.typeOfOrganism);
        			paintWorld(j, i, animalColor, plantColor);
            	} 
        		else if (plant!=null&&animal==null)
        		{
        			Color plantColor = getColorForOrganismType(plant.typeOfOrganism);
            		paintWorld(j, i, plantColor, plantColor);
        		}
        		else
        		{
        			paintWorld(j, i, sandColor, sandColor);
        		}
            }
        }
    }
 // Zwraca kolor na podstawie rodzaju organizmu.
    public Color getColorForOrganismType(int typeOfOrganism) {
        return organismColors.get(typeOfOrganism);
    }

    // Metoda do malowania zwierząt
    public void paintWorld(int row, int col, Color animalColor, Color plantColor) {
    	// Ustaw odpowiedni kolor przycisku na podstawie rodzaju organizmu na płytkach
    	buttons[row][col].setBackground(plantColor);
    	// Kod do rysowania kółka po środku przycisku
    	Graphics g = buttons[row][col].getGraphics();
    	if (g != null) {
        g.setColor(animalColor);
        int diameter = Math.min(buttons[row][col].getWidth(), buttons[row][col].getHeight()) / 2;
        int x = (buttons[row][col].getWidth() - diameter) / 2;
        int y = (buttons[row][col].getHeight() - diameter) / 2;
        g.fillOval(x, y, diameter, diameter);
    	}
    }
    protected void paintComponent(Graphics g) {
        setWorldTiles();

    }
    private void initializeOrganismComboBox(JComboBox<String> comboBox, int row, int col) {
        // Tutaj dodaj organizmy do rozwijanego menu, zależnie od warunków
    	// Wyczyść rozwijaną listę przed dodaniem nowych elementów
        organismComboBox.removeAllItems();

        // Pobierz informacje o organizmach na danej płytce
        Organism plant = getWorld().getTile(col, row).getPlantOnTile();
        Organism animal = getWorld().getTile(col, row).getAnimalOnTile();

        // Dodaj organizmy do rozwijanej listy
        if (plant == null&&animal!=null) {
        	organismComboBox.addItem("There is only "+animal.spec+" on Tile. Do you like to add any plant? ");
            organismComboBox.addItem("Plant: ");
            organismComboBox.addItem("Grass");
            organismComboBox.addItem("Milkweed");
            organismComboBox.addItem("Coca");
         // Ustaw pierwszą pozycję jako niewybieralną
            organismComboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index == 0||index == 1) {
                        c.setEnabled(false);
                    }
                    return c;
                }
            });
        }
        else if (animal == null && plant!=null) {
        	organismComboBox.addItem("There is only "+plant.spec+" on Tile. Do you like to add any animal? ");
            organismComboBox.addItem("Animal: ");            
            organismComboBox.addItem("Sheep");
            organismComboBox.addItem("Wolf");
            organismComboBox.addItem("Turtle");
            organismComboBox.addItem("Lion");
            organismComboBox.addItem("Unicorn");
         // Ustaw pierwszą pozycję jako niewybieralną
            organismComboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index == 0||index==1) {
                        c.setEnabled(false);
                    }
                    return c;
                }
            });
        }
        else if (animal == null && plant==null) {
        	organismComboBox.addItem("There is no organism on the Tile.Do you like to add any? ");
        	organismComboBox.addItem("Plant: ");
            organismComboBox.addItem("Grass");
            organismComboBox.addItem("Milkweed");
            organismComboBox.addItem("Coca");
            organismComboBox.addItem("Animal: ");            
            organismComboBox.addItem("Sheep");
            organismComboBox.addItem("Wolf");
            organismComboBox.addItem("Turtle");
            organismComboBox.addItem("Lion");
            organismComboBox.addItem("Unicorn");
         // Ustaw pierwszą pozycję jako niewybieralną
            organismComboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index == 0||index==1||index==5) {
                        c.setEnabled(false);
                    }
                    return c;
                }
            });
        }
        else {organismComboBox.addItem("There is "+animal.spec+" and "+plant.spec +" on Tile.");}
        
    	
        
        // ...
    }
    
    
}