package example.com.trainschedule.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.trainschedule.parsingclasses.ReturnDataSchedule;
import example.com.trainschedule.utilities.ScheduleApplicationClass;

public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder>
{

    ReturnDataSchedule returnDataReddit;
    public static boolean hindiOrEnglish  = false;

    Integer sizeOfListToShow;
    Context mContext;
    ViewHolder viewHolderToUseInOtherPlaces;





    public static void setToHindiBooleanTrue(boolean trueValue)
    {
        hindiOrEnglish = trueValue;
    }

    public static void setToEnglishBooleanFalse(boolean falseValue)
    {
        hindiOrEnglish = falseValue;
    }


    public ScheduleRecyclerAdapter(ReturnDataSchedule returnedDataNewsUpdate)
    {
        this.returnDataReddit = returnedDataNewsUpdate;
        mContext = ScheduleApplicationClass.getContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflating_layout_news, parent, false); //Inflating the layout
//        ViewHolder vhItem = new ViewHolder(v, viewType); //Creating ViewHolder and passing the object of type view
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {





    }



    @Override
    public int getItemCount()
    {

       return 3;


    }




        public class ViewHolder extends RecyclerView.ViewHolder
        {

            ImageView  imageViewNews;
            TextView textViewNewsTitle, textViewComments, textViewNewsPublishedBy;
            View linerLayotParntNewsInflating;


            public ViewHolder(View itemView, int ViewType)
            {
                super(itemView);

            }
        }
}
