package com.company;

public class Section {

    private String name;
    private int x;
    private int y;

    public Section(String name) {
        this.name = name;
        this.x = (int) (Math.random() *  100);
        this.y = (int) (Math.random() *  100);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return getName()+" ("+getX() + ", " + getY()+")";
    }

}