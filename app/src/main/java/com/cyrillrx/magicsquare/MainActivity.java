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

    private SeekBar seekBar;
    private GridView gridView;
    private TextView tvSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSize = (TextView) findViewById(R.id.tvSize);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setCacheColorHint(getResources().getColor(R.color.grid_color));
        gridView.setBackgroundColor(getResources().getColor(R.color.grid_color));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        final int size = getSquareSize();
        // Magic Square works only with odd sizes
        if (size % 2 == 0) {
            return;
        }

        tvSize.setText(String.valueOf(size));
        gridView.removeAllViewsInLayout();
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

        gridView.setNumColumns(size);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_grid,
                values);
        gridView.setAdapter(adapter);
    }

    private int getSquareSize() { return seekBar.getProgress() + MIN_SQUARE_SIZE; }
}
