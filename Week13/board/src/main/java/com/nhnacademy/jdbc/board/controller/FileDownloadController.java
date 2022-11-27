package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.dto.FileNameAndUuid;
import com.nhnacademy.jdbc.board.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class FileDownloadController {

    private static final String UPLOAD_DIR = "/Users/gyeongseo/temp/";
    private final FileService fileService;

    public FileDownloadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/post/download/file/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws MalformedURLException {
        FileNameAndUuid fileNameAndUuid = fileService.getFile(fileId);

        String uploadFileName = fileNameAndUuid.getFileName();
        String storeFileName = fileNameAndUuid.getUuidName();

        UrlResource resource = new UrlResource("file:" + UPLOAD_DIR + storeFileName);

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(resource);
    }
}
