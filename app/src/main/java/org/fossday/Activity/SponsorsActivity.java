package org.fossday.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.fossday.Adapter.SponsorAdapter;
import org.fossday.Adapter.ViewHolder.SponsorHolder;
import org.fossday.Database.DB;
import org.fossday.Model.Sponsor;
import org.fossday.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SponsorsActivity extends AppCompatActivity implements SponsorHolder.OnSponsorClickListener {

    private static final String TAG = "SponsorsActivity";

    private SponsorAdapter sponsorAdapter;
    private List<Sponsor> sponsorList;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sponsors);
        ButterKnife.bind(this);

        DB db = new DB(this);
        sponsorList = db.getSponsors();

        sponsorAdapter = new SponsorAdapter(this, sponsorList, this);

//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sponsorAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSponsorClick(int position) {
        Log.d(TAG, "onSponsorClick: " + position);
    }
}

