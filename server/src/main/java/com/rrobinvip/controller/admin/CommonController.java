package com.rrobinvip.controller.admin;

import com.rrobinvip.result.Result;
import com.rrobinvip.utils.S3Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Common API
 */
@RestController
@RequestMapping("/admin/common")
@Api(value = "Common API")
@Slf4j
public class CommonController {

    @Autowired
    private S3Util s3Util;

    @PostMapping("/upload")
    @ApiOperation(value = "upload file")
    public Result<String> fileUpload(MultipartFile file) {
        log.info("Upload file: {}, size: {}", file.getOriginalFilename(), file.getSize());
        try {
            String originalFileName = file.getOriginalFilename();
            String fileFormat;
            if (originalFileName == null) {
                return Result.error("Filename invalid.");
            } else {
                fileFormat = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            String objName = UUID.randomUUID().toString() + fileFormat;

            String accessUrl = s3Util.upload(file.getBytes(), objName);
            return Result.success(accessUrl);
        } catch (Exception e) {
            log.error("File upload filed. Error message: {}", e.getMessage());

            return Result.error(e.getMessage());
        }
    }
}
