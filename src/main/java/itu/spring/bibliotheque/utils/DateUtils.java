package itu.spring.bibliotheque.utils;

import java.sql.Date;
import itu.spring.bibliotheque.enums.HolidayDirection;
import itu.spring.bibliotheque.services.HolidayListService;

public class DateUtils {
    public static Date getLoanEndDate(Date startDate, int durationDays, HolidayDirection direction, HolidayListService holidayService) {
        if (startDate == null || durationDays < 0) {
            throw new IllegalArgumentException("Invalid start date or duration");
        }
        durationDays -= 1;
        if (durationDays <= 0) {
            return startDate;
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
