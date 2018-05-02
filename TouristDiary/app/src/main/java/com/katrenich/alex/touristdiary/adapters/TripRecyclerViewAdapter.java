package com.katrenich.alex.touristdiary.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.katrenich.alex.touristdiary.R;
import com.katrenich.alex.touristdiary.entity.Trip;

import java.util.List;

public class TripRecyclerViewAdapter extends RecyclerView.Adapter<TripRecyclerViewAdapter.TripViewHolder> {
    private List<Trip> trips;
    private OnItemClickListener onItemClickListener;


    public TripRecyclerViewAdapter(List<Trip> trips) {
        this.trips = trips;
    }


    /* int viewType - номер позиції в списку відповідної в'юхи
     * метод створює пусту оболочку для в'юхи
     * */
    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // парсимо зроблений лейаут(ітем списка подорожей) у в'юху
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_trip, parent, false);

        return new TripViewHolder(itemView);
    }

    // метод наповнює оболочку в'юхи даними
    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, final int position) {
        Trip trip = trips.get(position);
        holder.setData(trip);

        holder.itemRecycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
//                    v.setBackgroundColor();
                    Toast.makeText(v.getContext(),
                            String.format("Element with position: %s was clicked!", position),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //метод відповідає за формування відповідної кількості Item в RecycleView
    @Override
    public int getItemCount() {
        return trips.size();
    }

    // вкладений клас
    public static class TripViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTripShortDescr;
        protected LinearLayout itemRecycleView;

        public TripViewHolder(View itemView) {
            super(itemView);
            tvTripShortDescr = itemView.findViewById(R.id.tv_trip_short_description);
            itemRecycleView = itemView.findViewById(R.id.ll_item_trip);
        }

        public void setData(Trip trip) {
            tvTripShortDescr.setText(trip.getShortDescription());
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    //інтерфейс для відстеження кліку по елементу списку RecycleView
    public interface OnItemClickListener {
        public void onItemClick(int position);
    }
}
