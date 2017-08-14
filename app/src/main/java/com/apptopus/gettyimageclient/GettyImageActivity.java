package com.apptopus.gettyimageclient;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apptopus.gettyimageclient.data.model.Image;
import com.apptopus.gettyimageclient.presenter.GettyImageActivityContract;
import com.apptopus.gettyimageclient.presenter.GettyImageActivityPresenter;
import com.apptopus.gettyimageclient.rest.RestService;
import com.apptopus.gettyimageclient.view.ImageAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GettyImageActivity extends AppCompatActivity implements GettyImageActivityContract.View, ImageAdapter.ItemClickListener {

    @Inject
    RestService restService;

    @BindView(R.id.recyclerView)
    RecyclerView imageList;

    @BindView(R.id.searchView)
    EditText searchView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    GettyImageActivityPresenter presenter;

    ImageAdapter imageAdapter;

    LinearLayoutManager layoutManager;

    boolean loading;
    int page = 1;
    int pageSize = 10;
    public String searchString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getty_image);
        ButterKnife.bind(this);
        ((GettyImageClientApplication) getApplication()).restServiceComponent().inject(this);
        presenter = new GettyImageActivityPresenter(restService, this);
        prepareViews();
    }

    private void prepareViews() {
        imageAdapter = new ImageAdapter();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(false);
        imageList.setLayoutManager(layoutManager);
        imageList.setAdapter(imageAdapter);
        imageAdapter.setItemClickListener(this);

        imageList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                if (totalItemCount > 0) {
                    if (!loading) {
                        if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                            loading = true;
                            presenter.fetchImages(searchString, page++, pageSize);
                        }
                    }
                }
            }
        });

        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    imageAdapter.getImages().clear();
                    page = 1;
                    searchString = v.getText().toString();
                    presenter.fetchImages(searchString, page, pageSize);
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showImages(List<Image> images) {
        loading = false;
        imageAdapter.getImages().addAll(images);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void showImageDialog(Image image) {
        Toast.makeText(this, image.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorDialog(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage() + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onItemClick(Image image) {
        showImageDialog(image);
    }

}
