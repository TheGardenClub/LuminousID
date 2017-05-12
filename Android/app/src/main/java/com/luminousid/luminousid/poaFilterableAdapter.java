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

public class poaFilterableAdapter extends RecyclerView.Adapter<poaFilterableAdapter.poaHolder> {

    public class poaHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView plantThumbnail;
        public TextView speciesnameText;
        public TextView commonnameText;

        public poaHolder(View mView, final Context mContext) {
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
                    intent.putExtra("plantType", "poaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    poaceaeDetails poaPlant = poaFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(poaPlant));

                    mContext.startActivity(intent);
                }
            });
            speciesnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "poaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    poaceaeDetails poaPlant = poaFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(poaPlant));

                    mContext.startActivity(intent);
                }
            });
            commonnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "poaceae");

                    // Get plant clicked on and send to plantDetailActivity.
                    poaceaeDetails poaPlant = poaFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(poaPlant));

                    mContext.startActivity(intent);
                }
            });

        }

    }

    private static ArrayList<poaceaeDetails> mpoaDetails;
    Context mContext;

    public poaFilterableAdapter(Context context, ArrayList<poaceaeDetails> poadetails) {
        mpoaDetails = poadetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public poaFilterableAdapter.poaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View poaView = inflater.inflate(R.layout.speciesnamelist, parent, false);

        // Return new holder instance
        poaHolder poaHolder = new poaHolder(poaView, parent.getContext());
        return poaHolder;
    }

    @Override
    public void onBindViewHolder(poaFilterableAdapter.poaHolder mpoaHolder, int position) {
        poaceaeDetails thisPoa = mpoaDetails.get(position);

        ImageView plantThumbnail = mpoaHolder.plantThumbnail;
        TextView speciesnameText = mpoaHolder.speciesnameText;
        TextView commonnameText = mpoaHolder.commonnameText;

        // Using Glide library for image loading.
        String pictureFile = thisPoa.getPlant_code() + "_1.jpg";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/plantphotos/graminoids/" + pictureFile)).into(plantThumbnail);

        speciesnameText.setText(thisPoa.getSpecies_name());
        commonnameText.setText(thisPoa.getCommon_name());
    }

    @Override
    public int getItemCount() {
        return mpoaDetails.size();
    }

    public static poaceaeDetails getItem(int position) {
        return mpoaDetails.get(position);
    }

}
