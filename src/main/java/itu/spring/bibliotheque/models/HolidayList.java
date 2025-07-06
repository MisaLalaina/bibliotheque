package itu.spring.bibliotheque.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "holiday_list")
public class HolidayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "holiday_date")
    private Date holidayDate;

    private String description;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getHolidayDate() { return holidayDate; }
    public void setHolidayDate(Date holidayDate) { this.holidayDate = holidayDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
