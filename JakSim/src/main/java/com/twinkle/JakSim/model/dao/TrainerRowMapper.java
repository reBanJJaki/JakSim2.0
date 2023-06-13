package com.twinkle.JakSim.model.dao;

import com.twinkle.JakSim.model.dto.TrainerDo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerRowMapper implements RowMapper<TrainerDo>{

    @Override
    public TrainerDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrainerDo trainerDo = new TrainerDo();

        trainerDo.setId(rs.getInt("UT_IDX"));
        trainerDo.setIntroduce(rs.getString("UT_INTRO"));
        trainerDo.setInsta(rs.getString("UT_INSTA"));
        trainerDo.setGym(rs.getString("UT_GYM"));
        trainerDo.setUserId(rs.getString("USER_ID"));
        trainerDo.setExpert1(rs.getInt("UT_EXPERT_1"));
        trainerDo.setExpert2(rs.getInt("UT_EXPERT_2"));

        return trainerDo;
    }
}
