package example.com.trainschedule.activities;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.trainschedule.R;
import example.com.trainschedule.apiobjects.AllUser;
import example.com.trainschedule.constants.Constants;
import example.com.trainschedule.parsingclasses.ReturnDataSchedule;
import example.com.trainschedule.volleylibclasses.AppRequestListener;
import example.com.trainschedule.volleylibclasses.BaseTask;
import example.com.trainschedule.volleylibclasses.GetScheduleServiceClass;

public class MainActivity extends AppCompatActivity implements AppRequestListener, Constants
                                            , View.OnClickListener{

    String TAG = "MainActivity";
    ReturnDataSchedule returnDataSchedule;
    FrameLayout framLstationParentLeft,
                frameLayoutPointer,
                framLstationParentRight;
    LayoutInflater layoutInflater;

    CardView button_CardView;


    int heightLLparentStationsLeft,
            heightLLparentStationsRight,
            totalDistance,
            heightFrmLyOutpointer;
    List<Integer> particularStationDistance;


    EditText latitudeET,
             longitudeET;

    TextView toolbarTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = LayoutInflater.from(this);
        particularStationDistance = new ArrayList<>();

        toolbarTV = (TextView)findViewById(R.id.id_titleTV_toolbr);

        framLstationParentLeft = (FrameLayout) findViewById(R.id.id_FrmLay_StationsLeft);
        framLstationParentRight = (FrameLayout) findViewById(R.id.id_FrmLay_StationsRight);
        frameLayoutPointer = (FrameLayout) findViewById(R.id.id_FF_pointer);

        button_CardView = (CardView) findViewById(R.id.id_cardView_button);
        button_CardView.setOnClickListener(this);

        latitudeET = (EditText)findViewById(R.id.id_latitude_ET);
        longitudeET = (EditText)findViewById(R.id.id_longitude_ET);



        ScheduleAPI();
    }

    @Override
    public <T> void onRequestStarted(BaseTask<T> request) {

    }

    @Override
    public <T> void onRequestCompleted(BaseTask<T> request) {


        if (((GetScheduleServiceClass) request).getDataObject() != null) {
            returnDataSchedule = ((GetScheduleServiceClass) request).getDataObject();
            toolbarTV.setText("Train Tracker ---- "+returnDataSchedule.getTrainNo());
            InflateStationName();

        }


    }

    @Override
    public <T> void onRequestFailed(BaseTask<T> request) {

    }


    public void ScheduleAPI() {
        String url = "http://yosoku.confirmtkt.com/api/trains/schedulewithdetails?trainNo=12345";
        AllUser.getInstance().getScheduleTrain(url, MainActivity.this, MainActivity.this);
    }


    public void InflateStationName()
    {
        heightLLparentStationsLeft = framLstationParentLeft.getHeight();
        heightLLparentStationsRight = framLstationParentRight.getHeight();
        String tempDistance = returnDataSchedule.getSchedule().get(returnDataSchedule.getSchedule()
                .size() - 1).getDistance();
        totalDistance = Integer.parseInt(tempDistance);


        for (int i = 0; i < returnDataSchedule.getSchedule().size(); i++) {
            int distance;
            ReturnDataSchedule.Schedule scd = returnDataSchedule.getSchedule().get(i);
            String tempParDis = scd.getDistance();
            if (tempParDis.equals(""))
                tempParDis = "0";
            particularStationDistance.add(Integer.parseInt(tempParDis));
            distance = getDistanceBetStations(particularStationDistance.get(i), heightLLparentStationsLeft);
            View view = layoutInflater.inflate(R.layout.station_layout, framLstationParentLeft, false);
            ((TextView) view.findViewById(R.id.id_station_nameTV)).setText(scd.getStationName());

            View viewRight = layoutInflater.inflate(R.layout.station_layout_right, framLstationParentRight, false);
            ((TextView) viewRight.findViewById(R.id.id_station_nameTVRight)).setText(scd.getStationName());


            if(i%2==0)
            {
                framLstationParentLeft.addView(view);
                if(i != 0 && i == returnDataSchedule.getSchedule().size()-2 || i == returnDataSchedule.getSchedule().size()-1 )
                    view.setTranslationY(distance - 30);
                else if(i != 0)
                    view.setTranslationY(distance - 20);
                else
                    view.setTranslationY(distance);
            }

            else
            {

                framLstationParentRight.addView(viewRight);

                if(i != 0 && i == returnDataSchedule.getSchedule().size()-2 || i == returnDataSchedule.getSchedule().size()-1 )
                    viewRight.setTranslationY(distance - 30);
                else if(i != 0)
                    viewRight.setTranslationY(distance - 20);
                else
                    viewRight.setTranslationY(distance);

            }

        }
    }


    public Integer getDistanceBetStations(Integer particularDistanceStation, int vHeight) {
        return particularDistanceStation * vHeight / totalDistance;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_cardView_button:
                DoLatLongThing();
                break;

            default:
                break;

        }

    }



    public void DoLatLongThing()
    {

        float distanceFlt;

        String lattitude =latitudeET.getText().toString();
        String longitude =longitudeET.getText().toString();


        if(!lattitude.equals("")&&!longitude.equals(""))
        {
            latitudeET.setText("");
            longitudeET.setText("");
            // Call the function  to get Distance between Locatios
            distanceFlt = getDistanceInMiles(lattitude,longitude);
            Log.d(TAG,"LatLongDis==="+distanceFlt*1.609344);

            Double x = distanceFlt*1.609344;
            InflatePointerLayout(x.intValue());
        }
        else
        {
            Toast.makeText(this,"Wrong input", Toast.LENGTH_SHORT).show();
        }

    }




    private float getDistanceInMiles(String lat,String lng) {

        double lat1 =  Double.parseDouble(returnDataSchedule.getSchedule().get(0).getLatitude());
        double lng1 = Double.parseDouble(returnDataSchedule.getSchedule().get(0).getLongitude());
        double lat2 = Double.parseDouble(lat);
        double lng2 = Double.parseDouble(lng);
        float [] dist = new float[1];
        Log.i("destination coordinates", "Latitude:" + lat2 + ", Longitude: " + lng2);
        Location.distanceBetween(lat1, lng1, lat2, lng2, dist);
        return dist[0] * 0.000621371192f;
    }





    // Inflate Poiinter Layout
    public void InflatePointerLayout(Integer distance)
    {
        heightFrmLyOutpointer = frameLayoutPointer.getHeight();
        Integer pointerDis = getDistanceBetStations(distance,heightFrmLyOutpointer);

        if(pointerDis >frameLayoutPointer.getHeight())
            Toast.makeText(MainActivity.this,"Outside range",Toast.LENGTH_SHORT).show();

        View view = layoutInflater.inflate(R.layout.view_pointer_layout, frameLayoutPointer, false);
        frameLayoutPointer.removeAllViews();
        frameLayoutPointer.addView(view);
        view.setTranslationY(pointerDis);

    }




}
