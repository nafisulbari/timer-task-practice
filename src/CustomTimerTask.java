import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CustomTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("A Specific task from class, Working at: "+ new Date());
    }


    public void runAtServerStartup(TimerTask task){
        // Setting time of execution
        Calendar timeToExecuteTask = Calendar.getInstance();
        timeToExecuteTask.set(Calendar.DAY_OF_WEEK, 4);
        timeToExecuteTask.set(Calendar.HOUR_OF_DAY, 11);
        timeToExecuteTask.set(Calendar.MINUTE, 32);
        timeToExecuteTask.set(Calendar.SECOND, 50);
        Date date = timeToExecuteTask.getTime();


        // Bugfix of overflowing task execution if server restarts and current time has passed scheduled time
        Date now = new Date();
        int comparison = date.compareTo(now);
        Timer t = new Timer();
        if (comparison < 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            // Setting time of execution + 7 days if current time has passed scheduled time
            c.add(Calendar.DATE, 7);
            date = c.getTime();
        }

        /*
         * Setting period/intervals of task execution in ms
         * Example: 1000 * 5 = 5 seconds
         *          1000 * 60 * 60 * 24 = 1 day
         *          1000 * 60 * 60 * 24 * 7 = 7 days
         * */
        t.scheduleAtFixedRate(task, date, 1000 * 10);
    }
}
