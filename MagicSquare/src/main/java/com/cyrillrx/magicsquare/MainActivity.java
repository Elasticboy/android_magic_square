package com.cyrillrx.magicsquare;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

    private static final int MIN_SQUARE_SIZE = 3;
    private SeekBar mSeekBar;
    private GridView mGridView;
    private TextView mTvSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvSize = (TextView) findViewById(R.id.tvSize);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setCacheColorHint(getResources().getColor(R.color.grid_color));
        mGridView.setBackgroundColor(getResources().getColor(R.color.grid_color));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        final int size = getSquareSize();
        // Magic Square works only with odd sizes
        if (size % 2 == 0) {
            return;
        }

        mTvSize.setText(String.valueOf(size));
        mGridView.removeAllViewsInLayout();
        drawMagicSquare(size);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    private void drawMagicSquare(int size) {

        final Random rand = new Random();
        final Point startPosition = new Point(rand.nextInt(size), rand.nextInt(size));
        final MagicSquare magicSquare = new MagicSquare(size, startPosition);

        final String[] values = magicSquare.build();

        mGridView.setNumColumns(size);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_grid,
                values);
        mGridView.setAdapter(adapter);
    }

    private int getSquareSize() {
        return mSeekBar.getProgress() + MIN_SQUARE_SIZE;
    }
}
