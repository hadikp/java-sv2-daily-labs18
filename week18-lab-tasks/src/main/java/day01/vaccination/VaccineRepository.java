package day01.vaccination;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class VaccineRepository {

    private JdbcTemplate jdbcTemplate;

    public VaccineRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertData(String name, int age, ChronicDisease chronic, Pregnancy pregnant) {
        jdbcTemplate.update("Insert into registrations(person_name, age, chronic_disease, pregnancy) values(?,?,?,?)", name, age, chronic.name(), pregnant.name());
    }
}
