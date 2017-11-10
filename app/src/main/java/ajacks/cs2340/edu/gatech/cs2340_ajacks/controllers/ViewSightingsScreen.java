package ajacks.cs2340.edu.gatech.cs2340_ajacks.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.Model;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;
import ajacks.cs2340.edu.gatech.cs2340_ajacks.model.RatSighting;

public class ViewSightingsScreen extends AppCompatActivity {

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sightings_screen);
        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = findViewById(R.id.rcv_ratSightings);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);
        //Step 3. Set up Layout Manager to the view
        ((RecyclerView) recyclerView).setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Set up an adapter and hook it to the provided view
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Model model = Model.getInstance();
        recyclerView.setAdapter(new RatSightingRecyclerViewAdapter(model.getAllSightings()));
    }

    /**
     * What happens when a specific sighting is clicked. Redirects to DetailedRatSightingScreen.
     * @param view view to be passed in
     */
    public void clickSighting(View view) {
        Intent intent = new Intent(ViewSightingsScreen.this, DetailedRatSightingScreen.class);
        LinearLayout layout = (LinearLayout) (view);
        TextView idTextView = (TextView) (layout.getChildAt(0));
        String id = idTextView.getText().toString();
        int sightingID = Integer.parseInt(id.substring(10));
        intent.putExtra("Sighting ID", sightingID);
        startActivity(intent);
    }

    /**
     * Specialized adapter for Rat Sightings Recycler View
     */
    public class RatSightingRecyclerViewAdapter extends RecyclerView.Adapter<RatSightingRecyclerViewAdapter.SightingViewHolder> {

        private final List<RatSighting> allSightings;

        public RatSightingRecyclerViewAdapter(List<RatSighting> sightings) {
            allSightings = sightings;
        }

        @Override
        public SightingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sighting_list_format, parent, false);
            return new SightingViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SightingViewHolder holder, int position) {
            //start by getting the element at the correct position
            holder.currRatSighting = allSightings.get(position);
            //set text views according to specific rat sighting
            holder.currId.setText("Sighting #" + allSightings.get(position).getId());
            holder.currDetails.setText(" spotted on " + allSightings.get(position).getDateAndTime());

            //TODO:: write listener for intent passing
        }

        @Override
        public int getItemCount() {
            return allSightings.size();
        }

        /**
         * This inner class represents a specialized ViewHolder which provides a way to cache information
         * about the binding between the model element and the widgets in the list view
         */
        public class SightingViewHolder extends RecyclerView.ViewHolder {
            private final View currView;
            private final TextView currId;
            private final TextView currDetails;
            private RatSighting currRatSighting;

            public SightingViewHolder(View view) {
                super(view);
                currView = view;
                currId = view.findViewById(R.id.tv_sightingId);
                currDetails = view.findViewById(R.id.tv_details);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + currDetails.getText() + "'";
            }
        }

    }



}
