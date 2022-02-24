package activitytracker;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/activitytracker?useUnicoda=true");
            dataSource.setUser("activity");
            dataSource.setPassword("activity");
        }
        catch (SQLException sqe) {
            throw new IllegalStateException("Cannot reach Database!", sqe);
        }

        Flyway flyway = Flyway.configure().locations("db/migration/activity").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        ActivityRepository activityRepository = new ActivityRepository(dataSource);
        activityRepository.insertActivity(LocalDateTime.of(2022, 02, 23, 10, 12), "Hosszú futás", Type.RUNNING);
        //activityRepository.insertActivity(LocalDateTime.of(2022, 02, 22, 8, 22), "Biciglizés", Type.BIKING);
        System.out.println(activityRepository.findAllActivity());

    }
}
