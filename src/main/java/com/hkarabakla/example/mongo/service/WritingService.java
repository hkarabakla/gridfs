package com.hkarabakla.example.mongo.service;

import com.hkarabakla.example.mongo.model.FileMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class WritingService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public void writeToGridfs(File file, String fileName, FileMetaData fileMetaData) {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            gridFsTemplate.store(inputStream, fileName, fileMetaData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
