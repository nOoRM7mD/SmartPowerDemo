package com.example.lenovo.smartpowerdemo;

/**
 * Created by lenovo on 5/29/2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/*
public class FCAsyncTask extends AsyncTask<FCRunnable, Void, Long> {

    public interface FCAsyncTaskFinishListener {
        public void onTaskFinished(boolean finishSuccess);
    }

    private Context context;
    private ProgressDialog operationProgressDialog;
    private FCAsyncTaskFinishListener mListener;
    boolean needToast = true;

    public FCAsyncTask(Context context, FCAsyncTaskFinishListener asyncTaskFinishListener, boolean needToast) {
        this.context = context;
        this.mListener = asyncTaskFinishListener;
        this.needToast = needToast;
    }


    @Override
    protected Long doInBackground(FCRunnable... fcRunnables) {
//run runnable code in background thread
        long result = 1;
        for (FCRunnable runnable : fcRunnables) {
            if (runnable != null) {
                result = runnable.run();
            }
            if (result <= 0)
                break;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {
        if (operationProgressDialog != null)
            operationProgressDialog.dismiss();
        operationProgressDialog = null;

        String toastTxt = "";
        boolean resultFlag = false;
        if (result > 0) {
            toastTxt = "Operation succeeded";
            resultFlag = true;
        } else if (result == CommunicationConstants.OPERATION_FAILED) {
            toastTxt = "Operation failed";
            resultFlag = false;
        } else if (result == CommunicationConstants.OPERATION_FAILED_DUPLICATE_NAME) {
            toastTxt = "Operation failed,Name is duplicated";
            resultFlag = false;
        } else if (result == CommunicationConstants.OPERATION_FAILED_WRONG_PASSWORD || result == CommunicationConstants.OPERATION_FAILED_WRONG_USERNAME) {
            toastTxt = "Operation failed,Wrong username or password";
            resultFlag = false;
        } else if (result == CommunicationConstants.OPERATION_FAILED_INVALID_IP) {
            toastTxt = "Invalid IP,Can't connect to server";
            resultFlag = false;
        } else if (result == CommunicationConstants.OPERATION_FAILED_USER_BLOCK) {
            toastTxt = "Operation failed,User is blocked";
            resultFlag = false;
        }


        Toast.makeText(context, toastTxt, Toast.LENGTH_LONG).show();

    }
}
*/
