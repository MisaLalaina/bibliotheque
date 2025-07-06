package itu.spring.bibliotheque.utils;

import java.sql.Date;
import java.util.List;

import itu.spring.bibliotheque.enums.HolidayDirection;
import itu.spring.bibliotheque.models.HolidayList;
import itu.spring.bibliotheque.services.HolidayListService;

public class DateUtils {
    public static Date getLoanEndDate(Date startDate, int durationDays, List<HolidayList> holidays, HolidayDirection direction, HolidayListService holidayService) {
        if (startDate == null || durationDays < 0) {
            throw new IllegalArgumentException("Invalid start date or duration");
        }
        Date endDate = Date.valueOf(startDate.toLocalDate().plusDays(durationDays));
        boolean isHoliday = holidayService.isHoliday(endDate);
        while (isHoliday) {
            if (direction == HolidayDirection.After) {
                endDate = Date.valueOf(endDate.toLocalDate().plusDays(1));
            }
            else if (direction == HolidayDirection.Before) {
                endDate = Date.valueOf(endDate.toLocalDate().minusDays(1));
            } else {
                throw new IllegalArgumentException("Invalid holiday direction");
            }
            isHoliday = holidayService.isHoliday(endDate);
        }
        return endDate;
    }
}
