package com.tringapps.calenderdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by geethu on 19/12/16.
 */

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder>{

    private int total;
//    private Context context1;
    private String[] week = null;


    public WeekAdapter( String[] values2){

        week = values2;

//        context1 = context2;


    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View v){

            super(v);

            textView = (TextView) v.findViewById(R.id.date);

        }
    }

    @Override
    public WeekAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){

        Vholder.textView.setText(week[position]);


    }

    @Override
    public int getItemCount(){


            return week.length;

    }
}