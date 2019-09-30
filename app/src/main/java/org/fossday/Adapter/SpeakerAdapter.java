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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.fossday.Adapter.ViewHolder.SpeakerHolder;
import org.fossday.Model.Speaker;
import org.fossday.R;

import java.util.List;


public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerHolder> {

    private Context mContext;
    private List<Speaker> speakerList;
    private SpeakerHolder.OnSpeakerClickListener onSpeakerClickListener;

    public SpeakerAdapter(Context mContext, List<Speaker> speakerList, SpeakerHolder.OnSpeakerClickListener onSpeakerClickListener) {
        this.mContext = mContext;
        this.speakerList = speakerList;
        this.onSpeakerClickListener = onSpeakerClickListener;
    }

    @NonNull
    @Override
    public SpeakerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_speakers, parent, false);
        return new SpeakerHolder(view, onSpeakerClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakerHolder holder, int position) {
        Speaker speaker = speakerList.get(position);

        String file = Environment.getDataDirectory().getAbsolutePath() +
                "/data/br.com.eaglehorn.fossday/app_FOSSDAY/speaker" +
                speaker.getId() + ".png";

        Glide.with(mContext).load(file)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new RoundedCorners(10))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.tux_big)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatar);

        holder.speakerName.setText(speaker.getName());

        String[] wordCounter = speaker.getDescription().split(" ");
        if (wordCounter.length > 7) {
            StringBuilder description = new StringBuilder();
            for (int i=0; i<7; i++) {

                description.append(wordCounter[i]);
                description.append(" ");
            }
            description.append("...");
            holder.speakerPosition.setText(description);
        } else {
            holder.speakerPosition.setText(speaker.getDescription());
        }

//        holder.speakerPosition.setText(speaker.getDescription());
    }

    @Override
    public int getItemCount() {
        return speakerList != null ? speakerList.size() : 0;
    }


}
