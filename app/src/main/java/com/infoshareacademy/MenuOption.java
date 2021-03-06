package com.infoshareacademy;

public class MenuOption {

    private String displayedText;
    private int position;
    private int parent;

    public MenuOption(String displayedText, int position, int parent) {
        this.displayedText = displayedText;
        this.position = position;
        this.parent = parent;

    }

    public int getParent() {
        return parent;
    }

    public int getPosition() {
        return position;
    }

    public String getDisplayedText() {
        return displayedText;
    }

    @Override
    public String toString() {
        return "MenuOptions{" +
                "displayedText='" + displayedText + '\'' +
                ", position=" + position +
                ", parent=" + parent +
                '}';
    }
}


