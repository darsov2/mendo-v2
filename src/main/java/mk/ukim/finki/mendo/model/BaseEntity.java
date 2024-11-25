package mk.ukim.finki.mendo.model;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;
}
