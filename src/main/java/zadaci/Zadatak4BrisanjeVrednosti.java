package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Knjiga;
import model.Oblast;

import java.io.IOException;

public class Zadatak4BrisanjeVrednosti {

	static Dao<Knjiga, Integer> knjigaDao;
	static Dao <Oblast, Integer> oblastDao;

	public static void main(String[] args) {

		ConnectionSource connectionSource = null;

		try {

			connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");

			

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (connectionSource != null) {
				try {
					connectionSource.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
