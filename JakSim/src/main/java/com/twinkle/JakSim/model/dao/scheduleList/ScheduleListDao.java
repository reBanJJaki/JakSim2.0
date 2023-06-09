package com.twinkle.JakSim.model.dao.scheduleList;


import com.twinkle.JakSim.model.dao.timetable.TimetableRowMapper;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleListDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    public List<TimetableDto> findSchedule(String userId, LocalDate firstDate, LocalDate lastDate, String trainerId) {
        List<TimetableDto> timetableList = new ArrayList<>();

        this.sql = "select * from timetable as tt inner join reservation res on tt.t_idx = res.t_idx " +
                "where res.user_id = ? and tt.user_id = ? and t_date between ? and ?";

        try {
            timetableList = jdbcTemplate.query(this.sql, new TimetableRowMapper(),
                                                                            userId, trainerId, firstDate, lastDate);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There's no any schedule");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return timetableList;
    }

    public List<TimetableDto> findAllSchedule(String trainerId, LocalDate firstDate, LocalDate lastDate, int tType) {
        List<TimetableDto> timetableList = new ArrayList<>();
        Object[] params;

        this.sql = "select * from timetable where user_id = ? and t_date between ? and ?";

        if(tType == 3) {
            params = new Object[]{trainerId, firstDate, lastDate};
        } else {
            sql += " and t_type = ?";
            params = new Object[]{trainerId, firstDate, lastDate, tType};
        }

        try {
            timetableList = jdbcTemplate.query(this.sql, new TimetableRowMapper(), params);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There's no any schedule");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return timetableList;
    }
}
