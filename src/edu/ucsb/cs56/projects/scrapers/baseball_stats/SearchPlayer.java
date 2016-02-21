package edu.ucsb.cs56.projects.scrapers.baseball_stats;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** SearchPlayer is a class that will implement JFrame and Jtextfield to search for a player in the players list.  It will save the players index value.*/

public class SearchPlayer extends JPanel
			      //JFrame
{
    private JPanel panel;
    private String name;
    private ArrayList<Player> searchResults;
    private boolean playerFound;

    private JTextField textField;
    

    public SearchPlayer(StatKeeper stats){
	panel = new JPanel();
	
	JTextField textField = new JTextField(25);
	textField.setEditable(true);
	textField.requestFocus();
	
	textField.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
		   
		    setName(textField.getText() );
		    repaint();
		    searchForPlayer( getName(), stats);
		    if(!playerFound){
		       	fuzzySearch(getName(), stats);
		    }
		}
	    });
	playerFound = false;
	JLabel label1 = new JLabel("Search for a Player: ");
	panel.add(label1);
	textField.setVisible(true);
	panel.add(textField);
    
    }

    public void searchForPlayer(String playerName, StatKeeper statistics){

        searchResults = new ArrayList<Player>();
	Player playerToFind = new Player(0, playerName + " ");
	    
	String  curPlayerFirst;
	String  curPlayerLast;
	String  curPlayerFullName;

	String playerToFindFirst = playerToFind.getFirstName();
	String playerToFindLast = playerToFind.getLastName();
	String playerToFindFull = playerToFind.getFullName();
	
	System.out.println(playerName);
	playerName = playerName;
	for(int i = 0; i< statistics.getPlayerCount(); i++){

	    Player curPlayer = new Player(statistics.getPlayer(i).getID(), statistics.getPlayer(i).getFullName()); 
	    
	    curPlayerFirst = curPlayer.getFirstName();
	    curPlayerLast = curPlayer.getLastName();
	    curPlayerFullName = statistics.getPlayerName(i).trim();
	  
		    
	    if((playerName.equalsIgnoreCase(curPlayerFirst)
		||playerName.equalsIgnoreCase(curPlayerLast))
	        ||(playerName.equalsIgnoreCase(curPlayerFullName)))
		{
		 playerFound = true;

		searchResults.add(curPlayer);
		System.out.println(playerToFindFirst +" " + playerToFindLast + " == " + curPlayerFullName +", "+ i);
       
	     }else{
	   
	    	System.out.println("|"+playerToFindFirst + " " + playerToFindLast  + "|" + curPlayerFirst + " "+ curPlayerLast + "|");
	    }
	}
    }

    public void fuzzySearch(String playerName, StatKeeper stats){}

    public ArrayList<Player> getSearchResults(){
	return searchResults;
	}

    public void setName(String name){
	this.name = name;
    }

    public String getName(){
	return name;
    }

     public JPanel getPanel(){
	 return panel;
     }

}
