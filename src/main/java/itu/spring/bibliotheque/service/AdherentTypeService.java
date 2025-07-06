package itu.spring.bibliotheque.service;

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
}
