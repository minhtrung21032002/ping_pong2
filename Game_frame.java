
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Game_frame extends JFrame {// this is a container hold things like paddle or ball ...
    
    Game_panel panel;

    public Game_frame(){
        panel = new Game_panel();

		this.add(panel);

		this.setTitle("Pong Game");

		this.setResizable(false);

		this.setBackground(Color.black);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();

		this.setVisible(true);

		this.setLocationRelativeTo(null);

	}


    }

    
    