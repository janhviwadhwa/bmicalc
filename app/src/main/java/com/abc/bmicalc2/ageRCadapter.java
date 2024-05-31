package com.abc.bmicalc2;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ageRCadapter extends RecyclerView.Adapter<ageRCadapter.AgeViewHolder> {
    private List<Integer> ageList;
    private int selectedItem = -1;
    private int centeredPosition = RecyclerView.NO_POSITION;

    public void setCenteredPosition(int position) {
        centeredPosition = position;
    }

    public int getCenteredPosition() {
        return centeredPosition;
    }

    public void clearCenteredPosition() {
        centeredPosition = RecyclerView.NO_POSITION;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ageRCadapter(List<Integer> ageList) {
        this.ageList = ageList;
    }

    @NonNull
    @Override
    public AgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_age, parent, false);
        return new AgeViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AgeViewHolder holder, int position) {
        if (ageList != null && position < ageList.size() && ageList.get(position) != null) {
            Integer age = ageList.get(position);
            holder.textAge.setText(String.valueOf(age));

            if (position == selectedItem) {
                holder.textAge.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                holder.textAge.setBackgroundColor(Color.BLUE);
            } else {
                holder.textAge.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                holder.textAge.setBackgroundColor(Color.WHITE);
            }
        } else {
            holder.textAge.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return ageList.size();
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    static class AgeViewHolder extends RecyclerView.ViewHolder {
        TextView textAge;

        public AgeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textAge = itemView.findViewById(R.id.txtage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
