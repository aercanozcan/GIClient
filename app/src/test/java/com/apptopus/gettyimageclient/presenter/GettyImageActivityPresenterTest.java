package com.apptopus.gettyimageclient.presenter;

import com.apptopus.gettyimageclient.BaseTest;
import com.apptopus.gettyimageclient.FixtureUtils;
import com.apptopus.gettyimageclient.data.model.Image;
import com.apptopus.gettyimageclient.rest.RestService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;

/**
 * Created by ercanozcan on 13/08/17.
 */

public class GettyImageActivityPresenterTest extends BaseTest {


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    GettyImageActivityContract.View view;

    GettyImageActivityPresenter presenter;


    @Before
    public void setUp() throws IOException {
        commonSetUp();
        restService = new RestService(retrofitBuilder.build());
        presenter = new GettyImageActivityPresenter(restService, view);
    }

    @Test
    public void showImagesShouldBeCalledWhenResponseIsSuccessful() throws IOException {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(FixtureUtils.readFixture("successfulResponse.json")));
        presenter.fetchImages("kitties", 1, 5);
        Mockito.verify(view, Mockito.timeout(1000).times(1)).showImages(Mockito.<Image>anyList());
        Mockito.verify(view, Mockito.timeout(1000).times(1)).showProgress();
        Mockito.verify(view, Mockito.timeout(1000).times(1)).hideProgress();
    }

    @Test
    public void showProgressAndHideProgressMustBeCalledWhenErrorHappens() throws IOException {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(FixtureUtils.readFixture("successfulResponse.json")));
        presenter.fetchImages("kitties", 1, 5);
        Mockito.verify(view, Mockito.timeout(1000).times(1)).showProgress();
        Mockito.verify(view, Mockito.timeout(1000).times(1)).hideProgress();
    }


    @After
    public void tearDown() throws IOException {
        commonTearDown();
        presenter = null;
    }


}
