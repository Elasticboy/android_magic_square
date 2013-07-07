package org.es.magicsquare;

import android.graphics.Point;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

    private SeekBar mSeekBar;
    private GridView mGridView;
    private TextView[] mCells;
    private int mCurrentX;
    private int mCurrentY;
    private int mCurrentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setCacheColorHint(R.color.grid_color);
        mGridView.setBackgroundColor(getResources().getColor(R.color.grid_color));

        mCurrentX = 0;
        mCurrentY = 0;
        mCurrentValue = 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        final int size = getSquareSize();
        // Magic Square works only with odd sizes
        if (size % 2 == 0) {
            return;
        }

        mGridView.removeAllViews();
        drawMagicSquare(size);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    private void drawMagicSquare(int size) {

        Random rand = new Random();
        Point startPosition = new Point(rand.nextInt(size), rand.nextInt(size));
        MagicSquare magicSquare = new MagicSquare(size, startPosition);

        int[] values = magicSquare.build();

        TextView textView = null;
        for (int value : values) {
            textView = new TextView(getApplicationContext());
            textView.setText(value);
            mGridView.addView(textView);
        }
    }

    private int getSquareSize() {
        return mSeekBar.getProgress() + 3;
    }
}
