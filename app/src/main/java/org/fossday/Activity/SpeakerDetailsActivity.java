package org.fossday.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import butterknife.OnClick;

public class SpeakerDetailsActivity extends AppCompatActivity {

    private static final String TAG = "SpeakerDetailsActivity";

    @BindView(R.id.ivSpeaker) ImageView ivSpeaker;
    @BindView(R.id.tvSpeakerName) TextView tvSpeakerName;
    @BindView(R.id.tvSpeakerDescription) TextView tvSpeakerDescription;

    String linkedin, facebook, instagram, github;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String file = Environment.getDataDirectory().getAbsolutePath() +
                "/data/br.com.eaglehorn.fossday/app_FOSSDAY/speaker" +
                intent.getIntExtra("id", 0) + ".png";

        Glide.with(this).load(file)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new RoundedCorners(10))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.tux)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivSpeaker);

        tvSpeakerName.setText(intent.getStringExtra("name"));
        tvSpeakerDescription.setText(intent.getStringExtra("description"));
    }

    @OnClick(R.id.btnLinkedin)
    void linkedin() {
        Log.d(TAG, "linkedin: ");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/alangodoi"));
        startActivity(intent);
    }

    @OnClick(R.id.btnFacebook)
    void facebook() {
        Log.d(TAG, "facebook: ");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/IchBinGodoi"));
        startActivity(intent);
    }

    @OnClick(R.id.btnInstagram)
    void Instagram() {
        Log.d(TAG, "Instagram: ");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/ichbingodoi"));
        startActivity(intent);
    }

    @OnClick(R.id.btnGithub)
    void Github() {
        Log.d(TAG, "Github: ");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/alangodoi"));
        startActivity(intent);
    }
}

