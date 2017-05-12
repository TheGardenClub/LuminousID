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

public class deciFilterableAdapter extends RecyclerView.Adapter<deciFilterableAdapter.deciHolder> {

    public class deciHolder extends RecyclerView.ViewHolder {

        View mView;
        Context mContext;

        public ImageView plantThumbnail;
        public TextView speciesnameText;
        public TextView commonnameText;

        public deciHolder(View mView, final Context mContext) {
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
                    intent.putExtra("plantType", "deciduous");

                    // Get plant clicked on and send to plantDetailActivity.
                    deciduousDetails deciPlant = deciFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(deciPlant));

                    mContext.startActivity(intent);
                }
            });
            speciesnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "deciduous");

                    // Get plant clicked on and send to plantDetailActivity.
                    deciduousDetails deciPlant = deciFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(deciPlant));

                    mContext.startActivity(intent);
                }
            });
            commonnameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, plantDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("plantType", "deciduous");

                    // Get plant clicked on and send to plantDetailActivity.
                    deciduousDetails deciPlant = deciFilterableAdapter.getItem(itemPosition);

                    // Use Parcels class to wrap a class and send it to the detail activity.
                    intent.putExtra("plantInfo", Parcels.wrap(deciPlant));

                    mContext.startActivity(intent);
                }
            });

        }

    }

    private static ArrayList<deciduousDetails> mdeciDetails;
    Context mContext;

    public deciFilterableAdapter(Context context, ArrayList<deciduousDetails> deciDetails) {
        mdeciDetails = deciDetails;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public deciFilterableAdapter.deciHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View deciView = inflater.inflate(R.layout.speciesnamelist, parent, false);

        // Return new holder instance
        deciHolder deciHolder = new deciHolder(deciView, parent.getContext());
        return deciHolder;
    }

    @Override
    public void onBindViewHolder(deciFilterableAdapter.deciHolder mdeciHolder, int position) {
        deciduousDetails thisDeci = mdeciDetails.get(position);

        ImageView plantThumbnail = mdeciHolder.plantThumbnail;
        TextView speciesnameText = mdeciHolder.speciesnameText;
        TextView commonnameText = mdeciHolder.commonnameText;

        // Using Glide library for image loading.
        String pictureFile = thisDeci.getPlant_code() + ".jpg";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/plantphotos/woody/" + pictureFile)).into(plantThumbnail);

        speciesnameText.setText(thisDeci.getSpecies_name());
        commonnameText.setText(thisDeci.getCommon_name());
    }

    @Override
    public int getItemCount() {
        return mdeciDetails.size();
    }

    public static deciduousDetails getItem(int position) {
        return mdeciDetails.get(position);
    }

}
