package org.sysinfo.morpion.game;

public class Player {
    private int id;
    private String name;
    private String pawn;
    private int nbWin;
    private boolean canPlay;

    public Player (int id, String name, String pawn){
        this.id = id;
        this.name = name;
        this.pawn = pawn;
        this.nbWin = 0;
    }

    public String getPawn() {
        return pawn;
    }

    public int getNbWin() {
        return nbWin;
    }

    public void setNbWin(int nbWin) {
        this.nbWin = nbWin;
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }
}
