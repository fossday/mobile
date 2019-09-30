package org.fossday.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.fossday.Database.DB;
import org.fossday.Helper.StorageHelper;
import org.fossday.Model.Category;
import org.fossday.Model.Lecture;
import org.fossday.Model.Media;
import org.fossday.Model.Speaker;
import org.fossday.Model.Sponsor;
import org.fossday.Model.SponsorSupporter;
import org.fossday.Model.Talk;
import org.fossday.Model.User;
import org.fossday.Network.ApiClient;
import org.fossday.Network.ApiInterface;
import org.fossday.Preference.PrefManager;
import org.fossday.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    DB db;
    ApiInterface apiInterface;
    PrefManager prefManager;
    StorageHelper storageHelper;

    @BindView(R.id.ivEvent)
    ImageView ivEvent;

    @BindView(R.id.tvCreator)
    TextView tvCreator;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        Animation pulsate = AnimationUtils.loadAnimation(this, R.anim.pulsate);
        tvCreator.startAnimation(pulsate);

        prefManager = new PrefManager(this);
        storageHelper = new StorageHelper(this);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        int SPLASH_DISPLAY_LENGTH;
        if (isConnected) {

            if (prefManager.isFirstTimeLaunch()) {
                SPLASH_DISPLAY_LENGTH = 12000;
            } else {
                SPLASH_DISPLAY_LENGTH = 4000;
            }

            new Handler().postDelayed(() -> {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }, SPLASH_DISPLAY_LENGTH);

            db = new DB(this);
            db.clearTables();

            apiInterface =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<List<Category>> call = apiInterface.getCategories(100);
            Call<List<User>> call1 = apiInterface.getSpeakers(100);
            Call<List<Talk>> call2 = apiInterface.getLectures(100);
            Call<List<SponsorSupporter>> call3 = apiInterface.getSponsors(100);
            Call<List<SponsorSupporter>> call5 = apiInterface.getSupporters(100);

            // CATEGORIES
            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if (response.code() == 200) {
                        for (int i=0; i<response.body().size(); i++) {
                            Log.d(TAG, "Category: " + response.body().get(i).getName());
                            db.insertCategoy(new Category(
                                    response.body().get(i).getId(),
                                    response.body().get(i).getName()
                            ));
                        }
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                }
            });

            // SPEAKERS
            call1.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.code() == 200) {
                        for (int i=0; i<response.body().size(); i++) {

                            if (!response.body().get(i).getName().equals("charmander") &&
                                    !response.body().get(i).getName().equals("Eduarda Nicaretta")
                            ) {
                                db.insertSpeaker(new Speaker(
                                        response.body().get(i).getId(),
                                        response.body().get(i).getName(),
                                        "Position " + i,
                                        String.valueOf(Html.fromHtml(response.body().get(i).getDescription()))
                                ));

                                int id = response.body().get(i).getId();
                                String link = response.body().get(i).getAvatarUrls().get96().replace("\\", "");

                                Call<ResponseBody> call4 = apiInterface.download(link);

                                // Images
                                call4.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()){

                                            new StorageHelper(getApplicationContext())
                                                    .setDirectoryName("FOSSDAY")
                                                    .setFileName("speaker"+id+".png")
                                                    .setExternal(false)
                                                    .saveFile(response.body());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Log.d(TAG, "onFailure: " + t.toString());
                                    }
                                });
                            }

                        }
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                }
            });

            // LECTURES
            call2.enqueue(new Callback<List<Talk>>() {
                @Override
                public void onResponse(Call<List<Talk>> call, Response<List<Talk>> response) {
                    if (response.code() == 200) {
                        for (int i=0; i<response.body().size(); i++) {

                            db.insertLecture(new Lecture(
//                                    response.body().get(i).getTitle().getRendered(),
                                    String.valueOf(Html.fromHtml(response.body().get(i).getTitle().getRendered())),
                                    String.valueOf(Html.fromHtml(response.body().get(i).getExcerpt().getRendered())),
                                    response.body().get(i).getPeriod(),
                                    response.body().get(i).getTime(),
                                    response.body().get(i).getRoom(),
                                    Integer.parseInt(response.body().get(i).getAuthor().toString())
                            ));
                        }
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }

                }

                @Override
                public void onFailure(Call<List<Talk>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                }
            });

            // SPONSORS
            call3.enqueue(new Callback<List<SponsorSupporter>>() {
                @Override
                public void onResponse(Call<List<SponsorSupporter>> call, Response<List<SponsorSupporter>> response) {
                    if (response.code() == 200) {

                        for (int i=0; i<response.body().size(); i++) {
//                            if (response.body().get(i).getId() != 112) {

                            Log.d(TAG, "SPONSORS ID: " + response.body().get(i).getId());

                            db.insertSponsor(new Sponsor(
                                    response.body().get(i).getId(),
                                    String.valueOf(Html.fromHtml(response.body().get(i).getTitle().getRendered())),
                                    response.body().get(i).getLink(),
                                    response.body().get(i).getType()
                            ));

                            int id = response.body().get(i).getId();
                            String type = response.body().get(i).getType();
                            Call<Media> medias = apiInterface.getMedias(
                                    response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref()
                            );

                            medias.enqueue(new Callback<Media>() {
                                @Override
                                public void onResponse(Call<Media> call, Response<Media> response) {
                                    Log.d(TAG, "Media - Response Code: " + response.code());

                                    if (response.code() == 200) {
                                        String link = response.body().getGuid().getRendered();

                                        Call<ResponseBody> file = apiInterface.download(link);

                                        //Images
                                        file.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.isSuccessful()){

                                                    new StorageHelper(getApplicationContext())
                                                            .setDirectoryName("FOSSDAY")
                                                            .setFileName(type+id+".png")
                                                            .setExternal(false)
                                                            .saveFile(response.body());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                Log.d(TAG, "onFailure: " + t.toString());
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onFailure(Call<Media> call, Throwable t) {
                                    Log.d(TAG, "Media - onFailure: " + t);
                                }
                            });

//                            }
                        }
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<SponsorSupporter>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                }
            });

            // SUPPORTERS
            call5.enqueue(new Callback<List<SponsorSupporter>>() {
                @Override
                public void onResponse(Call<List<SponsorSupporter>> call, Response<List<SponsorSupporter>> response) {
                    if (response.code() == 200) {

                        for (int i=0; i<response.body().size(); i++) {

                            Log.d(TAG, "SUPPORTERS - ID: " + response.body().get(i).getId());

                            db.insertSponsor(new Sponsor(
                                    response.body().get(i).getId(),
                                    String.valueOf(Html.fromHtml(response.body().get(i).getTitle().getRendered())),
                                    response.body().get(i).getLink(),
                                    response.body().get(i).getType()
                            ));

                            int id = response.body().get(i).getId();
                            String type = response.body().get(i).getType();
                            Call<Media> medias = apiInterface.getMedias(
                                    response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref()
                            );

                            medias.enqueue(new Callback<Media>() {
                                @Override
                                public void onResponse(Call<Media> call, Response<Media> response) {
                                    Log.d(TAG, "Media - Response Code: " + response.code());

                                    if (response.code() == 200) {
                                        String link = response.body().getGuid().getRendered();

                                        Call<ResponseBody> file = apiInterface.download(link);

                                        //Images
                                        file.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.isSuccessful()){

                                                    new StorageHelper(getApplicationContext())
                                                            .setDirectoryName("FOSSDAY")
                                                            .setFileName(type+id+".png")
                                                            .setExternal(false)
                                                            .saveFile(response.body());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                Log.d(TAG, "onFailure: " + t.toString());
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onFailure(Call<Media> call, Throwable t) {
                                    Log.d(TAG, "Media - onFailure: " + t);
                                }
                            });
                        }
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<SponsorSupporter>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                }
            });



        } else {
            if (prefManager.isFirstTimeLaunch()) {
                Toast.makeText(this, "No primeiro acesso, você precisa estar conectado à internet!", Toast.LENGTH_LONG).show();
                SplashActivity.this.finish();
            } else {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }

        }

    }

}
