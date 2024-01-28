package VWorld;
//import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;

public class Main extends JFrame {
	
	private GameMenu gameMenu;  
	private GameWindow gameWindow;
	private World world;
	public Main() {
        setTitle("Virtual World");
        setSize(880, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        World world = new World(0,0,"temp");
        setLayout(new BorderLayout());
        gameMenu = new GameMenu(this);
        add(gameMenu, BorderLayout.CENTER);
        setVisible(true);
        gameWindow = new GameWindow(world, gameMenu, this);
    }
	public void startNewGame(int x, int y, String nameOfWorld) {
        this.world = createNewWorld(x, y, nameOfWorld);
        gameMenu.setWorld(world);
        this.gameWindow=new GameWindow(world, gameMenu, this);
        remove(gameMenu);
        add(gameWindow, BorderLayout.CENTER);
        gameWindow.setWorld(world);
        gameWindow.setVisible(true);
        gameMenu.setVisible(false);
        setSize(1000, 750);
        
    }

    public void exitGame() {
        remove(gameWindow);
        add(gameMenu, BorderLayout.CENTER);
        gameMenu.setVisible(true);

        setSize(880, 750); 
        setLocationRelativeTo(null); // Ustaw okno na środku ekranu
    }

    private World createNewWorld(int x, int y, String nameOfWorld) {
        return new World(x, y, nameOfWorld);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}