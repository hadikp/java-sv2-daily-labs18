package day02;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/bookstore?useUnicoda=true");
            dataSource.setUser("root");
            dataSource.setPassword("ciprush11");
        }
        catch (SQLException sqe) {
            throw new IllegalStateException("Cannot reach Database!", sqe);
        }

        Flyway flyway = Flyway.configure().locations("db/migration/bookstore").dataSource(dataSource).load();
        flyway.migrate();
        BookRepository bookRepository = new BookRepository(dataSource);
        //bookRepository.insertBook("Fekete István", "Vuk", 3400, 20);
        //bookRepository.insertBook("Fekete István", "Téli berek", 3200, 30);
        System.out.println(bookRepository.findBooksWriter("Fekete"));
        bookRepository.updatePieces(1L, 55);

    }
}
