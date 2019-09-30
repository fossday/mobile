package org.fossday.Adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.fossday.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LectureHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnLectureClickListener onLectureClickListener;

    @BindView(R.id.ivAvatar) public ImageView avatar;
    @BindView(R.id.tvLectureName) public TextView lectureName;
    @BindView(R.id.tvLectureDescription) public TextView lectureDescription;
    @BindView(R.id.tvPeriod) public TextView lecturePeriod;
    @BindView(R.id.tvTimer) public TextView lectureTime;
    @BindView(R.id.tvRoom) public TextView lectureRoom;

    public LectureHolder(View itemView, final LectureHolder.OnLectureClickListener onLectureClickListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.onLectureClickListener = onLectureClickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onLectureClickListener.onLectureClick(getAdapterPosition());
    }

    public interface OnLectureClickListener {
        void onLectureClick(int position);
    }
}
