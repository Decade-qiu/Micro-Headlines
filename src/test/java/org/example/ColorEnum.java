package org.example;

public enum ColorEnum {
    RED("红色", 1),
    GREEN("绿色", 2),
    BLUE("蓝色", 3);

    private String name;
    private int index;

    ColorEnum(String color, int index) {
        this.name = color;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
