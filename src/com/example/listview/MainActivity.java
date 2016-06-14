package com.example.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ListView list;
	List<String> l;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.list);
		l = new ArrayList<String>();
		set();
		list.setAdapter(new Adapter());
	}

	private void set() {
		for (int i = 0; i < 50; i++) {
			l.add("---" + i + "---");
		}
	}

	class Adapter extends BaseAdapter {
		LayoutInflater inflater;
		int i = -1;
		View view = null;

		Animation in;
		Animation out;

		Adapter() {
			inflater = LayoutInflater.from(MainActivity.this);
			in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right);
			out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_right);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return l.size();
		}

		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return l.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item, null);
			}
			TextView tv = (TextView) convertView.findViewById(R.id.tv);

			final View vi = convertView.findViewById(R.id.vie);
			Log.e("", vi.toString() + "---");
			if (i == position) {
				tv.setTextColor(Color.RED);
				vi.setVisibility(View.VISIBLE);
			} else {
				tv.setTextColor(Color.BLACK);
				vi.setVisibility(View.GONE);
			}
			tv.setText(getItem(position));
			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (view != null && i != position) {
						if (view.getVisibility() == View.VISIBLE) {
							view.setAnimation(out);
							view.startAnimation(out);
							view.setVisibility(View.GONE);
						}
					}
					i = position;
					view = vi;
					if (vi.getVisibility() == View.GONE) {
						vi.setAnimation(in);
						vi.startAnimation(in);
						vi.setVisibility(View.VISIBLE);
					}

					notifyDataSetChanged();
				}
			});

			return convertView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
