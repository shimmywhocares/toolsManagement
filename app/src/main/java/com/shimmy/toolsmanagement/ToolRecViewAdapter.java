package com.shimmy.toolsmanagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ToolRecViewAdapter extends RecyclerView.Adapter<ToolRecViewAdapter.ViewHolder>{
    private static final String TAG = "ToolRecViewAdapter";

    private ArrayList<Tool> toolsArrayList = new ArrayList<>();
    private Context mContext;

    public ToolRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tool, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        //here I think I need to implement Pares API
        holder.txtToolName.setText(toolsArrayList.get(position).getName());
        holder.txtManufacturer.setText(toolsArrayList.get(position).getManufacturer());
        Glide.with(mContext)
                .asBitmap()
                .load(toolsArrayList.get(position).getImageUrl())
                .into(holder.imgTool);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Intent czy nie intent");
                //Toast.makeText(mContext, toolsArrayList.get(position).getName() + " selected, yo!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (mContext, ToolActivity.class);
                mContext.startActivity(intent);


            }
        });

        holder.txtStatus.setText(toolsArrayList.get(position).getStatus());
        //holder.txtPlaceOfStaying.setText(toolsArrayList.get(position).getPlaceOfStaying());
        holder.txtHolder.setText(toolsArrayList.get(position).getHolder());
        //TODO: toString() somehow
        holder.txtHoursOfUsage.setText(String.valueOf(toolsArrayList.get(position).getHoursOfUsage()));
        holder.txtToolID.setText(String.valueOf(toolsArrayList.get(position).getId()));

        //turn on and off the visibility of expendedRelLayout
        if(toolsArrayList.get(position).isExpanded()){
            //In next method I need to pass the root. In this case the root is CardView - parent.
            androidx.transition.TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        }else {
            androidx.transition.TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

    }

    //It returns the number of items or size of list in RecView
    @Override
    public int getItemCount() {
        return toolsArrayList.size();
    }

    public void setToolsArrayList(ArrayList<Tool> toolArrayList) {
        this.toolsArrayList = toolArrayList;
        //we're going to refresh the data in our recycler view
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgTool;
        private TextView txtToolName;
        private TextView txtManufacturer;

        private TextView txtStatus, txtPlaceOfStaying, txtHolder, txtHoursOfUsage, txtToolID;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;

        //WTF is that?
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgTool = itemView.findViewById(R.id.imgTool);
            txtToolName = itemView.findViewById(R.id.textViewToolName);
            txtManufacturer = itemView.findViewById(R.id.textViewManufacturer);

            txtStatus = itemView.findViewById(R.id.txtStatus);
            //txtPlaceOfStaying = itemView.findViewById(R.id.txtPlaceOfStaying);
            txtHolder = itemView.findViewById(R.id.txtHolder);
            txtHoursOfUsage = itemView.findViewById(R.id.txtHoursOfUsage);
            txtToolID = itemView. findViewById(R.id.txtToolID);

            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //I'm going to get the current tool that I'm looking into; the second option to do that is by onBind method
                     Tool tool = toolsArrayList.get(getAdapterPosition());

                     //after getting the tool I'm going to change its field e.g isExpended; shorter ver of the negation
                     tool.setExpanded(!tool.isExpanded());

                     //tell the adapter that I've changed some item in my data set
                    notifyItemChanged(getAdapterPosition());
                }

            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //I'm going to get the current tool that I'm looking into; the second option is by onBind method
                    Tool tool = toolsArrayList.get(getAdapterPosition());

                    //after getting the tool I'm going to change its field e.g isExpended; shorter ver of the negation
                    tool.setExpanded(!tool.isExpanded());

                    //tell the adapter that I've changed some item in my data set
                    notifyItemChanged(getAdapterPosition());
                }

            });

        }
    }
}
