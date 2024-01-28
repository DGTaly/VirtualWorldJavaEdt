package VWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class GameMenu extends JPanel {

    private Main main;
    private World world;
    private JTextField dimensionXField;
    private JTextField dimensionYField;
    private JTextField nameField;
    private JTextField fileNameField;
    
    public GameMenu(Main main) {
        this.main = main;
        
        initComponents();
    }

    
private void initComponents() {
    	
        setLayout(new BorderLayout());

         //Dodanie obrazka
        try {
            File imageFile = new File("C:\\Users\\grabo\\eclipse-workspace\\VWorld\\src\\VWorld\\MenuBackground.jpg");
            Image backgroundImage = ImageIO.read(imageFile);
            ImagePanel imagePanel = new ImagePanel(backgroundImage);
            add(imagePanel, BorderLayout.CENTER);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        	//Boczny Panel Menu
            JPanel inputPanel = new JPanel(new GridLayout(15, 1));
            JButton newGameButton = new JButton("New Game");             
            JLabel dimensionXLabel = new JLabel("Enter X dimension:");
            dimensionXField = new JTextField();
            dimensionXField.setEditable(false);
            JLabel dimensionYLabel = new JLabel("Enter Y dimension:");
            dimensionYField = new JTextField();
            dimensionYField.setEditable(false);
            JLabel nameLabel = new JLabel("Enter the name of the world:");
            nameField = new JTextField();
            nameField.setEditable(false);
            JButton startButton = new JButton("Start!");
            startButton.setEnabled(false);
            JButton returnButton = new JButton("Return to Main");
            returnButton.setEnabled(false);
            JButton loadGameButton = new JButton("Load Game");
            JLabel fileNameLabel = new JLabel("Enter the file name to read:");
            fileNameField = new JTextField();
            fileNameField.setEditable(false);  // Ustaw pole jako nieedytowalne
            JButton reloadButton = new JButton("Reload GameSave");
            JButton exitButton = new JButton("Exit");;
            
            
            
            
            
            inputPanel.add(newGameButton);
            inputPanel.add(dimensionXLabel);
            inputPanel.add(dimensionXField);
            inputPanel.add(dimensionYLabel);
            inputPanel.add(dimensionYField);
            inputPanel.add(nameLabel);
            inputPanel.add(nameField);
            inputPanel.add(startButton);
            inputPanel.add(returnButton);
            inputPanel.add(loadGameButton);            
            inputPanel.add(fileNameLabel);
            inputPanel.add(fileNameField);
            inputPanel.add(reloadButton);
            inputPanel.add(exitButton);
            
            add(inputPanel, BorderLayout.WEST);

        newGameButton.addActionListener(new ActionListener(){
        	@Override
            public void actionPerformed(ActionEvent e) {
        		dimensionXField.setEditable(true);
        		dimensionYField.setEditable(true);
        		nameField.setEditable(true);
        		startButton.setEnabled(true);
        		returnButton.setEnabled(true);
        		newGameButton.setEnabled(false);
        		loadGameButton.setEnabled(false);
        		exitButton.setEnabled(false);
            }
        });
        startButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                // Handle a new game
                handleNewGame();
                
            }
        });
        returnButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		dimensionXField.setEditable(false);
        		dimensionYField.setEditable(false);
        		nameField.setEditable(false);
        		startButton.setEnabled(false);
        		returnButton.setEnabled(false);
        		newGameButton.setEnabled(true);
        		loadGameButton.setEnabled(true);
        		exitButton.setEnabled(true);
        		reloadButton.setEnabled(false);
        		fileNameField.setEditable(false);
        		
            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	returnButton.setEnabled(true);
            	newGameButton.setEnabled(false);
        		loadGameButton.setEnabled(false);
        		exitButton.setEnabled(false);
        		reloadButton.setEnabled(true);
        		fileNameField.setEditable(true);
        		
            }
        });
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obsługa wczytywania gry
                System.out.println("Loading the game!");
            }
        });
        

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obsługa wyjścia
                System.exit(0);
            }
        });

        JPanel GameInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel AuthorLabel = new JLabel("Author: Damian Grabowski, All Rights Reserved");
        GameInfoPanel.add(AuthorLabel);
        add(GameInfoPanel, BorderLayout.SOUTH);
    }
    private void handleNewGame() {
        // Get user input from text fields
        int x = Integer.parseInt(dimensionXField.getText());
        int y = Integer.parseInt(dimensionYField.getText());
        String nameOfWorld = nameField.getText();
        // Perform actions with the obtained data
        main.startNewGame(x, y, nameOfWorld);
        
    }
    // Metoda ustawiająca referencję do World
    public void setWorld(World world) {
        this.world = world;
    }
}

class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}