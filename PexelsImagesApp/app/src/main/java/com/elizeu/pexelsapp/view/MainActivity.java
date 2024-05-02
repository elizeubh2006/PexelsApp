package com.elizeu.pexelsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elizeu.pexelsapp.viewmodel.ListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pexelsapp.R;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.pxImageList)
    RecyclerView pxImagesList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    private ListViewModel viewModel;
    private PexelsListAdapter adapter = new PexelsListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        pxImagesList.setLayoutManager(new LinearLayoutManager(this));
        pxImagesList.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            refreshLayout.setRefreshing(false);
        });
		
        observerViewModel();
    }

    private void observerViewModel() {
        viewModel.pxImages.observe(this, pxImageModels -> {
            if(pxImageModels != null){
                pxImagesList.setVisibility(View.VISIBLE);
                adapter.updatepxImages(pxImageModels);
            }
        });
        viewModel.pexelsImageLoadError.observe(this, isError -> {
            if(isError != null){
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if(isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading) {
                    listError.setVisibility(View.GONE);
                    pxImagesList.setVisibility(View.GONE);
                }
            }
        });										  
    }
}
