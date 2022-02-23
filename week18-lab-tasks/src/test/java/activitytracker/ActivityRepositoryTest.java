package activitytracker;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRepositoryTest {

    ActivityRepository activityRepository;
    Flyway flyway;

    @BeforeEach
    void init() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/activitytracker?useUnicoda=true");
            dataSource.setUser("activity");
            dataSource.setPassword("activity");
        }
        catch (SQLException sqe) {
            throw new IllegalStateException("Cannot reach Database!", sqe);
        }
        flyway = Flyway.configure().locations("db/migration/activity").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
        activityRepository = new ActivityRepository(dataSource);
        activityRepository.insertActivity(LocalDateTime.of(2022, 01, 12, 10, 8), "Teszt futás", Type.RUNNING);
        activityRepository.insertActivity(LocalDateTime.of(2022, 01, 22, 12, 15), "Teszt biciglizés", Type.BIKING);
    }

    @Test
    void testInsert() {
        assertEquals(2, activityRepository.findAllActivity().size());
    }

}