package org.fossday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.fossday.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LectureDetailsActivity extends AppCompatActivity {

    @BindView(R.id.ivSpeaker) ImageView ivSpeaker;
    @BindView(R.id.tvLectureTitle) TextView tvLectureTitle;
    @BindView(R.id.tvPeriod) TextView tvPeriod;
    @BindView(R.id.tvTime) TextView tvTime;
    @BindView(R.id.tvRoom) TextView tvRoom;
    @BindView(R.id.tvLectureDescription) TextView tvLectureDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String file = Environment.getDataDirectory().getAbsolutePath() +
                "/data/br.com.eaglehorn.fossday/app_FOSSDAY/speaker" +
                intent.getIntExtra("speakerId", 0) + ".png";

        Glide.with(this).load(file)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new RoundedCorners(10))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.tux)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivSpeaker);

        tvLectureTitle.setText(intent.getStringExtra("title"));

        if (intent.getStringExtra("period").equals("morning")) {
            tvPeriod.setText("Manhã");
        } else {
            tvPeriod.setText("Tarde");
        }

        String time;
        if (String.valueOf(intent.getIntExtra("time",0)).length() == 3) {
            time = "0" + intent.getIntExtra("time", 0);
        } else {
            time = String.valueOf(intent.getIntExtra("time", 0));
        }

        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);

        tvTime.setText(String.format(hour + "%s" + minute, ":"));
        tvRoom.setText(intent.getStringExtra("room"));
        tvLectureDescription.setText(intent.getStringExtra("description"));
    }
}

