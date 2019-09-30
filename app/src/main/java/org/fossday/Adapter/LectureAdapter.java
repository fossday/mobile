package org.fossday.Adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.fossday.Adapter.ViewHolder.LectureHolder;
import org.fossday.Model.Lecture;
import org.fossday.R;

import java.util.List;

public class LectureAdapter extends RecyclerView.Adapter<LectureHolder> {

    private Context mContext;
    private List<Lecture> lectureList;
    private LectureHolder.OnLectureClickListener onLectureClickListener;

    public LectureAdapter(Context mContext, List<Lecture> lectureList, LectureHolder.OnLectureClickListener onLectureClickListener) {
        this.mContext = mContext;
        this.lectureList = lectureList;
        this.onLectureClickListener = onLectureClickListener;
    }

    @NonNull
    @Override
    public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_lectures, parent, false);
        return new LectureHolder(view, onLectureClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
        Lecture lecture = lectureList.get(position);

//        Glide.with(mContext).load(R.drawable.harold)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .apply(RequestOptions.circleCropTransform())
//                .skipMemoryCache(false)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.avatar);

        String file = Environment.getDataDirectory().getAbsolutePath() +
                "/data/br.com.eaglehorn.fossday/app_FOSSDAY/speaker" +
                lecture.getSpeakerId() + ".png";

        Glide.with(mContext).load(file)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new RoundedCorners(10))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.tux_big)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatar);

        holder.lectureName.setText(lecture.getName());

        if (lecture.getPeriod().equals("morning")) {
            holder.lecturePeriod.setText("Manhã");
        } else {
            holder.lecturePeriod.setText("Tarde");
        }

        String time;
        if (String.valueOf(lecture.getTime()).length() == 3) {
            time = "0" + lecture.getTime();
        } else {
            time = String.valueOf(lecture.getTime());
        }

        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);

        holder.lectureTime.setText(String.format(hour + "%s" + minute, ":"));

        holder.lectureRoom.setText(lecture.getRoom());
    }

    @Override
    public int getItemCount() {
        return lectureList != null ? lectureList.size() : 0;
    }


}
