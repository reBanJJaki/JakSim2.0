package com.twinkle.JakSim.model.service.account;

import com.twinkle.JakSim.model.dao.account.LoginLogDao;
import com.twinkle.JakSim.model.dto.account.LoginLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginLogService {
    private final LoginLogDao loginLogDao;
    public int create(String userId, String ip) {
        LoginLogDto logDto = new LoginLogDto();

        logDto.setUser_id(userId);
        logDto.setIp(ip);

        return loginLogDao.create(logDto);
    }

    public LoginLogDto findByUsernameRecent(String username) {
        return loginLogDao.findByUsernameRecent(username);
    }

    public List<LoginLogDto> findByUsername(String username, int pageNum) {
        int pageSize = 20;
        return loginLogDao.findByUsername(username, pageNum, pageSize);
    }
}
