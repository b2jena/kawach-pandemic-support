package com.stackroute.resource.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*This is a Storage Service class by which the pdf file is uploaded in the S3 bucket*/
@Service
@Slf4j
public class StorageService {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(StorageService.class.getName());

    @Value("${application.bucket.name}")
    private String bucketName;

    private AmazonS3 s3Client;

    /*Amazon S3 Client is injected in this Storage Service class by @Autowired annotation*/
    @Autowired
    public StorageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    /*This Method is responsible for uploading the Pdf file in the Amazon S3 Bucket */
    public String uploadFile(MultipartFile file) throws Exception{
        try {
            File fileObj = convertMultiPartFileToFile(file);
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            fileObj.delete();
            return "File uploaded : " + fileName;
        }catch (Exception e){
            logger.error(String.valueOf(e));
            throw  new Exception();
        }
    }


/*This is to convert the original file in to multipart file*/
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
