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
        final int itemCount = size*size;
        mValues = new int[itemCount];
        for (int i = 0; i < itemCount; i++) {
            mValues[i] = EMPTY;
        }
        mCurrentValue = 0;
    }

    public int[] build() {
        mCurrentPosition = mStartPosition;
        addValue(mCurrentPosition, mCurrentValue);

        while (mCurrentValue <= mSize*mSize) {
            mCurrentPosition = getNextPosition();
            addValue(mCurrentPosition, mCurrentValue);
            mCurrentValue++;
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
        int newX = (mCurrentPosition.x + 1) % mSize - 1;
        int newY = (mCurrentPosition.y + 1) % mSize - 1;

        while (mValues[getId(newX, newY, mSize)] != EMPTY) {
            newY = (mCurrentPosition.y + 2) % mSize - 1;
        }
        mCurrentPosition.x = newX;
        mCurrentPosition.y = newY;

        return mCurrentPosition;
    }

    public int getSize() {
        return mSize;
    }


}
