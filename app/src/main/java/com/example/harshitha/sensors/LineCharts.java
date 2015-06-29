package com.example.harshitha.sensors;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class LineCharts {

    private GraphicalView view;



    private TimeSeries xSeries = new TimeSeries("X");
    private TimeSeries ySeries = new TimeSeries("Y");
    private TimeSeries zSeries = new TimeSeries("Z");

    private XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

    XYSeriesRenderer xRenderer = new XYSeriesRenderer();
    XYSeriesRenderer yRenderer = new XYSeriesRenderer();
    XYSeriesRenderer zRenderer = new XYSeriesRenderer();

    XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

    public LineCharts() {


        dataset.addSeries(xSeries);
        dataset.addSeries(ySeries);
        dataset.addSeries(zSeries);


        xRenderer.setColor(Color.RED);
        xRenderer.setPointStyle(PointStyle.CIRCLE);
        xRenderer.setFillPoints(true);
        xRenderer.setLineWidth(1);
        xRenderer.setDisplayChartValues(false);


        yRenderer.setColor(Color.GREEN);
        yRenderer.setPointStyle(PointStyle.CIRCLE);
        yRenderer.setFillPoints(true);
        yRenderer.setLineWidth(1);
        yRenderer.setDisplayChartValues(false);


        zRenderer.setColor(Color.BLUE);
        zRenderer.setPointStyle(PointStyle.CIRCLE);
        zRenderer.setFillPoints(true);
        zRenderer.setLineWidth(1);
        zRenderer.setDisplayChartValues(false);


        multiRenderer.setXLabels(0);
        multiRenderer.setLabelsColor(Color.RED);
        multiRenderer.setChartTitle("t vs (x,y,z)");
        multiRenderer.setXTitle("Sensor Data");
        multiRenderer.setYTitle("Values of Acceleration");
        multiRenderer.setZoomButtonsVisible(true);

        multiRenderer.addSeriesRenderer(xRenderer);
        multiRenderer.addSeriesRenderer(yRenderer);
        multiRenderer.addSeriesRenderer(zRenderer);
    }

    public GraphicalView getView(Context context)
    {
        view =  ChartFactory.getLineChartView(context, dataset, multiRenderer);
        return view;
    }

    public void addNewData(AccelData data, long t)
    {
        xSeries.add(data.getTimestamp()-t, data.getX());
        ySeries.add(data.getTimestamp()-t, data.getY());
        zSeries.add(data.getTimestamp()-t, data.getZ());

    }
}
