package org.fossday.Adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.fossday.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SponsorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private SponsorHolder.OnSponsorClickListener onSponsorClickListener;

    @BindView(R.id.ivSponsor)
    public ImageView sponsor;
//    @BindView(R.id.tvSpeakerName)
//    public TextView speakerName;
//    @BindView(R.id.tvSpeakerPosition)
//    public TextView speakerPosition;

    public SponsorHolder(View itemView, final SponsorHolder.OnSponsorClickListener onSponsorClickListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.onSponsorClickListener = onSponsorClickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onSponsorClickListener.onSponsorClick(getAdapterPosition());
    }

    public interface OnSponsorClickListener {
        void onSponsorClick(int position);
    }
}

