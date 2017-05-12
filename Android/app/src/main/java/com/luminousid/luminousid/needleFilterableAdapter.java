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

public class needleFilterableAdapter extends RecyclerView.Adapter<needleFilterableAdapter.needleHolder> {

    public class needleHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView plantThumbnail;
        public TextView speciesnameText;
        public TextView commonnameText;

        public needleHolder(View mView, final Context mContext) {
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
                    intent.putExtra("plantType", "needle");

                    // Get plant clicked on and send to plantDetailActivity.
                    needleDetails needlePlant = needleFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(needlePlant));

                    mContext.startActivity(intent);
                }
            });
            speciesnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "needle");

                    // Get plant clicked on and send to plantDetailActivity.
                    needleDetails needlePlant = needleFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(needlePlant));

                    mContext.startActivity(intent);
                }
            });
            commonnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "needle");

                    // Get plant clicked on and send to plantDetailActivity.
                    needleDetails needlePlant = needleFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(needlePlant));

                    mContext.startActivity(intent);
                }
            });

        }

    }

    private static ArrayList<needleDetails> mneedleDetails;
    Context mContext;

    public needleFilterableAdapter(Context context, ArrayList<needleDetails> needleDetails) {
        mneedleDetails = needleDetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public needleFilterableAdapter.needleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View needleView = inflater.inflate(R.layout.speciesnamelist, parent, false);

        // Return new holder instance
        needleHolder mneedleHolder = new needleHolder(needleView, parent.getContext());
        return mneedleHolder;
    }

    @Override
    public void onBindViewHolder(needleFilterableAdapter.needleHolder mneedleHolder, int position) {
        needleDetails thisNeedle = mneedleDetails.get(position);

        ImageView plantThumbnail = mneedleHolder.plantThumbnail;
        TextView speciesnameText = mneedleHolder.speciesnameText;
        TextView commonnameText = mneedleHolder.commonnameText;

        // Using Glide library for image loading.
        String pictureFile = thisNeedle.getPlant_code() + ".jpg";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/plantphotos/woody/" + pictureFile)).into(plantThumbnail);

        speciesnameText.setText(thisNeedle.getSpecies_name());
        commonnameText.setText(thisNeedle.getCommon_name());
    }

    @Override
    public int getItemCount() {
        return mneedleDetails.size();
    }

    public static needleDetails getItem(int position) {
        return mneedleDetails.get(position);
    }

}
