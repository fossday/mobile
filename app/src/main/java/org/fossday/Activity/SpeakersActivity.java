package org.fossday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.fossday.Adapter.SpeakerAdapter;
import org.fossday.Adapter.ViewHolder.SpeakerHolder;
import org.fossday.Database.DB;
import org.fossday.Model.Speaker;
import org.fossday.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeakersActivity extends AppCompatActivity implements SpeakerHolder.OnSpeakerClickListener {

    private static final String TAG = "SpeakersActivity";

    private SpeakerAdapter speakerAdapter;
    private List<Speaker> speakerList;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_speakers);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        DB db = new DB(this);
        speakerList = db.getSpeakers();

        speakerAdapter = new SpeakerAdapter(this, speakerList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(speakerAdapter);
    }

    @Override
    public void onSpeakerClick(int position) {
        Log.d(TAG, "onSpeakerClick: " + position);
        Intent intent = new Intent(this, SpeakerDetailsActivity.class);
        intent.putExtra("id", speakerList.get(position).getId());
        intent.putExtra("name", speakerList.get(position).getName());
        intent.putExtra("description", speakerList.get(position).getDescription());
//        intent.putExtra("");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}

