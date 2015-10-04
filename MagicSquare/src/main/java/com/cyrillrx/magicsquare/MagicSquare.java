package com.cyrillrx.magicsquare;

import android.graphics.Point;

/**
 * @author Cyril Leroux
 *         Created on 06/07/13.
 */
public class MagicSquare {

    private static final String EMPTY = "";

    private int mSize;
    private Point mStartPosition;
    private Point mCurrentPos;
    private String[] mValues;
    private int mCurrentValue;

    public MagicSquare(int size, Point startPosition) {
        mSize = size;

        mStartPosition = startPosition;
        mStartPosition.x = getValueInBound(startPosition.x);
        mStartPosition.y = getValueInBound(startPosition.y);

        final int itemCount = size * size;
        mValues = new String[itemCount];
        for (int i = 0; i < itemCount; i++) {
            mValues[i] = EMPTY;
        }
        mCurrentValue = 1;
    }

    public String[] build() {
        mCurrentPos = mStartPosition;
        addValue(mCurrentPos, mCurrentValue);

        while (mCurrentValue < mSize * mSize) {
            mCurrentPos = getNextPosition();
            addValue(mCurrentPos, ++mCurrentValue);
        }
        return mValues;
    }

    private void addValue(Point position, int value) {
        final int id = getId(position.x, position.y, mSize);
        mValues[id] = String.valueOf(value);
    }

    private static int getId(int x, int y, int size) {
        return y * size + x;
    }

    private Point getNextPosition() {
        int newX = getValueInBound(mCurrentPos.x + 1);
        int newY = getValueInBound(mCurrentPos.y + 1);

        while (!EMPTY.equals(mValues[getId(newX, newY, mSize)])) {
            newX = getValueInBound(mCurrentPos.x);
            newY = getValueInBound(mCurrentPos.y + 2);
        }
        mCurrentPos.x = newX;
        mCurrentPos.y = newY;

        return mCurrentPos;
    }

    public int getSize() { return mSize; }

    private int getValueInBound(int value) {
        if (value >= 0 && value < mSize) {
            return value;
        }

        return (value + 1) % mSize - 1;
    }

}
