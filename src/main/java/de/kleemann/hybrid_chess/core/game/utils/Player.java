package de.kleemann.hybrid_chess.core.game.utils;

public class Player {

    private String name;
    private Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Player{\nname: " + name + ",\ncolor: " + color + "\n}";
    }
}
