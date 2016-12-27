package example.com.trainschedule.apiobjects;

import android.content.Context;

import com.android.volley.Request;

import example.com.trainschedule.constants.Constants;
import example.com.trainschedule.volleylibclasses.AppRequestListener;
import example.com.trainschedule.volleylibclasses.GetScheduleServiceClass;


public class AllUser extends BaseObject implements Constants {

    private static AllUser sInstance;

    @Override
    public void clear(Context context) {

    }

    public static AllUser getInstance() {
        if (sInstance == null)
            sInstance = new AllUser();
        return sInstance;
    }

    public void getScheduleTrain(String url, AppRequestListener appRequestListener, Context context)
    {
        AppNetworkError appNetworkError = new AppNetworkError();
        GetScheduleServiceClass request = new GetScheduleServiceClass(Request.Method.GET, url, appNetworkError, Constants.ID_SCHEDULE, appRequestListener);
        sendRequest(context, appNetworkError, request, appRequestListener);
    }




}
