package com.recipiepuppy.recipiepuppy.views.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.recipiepuppy.recipiepuppy.R;
import com.recipiepuppy.recipiepuppy.contracts.IViewInteractor;
import com.recipiepuppy.recipiepuppy.model.ItemModel;
import com.recipiepuppy.recipiepuppy.presenters.ItemPresenter;
import com.recipiepuppy.recipiepuppy.views.adapters.ItemlistAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipieListActivity extends AppCompatActivity implements TextWatcher, IViewInteractor {


    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tvCancel)
    TextView tvCancel;

    private ItemlistAdapter itemlistAdapter;
    private ArrayList<ItemModel.Result> list = new ArrayList<>();
    private ItemPresenter itemPresenter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reciepe_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        itemPresenter = new ItemPresenter(this);
        itemlistAdapter = new ItemlistAdapter(list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(itemlistAdapter);
        edSearch.addTextChangedListener(this);
        handler = new Handler();
        itemPresenter.getItems(null, null);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        itemPresenter.getItems(s.toString(), handler);

    }

    @Override
    public void refreshAdapter(List<ItemModel.Result> results) {
        list.clear();
        list.addAll(results);
        itemlistAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.tvCancel)
    void onClearSearch(View v) {
        edSearch.setText(null);
        itemPresenter.getItems(null, null);
    }
}
