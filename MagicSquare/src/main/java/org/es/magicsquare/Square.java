package org.es.magicsquare;

import android.graphics.Point;

/**
 * Created by Cyril on 06/07/13.
 */
public class Square {

    private int mSize;
    private Point mStartPosition;
    private Point mCurrentPosition;
    private int[] mValues;
    private int mCurrentValue;

    public Square(int size, Point startPosition) {
        mSize = size;
        mStartPosition = startPosition;
        mValues = new int[size*size];
        mCurrentValue = 0;
    }

    public int[] build() {
        Point currentPosition = mStartPosition;
        addValue(currentPosition);

        while (mCurrentValue <= mSize*mSize) {
            currentPosition = getNextPosition();
            addValue(currentPosition);
            mCurrentValue++;
        }
        return mValues;
    }

    private void addValue(Point position) {
        final int id = getId(position, mSize);
        mValues[id] = mCurrentValue;
    }

    private static int getId(Point position, int size) {
        return position.y * size + position.x;
    }

    private Point getNextPosition() {
        return null;
    }

    public int getSize() {
        return mSize;
    }


}
