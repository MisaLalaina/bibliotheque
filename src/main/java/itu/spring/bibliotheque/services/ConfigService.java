package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.Config;
import itu.spring.bibliotheque.repositories.ConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigService {
    @Autowired
    private ConfigRepository configRepository;

    public List<Config> getAll() {
        return configRepository.findAll();
    }

    public Optional<Config> getById(Integer id) {
        return configRepository.findById(id);
    }

    public Config save(Config config) {
        return configRepository.save(config);
    }

    public void delete(Integer id) {
        configRepository.deleteById(id);
    }
}
