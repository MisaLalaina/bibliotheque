package itu.spring.bibliotheque.repositories;

import itu.spring.bibliotheque.models.HolidayList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.util.List;

public interface HolidayListRepository extends JpaRepository<HolidayList, Integer> {
    List<HolidayList> findByHolidayDate(Date holidayDate);
}
