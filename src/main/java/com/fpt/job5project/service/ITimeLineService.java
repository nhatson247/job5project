package com.fpt.job5project.service;



import com.fpt.job5project.dto.TimeLineDTO;

import java.util.List;

public interface ITimeLineService {
    public List<TimeLineDTO> listOfTimeLine(Long id);


    public TimeLineDTO addTimeLine(TimeLineDTO timeLineDTO);

    public TimeLineDTO updateTimeLine(long id, TimeLineDTO timeLineDTO);

    public void deleteTimeLine(long id);
}
