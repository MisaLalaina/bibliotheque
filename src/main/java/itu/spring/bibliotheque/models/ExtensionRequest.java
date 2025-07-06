package itu.spring.bibliotheque.models;

import itu.spring.bibliotheque.enums.ExtensionRequestState;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "extension_request")
public class ExtensionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(name = "request_date")
    private Date requestDate;

    @Enumerated(EnumType.STRING)
    private ExtensionRequestState state;

    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validated_by")
    private Utilisateur validatedBy;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }
    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    public ExtensionRequestState getState() { return state; }
    public void setState(ExtensionRequestState state) { this.state = state; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public Utilisateur getValidatedBy() { return validatedBy; }
    public void setValidatedBy(Utilisateur validatedBy) { this.validatedBy = validatedBy; }
}
