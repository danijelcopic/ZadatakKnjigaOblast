package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Knjiga;
import model.Oblast;

import java.io.IOException;
import java.util.Date;

public class Zadatak3IzmenaVrednosti {

	static Dao<Knjiga, Integer> knjigaDao;
	static Dao <Oblast, Integer> oblastDao;

	public static void main(String[] args) {

		ConnectionSource connectionSource = null;

		try {

			connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");

			knjigaDao = DaoManager.createDao(connectionSource, Knjiga.class);
			oblastDao = DaoManager.createDao(connectionSource, Oblast.class);

			TableUtils.clearTable(connectionSource, Knjiga.class);
			TableUtils.clearTable(connectionSource, Oblast.class);

			Knjiga knjiga1 = new Knjiga("Java programiranje", 650, new Date());
			knjigaDao.create(knjiga1);

			Knjiga knjiga2 = new Knjiga("Android programiranje", 500, new Date());
			knjigaDao.create(knjiga2);


			Oblast oblast1 = new Oblast("Uvod", 2);
			oblast1.setKnjiga(knjiga1);
			oblastDao.create(oblast1);

			Oblast oblast2 = new Oblast("Naredbe", 10);
			oblast2.setKnjiga(knjiga1);
			oblastDao.create(oblast2);

			Oblast oblast3 = new Oblast("Aritmeticki operatori", 20);
			oblast3.setKnjiga(knjiga1);
			oblastDao.create(oblast3);

			Oblast oblast4 = new Oblast("Android operativni sistem", 2);
			oblast4.setKnjiga(knjiga2);
			oblastDao.create(oblast4);

			Oblast oblast5 = new Oblast("Activity klasa", 30);
			oblast5.setKnjiga(knjiga2);
			oblastDao.create(oblast5);



			for(Oblast o : oblastDao.queryForAll()) {
				System.out.println(o);
			}

			Oblast oblastZaIzmenu = oblastDao.queryForId(oblast5.getId());
			oblastZaIzmenu.setPocetnaStrana(35);
			oblastDao.update(oblastZaIzmenu);


			for(Oblast o : oblastDao.queryForAll()) {
				System.out.println(o);
			}


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
