package playerprofile;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import game.GameJpanel;

public class WriteProfileMySQL {
	static final String url = "jdbc:mysql://localhost:3306/profile?useSSL=false";
	static final String user = "root";
	static final String password = "1234566";
	public Connection conn = null;
	public int yearNow, monthNow, dayNow, hoursNow, minutesNow, secondsNow, score;

	public WriteProfileMySQL() {
	}

	public void insert() {
		if (GameJpanel.map.live == -1 || (GameJpanel.map.passLevel == true &&
				GameJpanel.level == 1)) {
			Calendar cld = Calendar.getInstance();
			yearNow = cld.get(Calendar.YEAR);
			monthNow = cld.get(Calendar.MONTH) + 1;
			dayNow = cld.get(Calendar.DAY_OF_MONTH);
			hoursNow = cld.get(Calendar.HOUR_OF_DAY);
			minutesNow = cld.get(Calendar.MINUTE);
			secondsNow = cld.get(Calendar.SECOND);
			score = GameJpanel.map.score;
		}
		String txt1 = String.valueOf(yearNow) + "/" + String.valueOf(monthNow) + "/" + String.valueOf(dayNow);
		String txt2 = String.valueOf(hoursNow) + ":" + String.valueOf(minutesNow) + ":" + String.valueOf(secondsNow);
		String txt3 = String.valueOf(score);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO historyplay VALUES(?,?,?)");
			ps.setString(1, txt1);
			ps.setString(2, txt2);
			ps.setString(3, txt3);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
