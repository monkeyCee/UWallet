package ca.uwallet.main;



import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
import ca.uwallet.main.provider.WatcardContract;
import ca.uwallet.main.util.ProviderUtils;


/**
 * Fragment used to display statistics
 * @author Andy
 *
 */
public class StatsFragment extends Fragment implements LoaderCallbacks<Cursor>, OnClickListener {
	
	private static final int LOADER_BALANCES_ID = 17;
	private static final int BALANCE_CHART_ID = 123456;
	private GraphicalView dataChart;
	private MultipleCategorySeries series;
	private DefaultRenderer renderer;

	public StatsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_stats, container,
				false);
		v.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {	

				SeriesSelection seriesSelection = dataChart.getCurrentSeriesAndPoint();
				// double[] xy = dataChart.toRealPoint(0);
		        if (seriesSelection == null) {
		            Toast.makeText(getActivity(), "No chart element was clicked", Toast.LENGTH_SHORT)
		                .show();
		          } else {
		            Toast.makeText(
		                    getActivity(),
		                "Chart element in series index " + seriesSelection.getSeriesIndex()
		                    + " data point index " + seriesSelection.getPointIndex() + " was clicked"
		                    + " closest point value X=" + seriesSelection.getXValue() + ", Y=" + seriesSelection.getValue()
		                    /*+ " clicked point value X=" + (float) xy[0] + ", Y=" + (float) xy[1]*/, 500).show();
		            renderer.getSeriesRendererAt(seriesSelection.getPointIndex()).setHighlighted(true);
		            renderer.getSeriesRendererAt(seriesSelection.getPointIndex()).setColor(0xFF888888);
		            dataChart.repaint();
		            
		          }
		        return;
			}
			
		});
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(LOADER_BALANCES_ID, null, this);
	}

	private DefaultRenderer buildRenderer(int[] colors){
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
	        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	        r.setColor(color);
	        renderer.addSeriesRenderer(r);
	    }
		renderer.setBackgroundColor(0xFF000000);
		renderer.setPanEnabled(false);
		renderer.setZoomEnabled(false);
		renderer.setLabelsTextSize(30);
		renderer.setShowLegend(false);
		renderer.setClickEnabled(true);
	    return renderer;
	}
	
	private GraphicalView getBalanceChart(int[] amounts){
		Context context = getActivity();
		series = new MultipleCategorySeries("Balance");
		series.add(new String[] {"Meal Plan",  "Flex Dollars"}, 
				new double[] {/*ProviderUtils.getMealBalance(amounts)*/ 13.2,
				ProviderUtils.getFlexBalance(amounts)});
		
		int[] colors = {0xFF00FF00, 0xFFFFFF00};
		renderer = buildRenderer(colors);
		return ChartFactory.getDoughnutChartView(context, series, renderer);
	}
	
	private void appendView(View v, int id){
		ViewGroup parent = (ViewGroup)getView();
		if (parent != null){
			View old = parent.findViewById(id);
			if (old != null)
				parent.removeView(old);
			if (v != null)
				parent.addView(v);
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		if (id != LOADER_BALANCES_ID)
			return null;
		return new CursorLoader(getActivity(), WatcardContract.Balance.CONTENT_URI, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		int[] amounts = ProviderUtils.getBalanceAmounts(data);
		dataChart= getBalanceChart(amounts);
		appendView(dataChart, BALANCE_CHART_ID);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		appendView(null, BALANCE_CHART_ID);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}
