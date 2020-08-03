package by.mmkle.telegram.dao.service;

import by.mmkle.bean.Client;
import by.mmkle.telegram.dao.repository.TelegramRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TelegramService {

    private final TelegramRepository telegramRepository;

    public TelegramService(TelegramRepository telegramRepository) {
        this.telegramRepository = telegramRepository;
    }

    public List<Client> clientList() {
        return telegramRepository.findAll();
    }

    public void saveClient(Client client) {
        telegramRepository.save(client);
    }

    public Client getClient(Long idChat) {
        return telegramRepository.getByIdChat(idChat);
    }

    public Client buildNewClient(Long idChat) {
        return new Client(idChat, LocalDate.now(),LocalDate.now().plusMonths(1), "Test time" );
    }

}
