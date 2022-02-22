package day02;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class BookRepository {

    private JdbcTemplate jdbcTemplate;

    public BookRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertBook(String writer, String title, int price, int pieces) {
        jdbcTemplate.update("Insert into books(writer, title, price, pieces) values(?,?,?,?)", writer, title, price, pieces);
    }

    public List<Book> findBooksWriter(String prefix) {
        return jdbcTemplate.query("Select * from books where writer like ?",
                (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("writer"), rs.getString("title"), rs.getInt("price"), rs.getInt("pieces"))
                , prefix + "%");
    }

    public void updatePieces(Long id, int pieces) {
        jdbcTemplate.update("Update books Set pieces = pieces + ? where id = ?", pieces, id);
    }
}
