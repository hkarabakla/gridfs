package com.hkarabakla.example.mongo.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ReadingService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public InputStream getSingleFileFromGridfs(String fileName) {

        GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(fileName)));

        return gridFSDBFile.getInputStream();
    }

}
