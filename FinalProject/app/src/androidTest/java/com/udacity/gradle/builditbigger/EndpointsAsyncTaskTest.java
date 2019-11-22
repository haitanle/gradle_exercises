package com.udacity.gradle.builditbigger;

import android.app.Service;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.core.util.Pair;
import androidx.test.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import javax.security.auth.callback.Callback;

import static org.junit.Assert.*;

public class EndpointsAsyncTaskTest extends TestCase {

    CountDownLatch signal = null;
    Context context;

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

//    @Override
//    protected void tearDown() throws Exception {
//
//    }


    @Test
    public void testAsyncTask() throws InterruptedException {

        context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask() {

            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (result != null){
                    Log.d(EndpointsAsyncTask.class.getSimpleName(), "RESULTS: "+result);
                    assertTrue(result.length() > 0);
                    signal.countDown();
                }
            }
        };

        testTask.execute(new Pair<Context, String>(context,"Manfred"));
        signal.await();
    }
}