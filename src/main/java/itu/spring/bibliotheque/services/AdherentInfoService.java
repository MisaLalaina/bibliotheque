package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.AdherentInfo;
import itu.spring.bibliotheque.repositories.AdherentInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdherentInfoService {
    @Autowired
    private AdherentInfoRepository adherentInfoRepository;

    public List<AdherentInfo> findAll() {
        return adherentInfoRepository.findAll();
    }

    public Optional<AdherentInfo> findById(Integer id) {
        return adherentInfoRepository.findById(id);
    }
    public AdherentInfo save(AdherentInfo adherentInfo) {
        return adherentInfoRepository.save(adherentInfo);
    }
    public void deleteById(Integer id) {
        adherentInfoRepository.deleteById(id);
    }

    public AdherentInfo findByAdherentId(Integer id) {
        Optional<AdherentInfo> info = adherentInfoRepository.findByAdherentId(id);
        if (info.isPresent()) {
            return info.get();
        } else {
            return null; // or throw an exception if preferred
        }
    }
}
