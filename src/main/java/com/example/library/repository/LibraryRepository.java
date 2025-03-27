package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface LibraryRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
}