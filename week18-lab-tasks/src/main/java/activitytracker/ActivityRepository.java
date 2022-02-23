package activitytracker;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository {

    private JdbcTemplate jdbcTemplate;

    public ActivityRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertActivity(LocalDateTime startTime, String description, Type type) {
        Timestamp startT = Timestamp.valueOf(startTime);
        jdbcTemplate.update("Insert into activities(start_time, description, activity_type) values(?,?,?)", startT, description, type.name());
    }

    public List<Activity> findAllActivity() {
        return jdbcTemplate.query("Select * from activities", (rs, rowNum)
           -> new Activity(rs.getLong("id"), rs.getTimestamp("start_time").toLocalDateTime(), rs.getString("description"), Type.valueOf(rs.getString("activity_type"))));
    }
}
