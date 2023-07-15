package com.twinkle.JakSim.controller.timetable;

import com.twinkle.JakSim.model.dao.scheduleList.ScheduleListDao;
import com.twinkle.JakSim.model.dto.timetable.request.TimetableRequest;
import com.twinkle.JakSim.model.dto.timetable.response.TimetableResponse;
import com.twinkle.JakSim.model.service.scheduleList.ScheduleListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class TimetableRestController {

    private final ScheduleListService scheduleListService;

    @PostMapping("/details")
    public ResponseEntity<List<TimetableResponse>> myTrainerTimetable(@Valid @RequestBody TimetableRequest timetableRequest) {
        List<TimetableResponse> response = scheduleListService.findTrainerTimetable(timetableRequest.getTrainerId(),
                timetableRequest.getDt(), timetableRequest.getType());

        return ResponseEntity.ok().body(response);
    }
}
