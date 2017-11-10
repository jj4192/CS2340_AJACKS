package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;

/**
 * Displays a histogram about the number of rat sightings per month.
 */
public class GraphScreen extends AppCompatActivity {

    private final Model model = Model.getInstance();

    //All of the UI elements
    private Spinner startMonthSpinner;
    private Spinner startYearSpinner;
    private Spinner endMonthSpinner;
    private Spinner endYearSpinner;
    private BarChart chart;

    //Spinner values
    private final Integer[] MONTHS
            = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private final Integer[] YEARS = new Integer[]{
            2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);

        startMonthSpinner = (Spinner) findViewById(R.id.spinner_start_month);
        ArrayAdapter<Integer> startMonthAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, MONTHS);
        startMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMonthSpinner.setAdapter(startMonthAdapter);
        startMonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateGraph();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        startYearSpinner = (Spinner) findViewById(R.id.spinner_start_year);
        ArrayAdapter<Integer> startYearAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, YEARS);
        startYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startYearSpinner.setAdapter(startYearAdapter);
        startYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateGraph();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        endMonthSpinner = (Spinner) findViewById(R.id.spinner_end_month);
        ArrayAdapter<Integer> endMonthAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, MONTHS);
        endMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endMonthSpinner.setAdapter(endMonthAdapter);
        endMonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateGraph();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        endYearSpinner = (Spinner) findViewById(R.id.spinner_end_year);
        ArrayAdapter<Integer> endYearAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, YEARS);
        endYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endYearSpinner.setAdapter(endYearAdapter);
        endYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateGraph();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        chart = (BarChart) findViewById(R.id.chart);

        updateGraph();
    }

    /**
     * Update the graph based on the new date range
     */
    private void updateGraph() {
        final int startMonth = MONTHS[startMonthSpinner.getSelectedItemPosition()];
        final int startYear = YEARS[startYearSpinner.getSelectedItemPosition()];
        int endMonth = MONTHS[endMonthSpinner.getSelectedItemPosition()];
        int endYear = YEARS[endYearSpinner.getSelectedItemPosition()];

        int dateDistance = dateDistance(startMonth, startYear, endMonth, endYear) + 1;

        if (dateDistance <= 0) {
            return;
        }

        Integer[] labels = new Integer[dateDistance];
        Integer[] values = new Integer[dateDistance];

        for (int i = 1; i <= dateDistance; i++) {
            labels[i - 1] = i;
            values[i - 1] = 0;
        }

        try {
            List<RatSighting> filteredSightings = model.filterByDateAndTime(startMonth + "/01/" + startYear, endMonth + "/31/" + endYear);
            for (RatSighting sighting: filteredSightings) {
                String dateAndTime = sighting.getDateAndTime();
                String dateString = dateAndTime.split(" ")[0];
                DateFormat endDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = endDateFormat.parse(dateString);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH) + 1;
                int year = cal.get(Calendar.YEAR);
                int distanceToSighting = dateDistance(startMonth, startYear, month, year);
                values[distanceToSighting] = values[distanceToSighting] + 1;
            }

            Log.d("Graph",Arrays.toString(values));

            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int i = 0; i < values.length; i++) {
                entries.add(new BarEntry(i, values[i]));
            }

            BarDataSet dataSet = new BarDataSet(entries, "Sightings per month");
            dataSet.setColor(Color.RED);
            dataSet.setValueTextColor(Color.BLACK);

            XAxis xAxis = chart.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return getMonthYearByDistance(startMonth, startYear, (int)value);
                }
            });

            BarData barData = new BarData(dataSet);
            chart.setData(barData);
            chart.setFitBars(true);
            chart.invalidate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the number of months between two month/year dates
     */
    public static int dateDistance(int startMonth, int startYear, int endMonth, int endYear) {
        int deltaYear = endYear - startYear;
        int deltaMonth = endMonth - startMonth;
        return (deltaYear * 12) + deltaMonth;
    }

    /**
     * Creates a month/year string based on how many months after the start date it is
     */
    public static String getMonthYearByDistance(int startMonth, int startYear, int distance) {
        int month = (((startMonth + distance) - 1) % 12) + 1;
        int year = startYear + (((startMonth + distance) - 1) / 12);
        return month + "/" + year;
    }
}
