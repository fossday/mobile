package org.fossday.Adapter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;


import org.fossday.Adapter.ViewHolder.SponsorHolder;
import org.fossday.Model.Sponsor;
import org.fossday.R;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorHolder> {

    private static final String TAG = "SponsorAdapter";

    private static int TYPE_SPONSOR = 1;
    private static int TYPE_SUPPORTER = 2;

    private Context mContext;
    private List<Sponsor> sponsorList;
    private SponsorHolder.OnSponsorClickListener onSponsorClickListener;

    private int height;
    private int width;

    public SponsorAdapter(Context mContext, List<Sponsor> sponsorList, SponsorHolder.OnSponsorClickListener onSponsorClickListener) {
        this.mContext = mContext;
        this.sponsorList = sponsorList;
        this.onSponsorClickListener = onSponsorClickListener;
    }

    @NonNull
    @Override
    public SponsorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_speakers, parent, false);

        View view;
        if (viewType == TYPE_SPONSOR) {
            height = (int) (parent.getMeasuredHeight() * .2);
            width = (int) (parent.getMeasuredHeight() * .2);

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_sponsors, parent, false);

            view.setMinimumHeight(height);
            view.setMinimumWidth(width);
        } else { // for email layout
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_supporters, parent, false);
        }

        return new SponsorHolder(view, onSponsorClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorHolder holder, int position) {
        Sponsor sponsor = sponsorList.get(position);

        String file = Environment.getDataDirectory().getAbsolutePath() +
                "/data/br.com.eaglehorn.fossday/app_FOSSDAY/"+ sponsor.getType() +
                sponsor.getEid() + ".png";

        Log.d(TAG, "onBindViewHolder: " + file);

        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

        Glide.with(mContext).load(file)
//                .transition(DrawableTransitionOptions.withCrossFade())
                .transition(withCrossFade(factory))
                .transform(new RoundedCorners(10))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.tux_big)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.sponsor);

    }

    @Override
    public int getItemViewType(int position) {
        Sponsor sponsor = sponsorList.get(position);
        Log.d(TAG, "getItemViewType: " + sponsor.getType());
        if (sponsor.getType().equals("sponsors")) {
            return TYPE_SPONSOR;
        } else {
            return TYPE_SUPPORTER;
        }
    }

    @Override
    public int getItemCount() {
        return sponsorList != null ? sponsorList.size() : 0;
    }

}