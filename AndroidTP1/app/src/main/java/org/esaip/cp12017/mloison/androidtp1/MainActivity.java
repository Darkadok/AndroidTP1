package org.esaip.cp12017.mloison.androidtp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oc.hnapp.android.HNQueryTask;

public class MainActivity extends AppCompatActivity {

    private HNQueryTask _task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HNArticlesAdapter adapter = new HNArticlesAdapter();
        recyclerView.setAdapter(adapter);

        _task = new HNQueryTask(adapter, 80, 1);
        _task.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _task.cancel(true);
    }
}