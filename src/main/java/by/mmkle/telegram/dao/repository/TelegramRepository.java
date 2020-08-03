package by.mmkle.telegram.dao.repository;


import by.mmkle.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TelegramRepository extends JpaRepository<Client, Long> {
    List<Client> findAll();
    Client getByIdChat(Long IdChat);
}
