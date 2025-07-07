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

    public Config getConfig(){
        List<Config> configs = getAll();
        if (configs.size() == 0) {
            Config c = new Config();
            c.setMaxExtension(2);
            c.setMaxExtension(10);
            return c;
        }
        return configs.get(0);
    }
}
