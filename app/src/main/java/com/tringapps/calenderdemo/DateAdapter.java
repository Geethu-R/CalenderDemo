package com.tringapps.calenderdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by geethu on 20/12/16.
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder>{

    private int total,start,current,dateref;
    private View fakeview;



    public DateAdapter(int total_number_of_days_in_month, int i, int current_day){


        start = i-1;
        total = total_number_of_days_in_month;
        current = current_day;
    }

    public void resetAdapter(int total_number_of_days_in_month, int i, int current_day){
        start = i-1;
        total = total_number_of_days_in_month;
        current = current_day;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;

        public ViewHolder(View v){

            super(v);

            textView = (TextView) v.findViewById(R.id.date);
            v.setOnClickListener(this);

        }

        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {


            String got;
             if(fakeview != null) {

                 if(dateref%2 == 0) {
                        fakeview.setBackgroundColor(v.getContext().getResources().getColor(R.color.grey,null));
                 }
                 else {
                     fakeview.setBackground(null);
                 }
             }

            got = (String) textView.getText();
            if(!got.equals("")) {
                dateref = Integer.parseInt(got);
                if (dateref % 2 == 0) {
                    textView.setBackground(v.getContext().getResources().getDrawable(R.drawable.borders_even,null));
                } else {
                    textView.setBackground(v.getContext().getResources().getDrawable(R.drawable.borders,null));
                }
                fakeview = textView;
            }

        }




    }

    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days,parent,false);
        return new DateAdapter.ViewHolder(view);
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(DateAdapter.ViewHolder holder, int position) {
        Context context1 = holder.itemView.getContext();
        if(position>=start) {
            int date = position - (start - 1);
            holder.textView.setText(String.valueOf(date));
            if(date %2 == 0)
            {
                holder.textView.setBackgroundColor(context1.getResources().getColor(R.color.grey,null));
            }
            if(date == current)
            {
                holder.textView.setTextColor(context1.getResources().getColor(R.color.orange,null));

            }
        }
    }



    @Override
    public int getItemCount(){

        return total + (start);

    }


}
