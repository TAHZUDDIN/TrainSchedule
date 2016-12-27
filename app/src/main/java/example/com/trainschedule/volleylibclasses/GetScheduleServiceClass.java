package example.com.trainschedule.volleylibclasses;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import example.com.trainschedule.parsingclasses.ReturnDataSchedule;
import example.com.trainschedule.utilities.JSONUtils;


public class GetScheduleServiceClass extends BaseTask<JSONObject>
{

    public String TAG = "GetScheduleServiceClass";
    public GetScheduleServiceClass(int method, String url, Response.ErrorListener listener, String requestTag, AppRequestListener requestListener)
    {
        super(method, url, listener, requestTag, requestListener);
        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");


    }

    @Override
    public void processData() {
        sendMessage();
    }


    ReturnDataSchedule returnedDataNewsUpdate;


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        statusCode = response.statusCode;
        String responseString = new String(response.data);
        Log.i(TAG, "response:" + responseString);
        returnedDataNewsUpdate = new Gson().fromJson(responseString, ReturnDataSchedule.class);
        return Response.success(
                JSONUtils.getJSONObject(responseString),
                getCacheEntry());
    }


    public ReturnDataSchedule getDataObject() {
        return returnedDataNewsUpdate;
    }




    @Override
    protected void deliverResponse(JSONObject response) {

        this.setResponse(response);
        RequestPoolManager.getInstance().executeRequest(this);

    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }
}
