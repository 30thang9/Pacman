package playerprofile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import game.GameJpanel;

public class WriteProfileTXT {
    public int yearNow, monthNow, dayNow, hoursNow, minutesNow, secondsNow,score;

    public WriteProfileTXT() {
        // Write();
    }

    public void Write() {
        if (GameJpanel.map.live == -1 || (GameJpanel.map.passLevel == true && GameJpanel.level == 1)) {
            Calendar cld = Calendar.getInstance();
            yearNow = cld.get(Calendar.YEAR);
            monthNow = cld.get(Calendar.MONTH) + 1;
            dayNow = cld.get(Calendar.DAY_OF_MONTH);
            hoursNow = cld.get(Calendar.HOUR_OF_DAY);
            minutesNow = cld.get(Calendar.MINUTE);
            secondsNow = cld.get(Calendar.SECOND);
            score=GameJpanel.map.score;
        }
        String txt1 = String.valueOf(yearNow) + "/" + String.valueOf(monthNow) + "/" + String.valueOf(dayNow);
        String txt2 = String.valueOf(hoursNow) + ":" + String.valueOf(minutesNow) + ":" + String.valueOf(secondsNow);
        String txt3 = String.valueOf(score);
        String txt = txt1 + "       " + txt2 + "           " + txt3;
        try {
            FileWriter fw = new FileWriter("resources/playerprofile/historyPlayer.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(txt);
            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
