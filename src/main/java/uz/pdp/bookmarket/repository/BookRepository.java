package uz.pdp.bookmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.bookmarket.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByNameAndAuthor(String name, String author);

    boolean existsById(Long id);
}
