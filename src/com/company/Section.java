package com.company;

public class Section {

    private String name;
    private int x;
    private int y;

    public Section(String name) {
        this.name = name;
        this.x = (int) (Math.random() *  10);
        this.y = (int) (Math.random() *  10);
    }

//    public Section(String name, int x, int y) {
//        this.name = name;
//        this.x = x;
//        this.y = y;
//    }

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