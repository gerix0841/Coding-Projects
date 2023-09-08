package model;

public enum LevelItem {
    EXIT('$'), DRAGON('*'), WALL('#'), EMPTY(' '), DRAGON_OUTSIDE('*');
    LevelItem(char rep){ representation = rep; }
    public final char representation;
}
