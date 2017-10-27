package org.esaip.cp12017.mloison.androidtp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.oc.hnapp.android.HNQueryTask;

public class MainActivity extends AppCompatActivity {

    private HNQueryTask _task = null;
    HNArticlesAdapter _adapter;
    RecyclerView _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creation du RecyclerView
        _recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //creation de l'HNArticle Adapter
        _adapter = new HNArticlesAdapter();
        _recyclerView.setAdapter(_adapter);
        getArticles("");


        final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
        _adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
            }
        });

        final EditText textBox = (EditText) findViewById(R.id.editText);
        textBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getArticles(textBox.getText().toString() );
            }
            });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _task.cancel(true);
    }


    private void getArticles( String search){
        if (search.isEmpty()){
            _task = new HNQueryTask(_adapter, 80, 1);
        }else {
            _task = new HNQueryTask(_adapter, search, 80, 1);
        }
        _task.execute();
    }
}