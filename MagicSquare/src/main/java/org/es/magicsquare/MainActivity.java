package org.es.magicsquare;

import android.graphics.Point;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

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
        mGridView.setCacheColorHint(R.color.grid_color);
        mGridView.setBackgroundColor(getResources().getColor(R.color.grid_color));
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

        mTvSize.setText(String.valueOf(size));
        mGridView.removeAllViewsInLayout();
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

        String[] values = magicSquare.build();

        mGridView.setNumColumns(size);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                values);
        mGridView.setAdapter(adapter);
    }

    private int getSquareSize() {
        return mSeekBar.getProgress() + 3;
    }
}
