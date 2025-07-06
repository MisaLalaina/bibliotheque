package itu.spring.bibliotheque.services;

import itu.spring.bibliotheque.models.HolidayList;
import itu.spring.bibliotheque.repositories.HolidayListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;

@Service
public class HolidayListService {
    @Autowired
    private HolidayListRepository holidayListRepository;

    public List<HolidayList> getAll() {
        return holidayListRepository.findAll();
    }

    public List<HolidayList> getByDate(Date date) {
        return holidayListRepository.findByHolidayDate(date);
    }

    public HolidayList save(HolidayList holiday) {
        return holidayListRepository.save(holiday);
    }

    public void delete(Integer id) {
        holidayListRepository.deleteById(id);
    }

    public boolean isHoliday(Date date) {
        List<HolidayList> holidays = holidayListRepository.findByHolidayDate(date);
        return !holidays.isEmpty();
    }
}
