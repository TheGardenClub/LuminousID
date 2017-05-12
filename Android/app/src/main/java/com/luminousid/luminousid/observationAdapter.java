package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by chase on 4/17/2017.
 */

public class observationAdapter extends RecyclerView.Adapter<observationAdapter.observationHolder> {

    public class observationHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView observationThumbnail;
        public TextView speciesnameText;
        public TextView notesText;
        public TextView syncstatusText;

        public observationHolder(View mView, final Context mContext) {
            super(mView);

            observationThumbnail = (ImageView) mView.findViewById(R.id.observationThumbnail);
            speciesnameText = (TextView) mView.findViewById(R.id.species_nameText);
            notesText = (TextView) mView.findViewById(R.id.notesText);
            syncstatusText = (TextView) mView.findViewById(R.id.syncstatusText);

            /*
            observationThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "cyperaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    cyperaceaeDetails cyperPlant = observationAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(cyperPlant));

                    mContext.startActivity(intent);
                }
            });
            speciesnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "cyperaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    cyperaceaeDetails cyperPlant = observationAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(cyperPlant));

                    mContext.startActivity(intent);
                }
            });
            commonnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "cyperaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    cyperaceaeDetails cyperPlant = observationAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(cyperPlant));

                    mContext.startActivity(intent);
                }
            });
            */

        }

    }

    private static ArrayList<observationDetails> mobsDetails;
    Context mContext;

    public observationAdapter(Context context, ArrayList<observationDetails> obsdetails) {
        mobsDetails = obsdetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public observationAdapter.observationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View obsView = inflater.inflate(R.layout.observationlist, parent, false);

        // Return new holder instance
        observationHolder obsHolder = new observationHolder(obsView, parent.getContext());
        return obsHolder;
    }

    @Override
    public void onBindViewHolder(observationAdapter.observationHolder mobsHolder, int position) {
        observationDetails thisObs = mobsDetails.get(position);

        ImageView observationThumbnail = mobsHolder.observationThumbnail;
        TextView speciesnameText = mobsHolder.speciesnameText;
        TextView commentsText = mobsHolder.notesText;
        TextView syncstatusText = mobsHolder.syncstatusText;

        // Find a way to get picture saved on phone
        String imagePath = thisObs.getImagePath();
        Glide.with(mContext).load(imagePath).into(observationThumbnail);

        speciesnameText.setText(thisObs.getSpecies_name());
        commentsText.setText(thisObs.getComments());
        if(thisObs.getIs_synced()){
            syncstatusText.setText("Synced");
            syncstatusText.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return mobsDetails.size();
    }

    public static observationDetails getItem(int position) {
        return mobsDetails.get(position);
    }

}
