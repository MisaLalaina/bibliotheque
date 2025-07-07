package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.Sanction;
import itu.spring.bibliotheque.repositories.SanctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SanctionService {
    @Autowired
    private SanctionRepository sanctionRepository;

    public List<Sanction> getAll() {
        return sanctionRepository.findAll();
    }

    public List<Sanction> getByAdherentId(Integer adherentId) {
        return sanctionRepository.findByAdherentId(adherentId);
    }

    public Optional<Sanction> getById(Integer id) {
        return sanctionRepository.findById(id);
    }

    public Sanction save(Sanction sanction) {
        return sanctionRepository.save(sanction);
    }

    public void delete(Integer id) {
        sanctionRepository.deleteById(id);
    }
}
