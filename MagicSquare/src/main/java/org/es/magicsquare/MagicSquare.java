package org.es.magicsquare;

import android.graphics.Point;

/**
 * Created by Cyril on 06/07/13.
 */
public class MagicSquare {

    private static final int EMPTY = -1;

    private int mSize;
    private Point mStartPosition;
    private Point mCurrentPosition;
    private int[] mValues;
    private int mCurrentValue;

    public MagicSquare(int size, Point startPosition) {
        mSize = size;

        mStartPosition = startPosition;
        mStartPosition.x  = getValueInBound(startPosition.x);
        mStartPosition.y  = getValueInBound(startPosition.y);

        final int itemCount = size*size;
        mValues = new int[itemCount];
        for (int i = 0; i < itemCount; i++) {
            mValues[i] = EMPTY;
        }
        mCurrentValue = 1;
    }

    public int[] build() {
        mCurrentPosition = mStartPosition;
        addValue(mCurrentPosition, mCurrentValue);

        while (mCurrentValue < mSize*mSize) {
            mCurrentPosition = getNextPosition();
            addValue(mCurrentPosition, ++mCurrentValue);
        }
        return mValues;
    }

    private void addValue(Point position, int value) {
        final int id = getId(position.x, position.y, mSize);
        mValues[id] = value;
    }

    private static int getId(int x, int y, int size) {
        return y * size + x;
    }

    private Point getNextPosition() {
        int newX = getValueInBound(mCurrentPosition.x + 1);
        int newY = getValueInBound(mCurrentPosition.y + 1);

        while (mValues[getId(newX, newY, mSize)] != EMPTY) {
            newY = getValueInBound(mCurrentPosition.y + 2);
        }
        mCurrentPosition.x = newX;
        mCurrentPosition.y = newY;

        return mCurrentPosition;
    }

    public int getSize() {
        return mSize;
    }

    private int getValueInBound(int value) {
        if (value >= 0 && value < mSize) {
            return value;
        }

        return (value + 1) % mSize - 1;
    }

}
