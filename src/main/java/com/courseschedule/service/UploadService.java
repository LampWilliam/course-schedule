package com.courseschedule.service;

import com.courseschedule.common.lang.Result;
import com.courseschedule.common.lang.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Result uploadTask(MultipartFile file);
    Result uploadClass(MultipartFile file);
    Result uploadRoom(MultipartFile file);
}
