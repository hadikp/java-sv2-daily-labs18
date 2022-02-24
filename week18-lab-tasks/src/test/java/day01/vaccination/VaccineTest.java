package day01.vaccination;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTest {

    Vaccine vaccine;
    VaccineRepository vaccineRepository;
    MariaDbDataSource dataSource;
    Flyway flyway;

    @BeforeEach
    public void init() {
        dataSource = new MariaDbDataSource();
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/vaccinate?useUnicode=true");
            dataSource.setUser("vaccine");
            dataSource.setPassword("vaccine");

        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not connect to database", sqle);
        }
        flyway = Flyway.configure().locations("db/migration/vaccinate").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
        vaccineRepository = new VaccineRepository(dataSource);
        vaccineRepository.insertData("Kiss József", 45, ChronicDisease.NO, Pregnancy.NO);
        vaccineRepository.insertData("Nagy Eleonóra", 35, ChronicDisease.NO, Pregnancy.YES);
        vaccineRepository.insertData("Szép Virág", 65, ChronicDisease.YES, Pregnancy.NO);
        vaccineRepository.insertData("Gárdos Géza", 25, ChronicDisease.YES, Pregnancy.NO);
        vaccineRepository.insertData("Szabó Veronika", 32, ChronicDisease.NO, Pregnancy.NO);
        vaccineRepository.insertData("Vedres Károly", 53, ChronicDisease.NO, Pregnancy.NO);
        vaccineRepository.insertData("Ökrös Gizella", 33, ChronicDisease.NO, Pregnancy.YES);
        vaccineRepository.insertData("Fekete Dávid", 62, ChronicDisease.NO, Pregnancy.NO);
        vaccineRepository.insertData("Bíró Rita", 29, ChronicDisease.NO, Pregnancy.NO);
        vaccineRepository.insertData("Baráth Zita", 58, ChronicDisease.YES, Pregnancy.NO);
        vaccineRepository.insertData("Kovács Tamás", 42, ChronicDisease.YES, Pregnancy.NO);
        vaccineRepository.insertData("Török István", 81, ChronicDisease.NO, Pregnancy.NO);
        vaccineRepository.insertData("Fehér Ágnes", 34, ChronicDisease.YES, Pregnancy.YES);
        vaccineRepository.insertData("Bánkúzi Bendegúz", 93, ChronicDisease.YES, Pregnancy.NO);


    }

    @Test
    void testReadData() {
        System.out.println();
    }

    /*@Test
    public void testPfizer() {
        vaccine = new Pfizer(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        assertEquals(15, vaccinationList.size());
        assertEquals("Nagy Eleonóra", vaccinationList.get(0).getName());
        assertEquals("Németh Béla", vaccinationList.get(3).getName());
        assertEquals("Kiss József", vaccinationList.get(6).getName());
        assertEquals("Kovács Tamás", vaccinationList.get(14).getName());
    }

    @Test
    public void testAstraZeneca() {
        vaccine = new AstraZeneca(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        assertEquals(12, vaccinationList.size());
        assertEquals("Szép Virág", vaccinationList.get(0).getName());
        assertEquals("Bánkuti Bendegúz", vaccinationList.get(4).getName());
        assertEquals("Németh Béla", vaccinationList.get(5).getName());
        assertEquals("Bíró Rita", vaccinationList.get(11).getName());
    }

    @Test
    public void testSinoPharm() {
        vaccine = new SinoPharm(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        assertEquals(7, vaccinationList.size());
        assertEquals("Kiss József", vaccinationList.get(0).getName());
        assertEquals("Szabó Veronika", vaccinationList.get(1).getName());
        assertEquals("Németh Béla", vaccinationList.get(5).getName());
        assertEquals("Török István", vaccinationList.get(6).getName());
    }

    @AfterEach
    public void destruct() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String dropHousePoints = "DROP TABLE IF EXISTS registrations";
            Statement statement = connection.createStatement();
            statement.execute(dropHousePoints);
        }
    }*/

}