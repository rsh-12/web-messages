package ru.jelly.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.jelly.app.entity.WebMessage;

import java.util.List;

public interface WebMessageRepository extends JpaRepository<WebMessage, Long> {

    @Query(nativeQuery = true, value = "select * from message order by id desc offset ?1")
    List<WebMessage> oldMessages(@Param("quantity") int quantity);
}
