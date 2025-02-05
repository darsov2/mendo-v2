package mk.ukim.finki.mendo.repository;

import mk.ukim.finki.mendo.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}