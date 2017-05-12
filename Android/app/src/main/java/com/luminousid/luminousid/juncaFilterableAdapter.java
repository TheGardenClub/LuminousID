package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by chase on 4/17/2017.
 */

public class juncaFilterableAdapter extends RecyclerView.Adapter<juncaFilterableAdapter.juncaHolder> {

    public class juncaHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView plantThumbnail;
        public TextView speciesnameText;
        public TextView commonnameText;

        public juncaHolder(View mView, final Context mContext) {
            super(mView);

            plantThumbnail = (ImageView) mView.findViewById(R.id.plantThumbnail);
            speciesnameText = (TextView) mView.findViewById(R.id.species_nameText);
            commonnameText = (TextView) mView.findViewById(R.id.common_nameText);

            plantThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "juncaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    juncaceaeDetails juncaPlant = juncaFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(juncaPlant));

                    mContext.startActivity(intent);
                }
            });
            speciesnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "juncaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    juncaceaeDetails juncaPlant = juncaFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(juncaPlant));

                    mContext.startActivity(intent);
                }
            });
            commonnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "juncaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    juncaceaeDetails juncaPlant = juncaFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(juncaPlant));

                    mContext.startActivity(intent);
                }
            });

        }

    }

    private static ArrayList<juncaceaeDetails> mjuncaDetails;
    Context mContext;

    public juncaFilterableAdapter(Context context, ArrayList<juncaceaeDetails> juncadetails) {
        mjuncaDetails = juncadetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public juncaFilterableAdapter.juncaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View juncaView = inflater.inflate(R.layout.speciesnamelist, parent, false);

        // Return new holder instance
        juncaHolder juncaHolder = new juncaHolder(juncaView, parent.getContext());
        return juncaHolder;
    }

    @Override
    public void onBindViewHolder(juncaFilterableAdapter.juncaHolder mjuncaHolder, int position) {
        juncaceaeDetails thisJunca = mjuncaDetails.get(position);

        ImageView plantThumbnail = mjuncaHolder.plantThumbnail;
        TextView speciesnameText = mjuncaHolder.speciesnameText;
        TextView commonnameText = mjuncaHolder.commonnameText;

        // Using Glide library for image loading.
        String pictureFile = thisJunca.getPlant_code() + "_1.jpg";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/plantphotos/graminoids/" + pictureFile)).into(plantThumbnail);

        speciesnameText.setText(thisJunca.getSpecies_name());
        commonnameText.setText(thisJunca.getCommon_name());
    }

    @Override
    public int getItemCount() {
        return mjuncaDetails.size();
    }

    public static juncaceaeDetails getItem(int position) {
        return mjuncaDetails.get(position);
    }

}
