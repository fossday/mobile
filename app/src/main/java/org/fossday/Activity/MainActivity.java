package org.fossday.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.fossday.Preference.PrefManager;
import org.fossday.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 *  Aplicativo inicialmente desenvolvido por Alan Godoi da Silveira
 *  https://github.com/alangodoi
 *
 **/

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.ivGift)
    ImageView ivGift;

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        prefManager = new PrefManager(this);
        prefManager.setFirstTimeLaunch(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!prefManager.isGiftOpened()) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            ivGift.startAnimation(shake);
        }
    }

    @OnClick(R.id.cvSchedule)
    public void schedule() {
        startActivity(new Intent(this, ScheduleActivity.class));
    }

    @OnClick(R.id.cvSponsors)
    public void sponsors() {
        startActivity(new Intent(this, SponsorsActivity.class));
    }

    @OnClick(R.id.cvMaps)
    public void maps() {
        startActivity(new Intent(this, MapsActivity.class));
    }

    @OnClick(R.id.cvSpeakers)
    public void speakers() {
        startActivity(new Intent(this, SpeakersActivity.class));
    }

    @OnClick(R.id.cvGift)
    public void gift() {
//        startActivity(new Intent(this, GiftActivity.class));
//        if (!prefManager.isGiftOpened()) {
        Intent intent = new Intent(android.content.Intent.ACTION_SENDTO);
        Uri data = Uri.parse("mailto:?subject=" +
                "Estou me cadastrando pelo App do FOSSDay" +
                "&body=" + "FOSSDay2019" +
                "&to=" + "fossday@fossday.org");

        intent.setData(data);
        startActivity(Intent.createChooser(intent, "Escolha o seu app de Email"));

        prefManager.setGiftOpened(true);

//        } else {
//            Toast.makeText(this, "Você já está participando!", Toast.LENGTH_LONG).show();
//        }

    }

    @OnClick(R.id.cvTelegram)
    public void telegram() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/fossday"));
        startActivity(intent);
    }
}

