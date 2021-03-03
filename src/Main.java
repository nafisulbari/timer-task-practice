import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*
        * Approach 1
        * */
        TimerTask weeklyEmail = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Weekly EMAIL TASK, Working at: " + new Date());
            }
        };

        TimerTask dailyEmail = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Daily EMAIL TASK, Working at: " + new Date());
            }
        };



        // Setting time of execution
        Calendar timeToExecuteTask = Calendar.getInstance();
        timeToExecuteTask.set(Calendar.DAY_OF_WEEK, 4);
        timeToExecuteTask.set(Calendar.HOUR_OF_DAY, 8);
        timeToExecuteTask.set(Calendar.MINUTE, 34);
        timeToExecuteTask.set(Calendar.SECOND, 40);
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
        t.scheduleAtFixedRate(weeklyEmail, date, 1000 * 60 * 60 * 24 * 7);
        t.scheduleAtFixedRate(dailyEmail, date, 1000 * 60 * 60 * 24);



        /*
        * Approach 2
        * */
        CustomTimerTask customTimerTask = new CustomTimerTask();
        customTimerTask.runAtServerStartup(customTimerTask);

        //manual run
        //customTimerTask.run();




    }


}
