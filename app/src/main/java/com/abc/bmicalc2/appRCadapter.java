package com.abc.bmicalc2;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PublicKey;
import java.util.List;

public class ageRCadapter extends RecyclerView.Adapter<ageRCadapter.AgeViewHolder> {
    private List<Integer> ageList;
    private int selectedItem= -1;
    private int canteredPosition = RecyclerView.NO_POSITION;
    public void setCanteredPosition(int position){ canteredPosition = position; }
    public int getCanteredPosition(){ return canteredPosition;}
    public void clearCenteredPosition(){ canteredPosition= RecyclerView.NO_POSITION;}

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    private static  OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){ this.listener = listener;}
    public ageRCadapter(List<Integer> ageList){this.ageList=ageList;}

    @NonNull
    @Override
    public AgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.agescroll,parent,false);
        return new AgeViewHolder(view);

    }
    public void onBindViewHolder(@NonNull AgeViewHolder holder, int position){
        Integer age= ageList.get(position);
        holder.textAge.setText(String.valueOf(age));
        if(age!=null){
            if(position==selectedItem){
                holder.textAge.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
                holder.textAge.setBackgroundColor(Color.BLUE);

            }else {
                holder.textAge.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                holder.textAge.setBackgroundColor(Color.WHITE);
            }
        }else {
            holder.textAge.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return ageList.size();
    }
    public void setSelectedItem(int position){
        selectedItem=position;
        notifyDataSetChanged();
    }
    static class AgeViewHolder extends RecyclerView.ViewHolder{
        TextView textAge;
        public AgeViewHolder(@NonNull View itemView){
            super(itemView);
            textAge=itemView.findViewById(R.id.txtage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
