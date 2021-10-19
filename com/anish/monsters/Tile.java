package com.anish.monsters;

public class Tile<T extends Thing> {

    private T thing;
    private int xPos;
    private int yPos;

    public T getThing() {
        return thing;
    }

    public void setThing(T thing) {
        this.thing = thing;
        this.thing.setTile(this);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Tile() {
    //构造器
        this.xPos = -1;
        this.yPos = -1;
    }

    public Tile(int xPos, int yPos) {
    //构造器
        this.xPos = xPos;
        this.yPos = yPos;
    }

}