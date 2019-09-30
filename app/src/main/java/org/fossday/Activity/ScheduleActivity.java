package org.fossday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.fossday.Adapter.LectureAdapter;
import org.fossday.Adapter.ViewHolder.LectureHolder;
import org.fossday.Database.DB;
import org.fossday.Model.Lecture;
import org.fossday.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleActivity extends AppCompatActivity implements LectureHolder.OnLectureClickListener {

    private static final String TAG = "ScheduleActivity";

    private LectureAdapter lectureAdapter;
    private List<Lecture> lectureList;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        DB db = new DB(this);
        lectureList = db.getLectures();

        lectureAdapter = new LectureAdapter(this, lectureList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lectureAdapter);
    }

    @Override
    public void onLectureClick(int position) {
        Log.d(TAG, "onLectureClick: " + position);

        Intent intent = new Intent(this, LectureDetailsActivity.class);
        intent.putExtra("title", lectureList.get(position).getName());
        intent.putExtra("description", lectureList.get(position).getDescription());
        intent.putExtra("period", lectureList.get(position).getPeriod());
        intent.putExtra("time", lectureList.get(position).getTime());
        intent.putExtra("room", lectureList.get(position).getRoom());
        intent.putExtra("speakerId", lectureList.get(position).getSpeakerId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}

