package com.cyrillrx.magicsquare;

import android.graphics.Point;

/**
 * @author Cyril Leroux
 *         Created on 06/07/13.
 */
public class MagicSquare {

    private static final String EMPTY = "";

    private int size;
    private Point startPosition;
    private Point currentPos;
    private String[] values;
    private int currentValue;

    public MagicSquare(int size, Point startPosition) {
        this.size = size;

        this.startPosition = startPosition;
        this.startPosition.x = getValueInBound(startPosition.x);
        this.startPosition.y = getValueInBound(startPosition.y);

        final int itemCount = size * size;
        values = new String[itemCount];
        for (int i = 0; i < itemCount; i++) {
            values[i] = EMPTY;
        }
        currentValue = 1;
    }

    public String[] build() {
        currentPos = startPosition;
        addValue(currentPos, currentValue);

        while (currentValue < size * size) {
            currentPos = getNextPosition();
            addValue(currentPos, ++currentValue);
        }
        return values;
    }

    private void addValue(Point position, int value) {
        final int id = getId(position.x, position.y, size);
        values[id] = String.valueOf(value);
    }

    private static int getId(int x, int y, int size) { return y * size + x; }

    private Point getNextPosition() {
        int newX = getValueInBound(currentPos.x + 1);
        int newY = getValueInBound(currentPos.y + 1);

        while (!EMPTY.equals(values[getId(newX, newY, size)])) {
            newX = getValueInBound(currentPos.x);
            newY = getValueInBound(currentPos.y + 2);
        }
        currentPos.x = newX;
        currentPos.y = newY;

        return currentPos;
    }

    private int getValueInBound(int value) {
        if (value >= 0 && value < size) {
            return value;
        }

        return (value + 1) % size - 1;
    }
}
