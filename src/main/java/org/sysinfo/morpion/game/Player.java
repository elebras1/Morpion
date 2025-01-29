package org.sysinfo.morpion.game;

public class Player {
    private String name;
    private String pion;
    private int nbWin;
    private boolean canPlay;

    public Player (String name, String pion){
        this.name = name;
        this.pion = pion;
        this.nbWin = 0;
    }



}
