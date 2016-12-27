package example.com.trainschedule.parsingclasses;


import java.util.List;

public class ReturnDataSchedule
{

    String TrainName;
    String TrainNo;
    List<Schedule> Schedule;



    public class Schedule
    {

        String StopNumber;
        String StationName;
        String Distance;
        String Latitude;
        String Longitude;


        public String getStopNumber() {
            return StopNumber;
        }

        public String getStationName() {
            return StationName;
        }

        public String getDistance() {
            return Distance;
        }

        public String getLatitude() {
            return Latitude;
        }

        public String getLongitude() {
            return Longitude;
        }
    }


    public String getTrainName() {
        return TrainName;
    }

    public String getTrainNo() {
        return TrainNo;
    }

    public List<ReturnDataSchedule.Schedule> getSchedule() {
        return Schedule;
    }
}
