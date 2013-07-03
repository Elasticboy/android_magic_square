package org.es.magicsquare;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

    private SeekBar mSeekBar;
    private GridView mGridView;
    private TextView[][] mCells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        drawMagicSquare(getSquareSize());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void drawMagicSquare(int size) {
        mCells = new TextView[size][size];
        mGridView.setNumColumns(size);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                mGridView.addView(mCells[x][y]);
            }
        }
    }

    private void fillMagicSquare() {

    }

    private int getSquareSize() {
        return mSeekBar.getProgress() + 3;
    }

    //private TextView getCell(int id, int width) {
        //if ()
    //}
}
