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

      /**
     * Check if the adherent has an active sanction on the given reference date.
     * @param adherentId the adherent's id
     * @param refDate the date to check
     * @return true if a sanction exists for the adherent on refDate, false otherwise
     */
    public boolean checkSanction(Integer adherentId, java.sql.Date refDate) {
        List<Sanction> sanctions = sanctionRepository.findByAdherentId(adherentId);
        for (Sanction s : sanctions) {
            if (s.getFromDate() != null && s.getToDate() != null &&
                !refDate.before(s.getFromDate()) && !refDate.after(s.getToDate())) {
                return true;
            }
        }
        return false;
    }
}
