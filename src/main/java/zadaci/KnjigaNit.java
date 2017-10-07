package zadaci;

import com.j256.ormlite.dao.Dao;
import model.Knjiga;

import java.util.Random;

public class KnjigaNit extends Thread {


    static Dao<Knjiga, Integer> knjigaDao;

    private String imeClana;
    private Knjiga knjiga;

    public KnjigaNit() {
    }

    public KnjigaNit(String imeClana, Knjiga knjiga) {
        this.imeClana = imeClana;
        this.knjiga = knjiga;
    }


    @Override
    public void run() {

        Random random = new Random();

        System.out.println("Clan " + imeClana + " trazi knjigu " + knjiga.getNaslov());

        do {
            synchronized (Knjiga.prisutna) {
                if (Knjiga.prisutna) {
                    Knjiga.prisutna = false;
                }

                System.out.println("Clan " + imeClana + " je pozajmio knjigu " + knjiga.getNaslov());

                try {
                    sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
        while (true);


    }
}

