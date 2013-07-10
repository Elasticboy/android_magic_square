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
	private GridViewAdapter mGridViewAdapter;
    private TextView mTvSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvSize = (TextView) findViewById(R.id.tvSize);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);

        mGridView = (GridView) findViewById(R.id.gridView);
		mGridView.setAdapter(mGridViewAdapter);
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

        int[] values = magicSquare.build();

        TextView textView = null;
        for (int value : values) {
            textView = new TextView(getApplicationContext());
            textView.setText(String.valueOf(value));
            mGridView.addView(textView);
        }
    }

    private int getSquareSize() {
        return mSeekBar.getProgress() + 3;
    }
	
	public class GridViewAdapter extends BaseAdapter {

		private Context mContext;

		public GridViewAdapter(Context c, int itemCount) {
			mContext = c;
		}

		public int getCount() {
			return items.size();
		}

		public Object getItem(int position) {
			return items.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {         
			View v;         

			if(convertView == null) {
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.grid_item, null);
			} else {             
				v = convertView;         
			}

			TextView tv = (TextView)v.findViewById(R.id.grid_item_text);
			tv.setText(items.get(position));         

			return v;     
		} 

	}
}
