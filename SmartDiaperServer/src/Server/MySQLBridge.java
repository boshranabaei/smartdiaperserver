package Server;

import java.sql.*;

public class MySQLBridge {

	static final String WIN_URL = "jdbc:sqlite:/root/smartdiapersserver/SmartDiaperServer/db/sdiaper.db";

	static Baby[] babies = null;
	Connection conn;
	java.sql.Statement stmt;
	ResultSet rs;
	public static MySQLBridge msql = new MySQLBridge();

	// Establishing connection to the database
	public MySQLBridge() {
		try {
			conn = DriverManager.getConnection(WIN_URL);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("NO Connection");
		}
	}

	public synchronized Baby[] getBabies() {

		try {
			String sql = "SELECT COUNT(*) FROM babies;";
			rs = stmt.executeQuery(sql);
			babies = new Baby[rs.getInt("COUNT(*)")];

			sql = "SELECT * FROM babies;";
			rs = stmt.executeQuery(sql);
			rs.next();
			for (int i = 0; i < babies.length; i++) {
				babies[i] = new Baby();
				babies[i].userId = rs.getInt("userId");

				babies[i].firstName = rs.getString("firstName");
				babies[i].lastName = rs.getString("lastName");
				babies[i].birthYear = rs.getInt("birthYear");
				babies[i].birthMonth = rs.getInt("birthMonth");
				babies[i].birthDay = rs.getInt("birthDay");
				babies[i].status = rs.getInt("status");
				babies[i].numDiapersToday = rs.getInt("numDiapersToday");
				babies[i].image = rs.getString("image");
				rs.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return babies;
	}

	// To update the baby status
	public synchronized boolean changeBabyStatus(int[] babies_status) {
		String sql = null;
		for (int i = 0; i < 5; i++) {
			if (babies_status[i] != babies[i].status) {
				sql = "UPDATE babies SET status=" + babies_status[i];
				if (babies[i].status == 1) {
					babies[i].numDiapersToday += 1;
					sql += ", numDiapersToday=" + babies[i].numDiapersToday;
				}
				sql += " WHERE userId ==" + (i+1) + ";";
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
			babies[i].status = babies_status[i];
		}

		

		return true;

	}

	// To update the baby status
	public synchronized boolean resetDiapers() {
		String sql = "UPDATE babies SET numDiapersToday=1, status=0 WHERE 1==1;";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
