package org.fossday.Adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.fossday.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeakerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnSpeakerClickListener onSpeakerClickListener;

    @BindView(R.id.ivAvatar) public ImageView avatar;
    @BindView(R.id.tvSpeakerName) public TextView speakerName;
    @BindView(R.id.tvSpeakerPosition) public TextView speakerPosition;

    public SpeakerHolder(View itemView, final SpeakerHolder.OnSpeakerClickListener onSpeakerClickListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.onSpeakerClickListener = onSpeakerClickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onSpeakerClickListener.onSpeakerClick(getAdapterPosition());
    }

    public interface OnSpeakerClickListener {
        void onSpeakerClick(int position);
    }
}
