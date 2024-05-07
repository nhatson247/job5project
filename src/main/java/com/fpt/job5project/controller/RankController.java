package com.fpt.job5project.controller;

import com.fpt.job5project.dto.RankDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IRankService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/rank")
public class RankController {
    IRankService iRankService;
    @GetMapping()
    public ResponseObject<List<RankDTO>> listRanks() {
        ResponseObject<List<RankDTO>> responseObject = new ResponseObject<>();
        List<RankDTO> listDTOs = iRankService.getAllRank();
        responseObject.setData(listDTOs);
        return responseObject;
    }

    @GetMapping("/{id}")
    public ResponseObject<RankDTO> getOneRank(@PathVariable("id") long id) {
        ResponseObject<RankDTO> responseObject = new ResponseObject<>();
        responseObject.setData(iRankService.getRankById(id));
        return responseObject;
    }
}
