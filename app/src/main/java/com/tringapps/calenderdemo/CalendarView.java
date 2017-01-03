package com.tringapps.calenderdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.os.Build;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by geethu on 20/12/16.
 */

public class CalendarView extends LinearLayout implements View.OnClickListener {

    private final String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    private int current_year;
    private int current_month;
    private int current_day;
    private int first_day;
    private int total_number_of_days;

    private Calendar cal;
    private Calendar cal1,cal2;
    private TextView monthView;
    private Button nextView;
    private Button previousView;
    private RecyclerView weekView;
    private RecyclerView dateView;
    private DateAdapter adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.calender,this);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        monthView = (TextView) findViewById(R.id.month);
        previousView = (Button) findViewById(R.id.previous);
        nextView = (Button) findViewById(R.id.next);
        weekView = (RecyclerView) findViewById(R.id.recyclerweek);
        dateView = (RecyclerView) findViewById(R.id.recyclerday);
        monthView = (TextView) findViewById(R.id.month);

        previousView.setOnClickListener(this);
        nextView.setOnClickListener(this);
        monthView.setOnClickListener(this);


        cal = Calendar.getInstance();
        cal1 = Calendar.getInstance();
        getCalendar();
        setWeekAdapter();
        getDatas();



    }

    private void getDatas() {

        cal1.set(current_year,current_month,1);
        first_day = cal1.get(Calendar.DAY_OF_WEEK);
        total_number_of_days = cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
        recyclerViewLayoutManager = new GridLayoutManager(getContext(), 7);
        dateView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new DateAdapter(total_number_of_days,first_day,current_day);
        dateView.setAdapter(adapter);


    }

    private void setWeekAdapter() {

        recyclerViewLayoutManager = new GridLayoutManager(getContext(), 7);
        weekView.setLayoutManager(recyclerViewLayoutManager);
        weekView.setAdapter(new WeekAdapter(days));

    }


    @TargetApi(Build.VERSION_CODES.N)
    private void getCalendar( ) {
        current_year = cal.get(Calendar.YEAR);
        current_month = cal.get(Calendar.MONTH);
        current_day = cal.get(Calendar.DATE);
        setMonth(current_month);


    }

    private void setMonth(int current_month) {

        current_month = current_month + 1;
        switch (current_month) {
            case 1:
                monthView.setText(getContext().getString(R.string.january, current_year));
                break;
            case 2:
                monthView.setText(getContext().getString(R.string.february,current_year));
                break;
            case 3:
                monthView.setText(getContext().getString(R.string.march,current_year));
                break;
            case 4:
                monthView.setText(getContext().getString(R.string.april,current_year));
                break;
            case 5:
                monthView.setText(getContext().getString(R.string.may,current_year));
                break;
            case 6:
                monthView.setText(getContext().getString(R.string.june,current_year));
                break;
            case 7:
                monthView.setText(getContext().getString(R.string.july,current_year));
                break;
            case 8:
                monthView.setText(getContext().getString(R.string.august,current_year));
                break;
            case 9:
                monthView.setText(getContext().getString(R.string.september,current_year));
                break;
            case 10:
                monthView.setText(getContext().getString(R.string.october,current_year));
                break;
            case 11:
                monthView.setText(getContext().getString(R.string.november,current_year));
                break;
            case 12:
                monthView.setText(getContext().getString(R.string.december,current_year));

        }
        current_month = current_month - 1;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.previous:
                getCalendarAttr(-1);
                break;

            case R.id.next:
                getCalendarAttr(1);
                break;
            case R.id.month:
                showDialog();
        }
    }

    private void showDialog() {

            final String items[] = new String[21];
            int j = current_year - 10;

            for(int i=0;i<21;i++,j++)
            {
                items[i] = String.valueOf(j);
            }

            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
             builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    current_year = Integer.parseInt(items[i]);
                    setMonth(current_month);
                    getDatas();
                    setAgain();

                }
            });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(200, 600);

    }
    private void setAgain()
    {
        adapter.resetAdapter(total_number_of_days,first_day,current_day);
        adapter.notifyDataSetChanged();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void getCalendarAttr(int i) {

        current_month = current_month + i;
        if(current_month == 12)
        {
            current_year = current_year + 1;
            current_month = 0;
            setMonth(current_month);
            getDatas();
            setAgain();



        }else if(current_month == -1)
        {
            current_year = current_year - 1;
            current_month = 11;
            setMonth(current_month);
            getDatas();
            setAgain();


        }else
        {
            setMonth(current_month);
            getDatas();
            setAgain();

        }
    }
}
