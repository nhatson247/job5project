package com.fpt.job5project.service;


import com.fpt.job5project.dto.CVDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICVService {
    public List<CVDTO> listOfCv(Long id);

    public CVDTO getCv(long id);

    public CVDTO addCv(CVDTO cvdto, MultipartFile file);

    public CVDTO updateCv(long id, CVDTO cvdto, MultipartFile file);

    public void deleteCv(long id);
}
