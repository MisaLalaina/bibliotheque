package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.models.Sanction;
import itu.spring.bibliotheque.repository.SanctionRepository;
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

    public List<Sanction> getByState(String state) {
        return sanctionRepository.findByState(state);
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
