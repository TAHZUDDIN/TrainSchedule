package example.com.trainschedule.utilities;

import android.app.Application;
import android.content.Context;


public class ScheduleApplicationClass extends Application
{


    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();

        ScheduleApplicationClass.context = getApplicationContext();
    }


    public static Context getContext()
    {
        return context;
    }





}
