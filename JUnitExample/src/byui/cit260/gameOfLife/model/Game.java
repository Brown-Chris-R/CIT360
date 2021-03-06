/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.gameOfLife.model;

import java.io.Serializable;

/**
 *
 * @author cbrown
 */
public class Game implements Serializable {

    //class instance variables    
    private int totalTurns;
    private int score;
    private Player player;
    private Map map;
    private Item [] items;
    private Character [] characters;
    private String phase;

    public Game() {
    }

    public int getTotalTurns() {
        return totalTurns;
    }

    public void setTotalTurns(int totalTurns) {
        this.totalTurns = totalTurns;
    }
    
    public int getScore() {
        
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int addToScore(int points) {
        this.score += points;
        return score;
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
    
    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public String getPhase() {
        return phase;
    }

    public void nextPhase(String phase) {
        if (phase == null) {
            phase = Phase.Childhood.toString();
        } else {
            switch (phase) {
                case "Childhood":
                    phase = Phase.Adolescence.toString();
                    break;
                case "Adolescence":
                    phase = Phase.Adulthood.toString();
                    break;
                case "Adulthood":
                    phase = Phase.Senior.toString();
                    break;
            }
        }
        this.phase = phase;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.totalTurns;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.totalTurns != other.totalTurns) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{score=" + score + ", totalTurns=" + totalTurns + ", phase=" + phase + '}';
    }
}
