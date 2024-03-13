package com.rrobinvip.controller.admin;

import com.rrobinvip.constant.MessageConstant;
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

    /**
     * Uploads a file to an S3 bucket and returns the file's access URL.
     * <p>
     * This method logs the file's original name and size, then attempts to upload it to an S3 bucket.
     * If the file's original name is null, it returns an error. Otherwise, it extracts the file's format,
     * generates a unique object name by appending a UUID to the file format, and uploads the file to S3.
     * Upon successful upload, it returns the access URL of the uploaded file. If the upload fails,
     * it logs the error and returns an error message.
     *
     * @param file The file to be uploaded.
     * @return Result<String> A result object containing the file's access URL on success or an error message on failure.
     */
    @PostMapping("/upload")
    @ApiOperation(value = "upload file")
    public Result<String> fileUpload(MultipartFile file) {
        log.info("Upload file: {}, size: {}", file.getOriginalFilename(), file.getSize());
        try {
            String originalFileName = file.getOriginalFilename();
            String fileFormat;
            if (originalFileName == null) {
                return Result.error(MessageConstant.UPLOAD_FAILED);
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
