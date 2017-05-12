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

public class cyperFilterableAdapter extends RecyclerView.Adapter<cyperFilterableAdapter.cyperHolder> {

    public class cyperHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView plantThumbnail;
        public TextView speciesnameText;
        public TextView commonnameText;

        public cyperHolder(View mView, final Context mContext) {
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
                    intent.putExtra("plantType", "cyperaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    cyperaceaeDetails cyperPlant = cyperFilterableAdapter.getItem(itemPosition);

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
                    cyperaceaeDetails cyperPlant = cyperFilterableAdapter.getItem(itemPosition);

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
                    cyperaceaeDetails cyperPlant = cyperFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(cyperPlant));

                    mContext.startActivity(intent);
                }
            });

        }

    }

    private static ArrayList<cyperaceaeDetails> mcyperDetails;
    Context mContext;

    public cyperFilterableAdapter(Context context, ArrayList<cyperaceaeDetails> cyperdetails) {
        mcyperDetails = cyperdetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public cyperFilterableAdapter.cyperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View cyperView = inflater.inflate(R.layout.speciesnamelist, parent, false);

        // Return new holder instance
        cyperHolder cyperHolder = new cyperHolder(cyperView, parent.getContext());
        return cyperHolder;
    }

    @Override
    public void onBindViewHolder(cyperFilterableAdapter.cyperHolder mcyperHolder, int position) {
        cyperaceaeDetails thisCyper = mcyperDetails.get(position);

        ImageView plantThumbnail = mcyperHolder.plantThumbnail;
        TextView speciesnameText = mcyperHolder.speciesnameText;
        TextView commonnameText = mcyperHolder.commonnameText;

        // Using Glide library for image loading.
        String pictureFile = thisCyper.getPlant_code() + "_1.jpg";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/plantphotos/graminoids/" + pictureFile)).into(plantThumbnail);

        speciesnameText.setText(thisCyper.getSpecies_name());
        commonnameText.setText(thisCyper.getCommon_name());
    }

    @Override
    public int getItemCount() {
        return mcyperDetails.size();
    }

    public static cyperaceaeDetails getItem(int position) {
        return mcyperDetails.get(position);
    }

}
