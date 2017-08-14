package com.apptopus.gettyimageclient.presenter;

import com.apptopus.gettyimageclient.data.model.Image;

import java.util.List;

/**
 * Created by ercanozcan on 09/08/17.
 */

public interface GettyImageActivityContract {

    public interface View {
        public void showImages(List<Image> images);

        public void showImageDialog(Image image);

        public void showErrorDialog(Throwable th);

        public void showProgress();

        public void hideProgress();

    }

    public interface Operations {
        public void fetchImages(String phrase, int page, int pageSize);

    }

}
