
package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.enums.ExtensionRequestState;
import itu.spring.bibliotheque.models.ExtensionRequest;
import itu.spring.bibliotheque.repositories.ExtensionRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtensionRequestService {

    public List<ExtensionRequest> findByUserId(Integer userId) {
        return extensionRequestRepository.findByLoan_Adherent_Utilisateur_Id(userId);
    }
    @Autowired
    private ExtensionRequestRepository extensionRequestRepository;

    public List<ExtensionRequest> getAll() {
        return extensionRequestRepository.findAll();
    }


    public List<ExtensionRequest> getNonValidated() {
        List<ExtensionRequest> req = extensionRequestRepository.findAll();
        List<ExtensionRequest> result  = new ArrayList<>();
        for (ExtensionRequest extensionRequest : req) {
            if (!extensionRequest.getState().equals(ExtensionRequestState.Validated)  ) {
                result.add(extensionRequest);
            }
        }
        return result;
    }

    public List<ExtensionRequest> getByLoanId(Integer loanId) {
        return extensionRequestRepository.findByLoanId(loanId);
    }

    public List<ExtensionRequest> getByState(String state) {
        return extensionRequestRepository.findByState(state);
    }

    public Optional<ExtensionRequest> getById(Integer id) {
        return extensionRequestRepository.findById(id);
    }

    public ExtensionRequest save(ExtensionRequest extensionRequest) {
        return extensionRequestRepository.save(extensionRequest);
    }

    public void delete(Integer id) {
        extensionRequestRepository.deleteById(id);
    }
}
