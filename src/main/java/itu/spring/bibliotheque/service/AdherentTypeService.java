package itu.spring.bibliotheque.service;

import itu.spring.bibliotheque.model.Adherent;
import itu.spring.bibliotheque.model.AdherentInfo;
import itu.spring.bibliotheque.model.AdherentType;
import itu.spring.bibliotheque.repository.AdherentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentTypeService {
    @Autowired
    private AdherentTypeRepository adherentTypeRepository;

    public List<AdherentType> findAll() {
        return adherentTypeRepository.findAll();
    }

    public Optional<AdherentType> findById(Integer id) {
        return adherentTypeRepository.findById(id);
    }

    public AdherentInfo createInfo(Adherent adherent, AdherentType adherentType) {
        AdherentInfo info = new AdherentInfo();
        info.setAvailableDuration(adherentType.getDefaultDuration());
        info.setAvailableQuote(adherentType.getDefaultQuote());
        info.setAdherent(adherent);
        return info;
    }

    public AdherentInfo createInfo(Adherent adherent) {
        return createInfo(adherent, adherent.getAdherentType());
    }
}
