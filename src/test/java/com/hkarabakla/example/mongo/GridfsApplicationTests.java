package com.hkarabakla.example.mongo;

import com.hkarabakla.example.mongo.model.FileMetaData;
import com.hkarabakla.example.mongo.service.ReadingService;
import com.hkarabakla.example.mongo.service.WritingService;
import com.hkarabakla.example.mongo.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GridfsApplicationTests {

	@Autowired
	private WritingService writingService;

	@Autowired
	private ReadingService readingService;

	private String fileName = "testfile.txt";

	@Test
	public void writeToMongoGridFs() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		FileMetaData fileMetaData = new FileMetaData();
		fileMetaData.setOwner("King of the files");
		fileMetaData.setTarget("Shire");
		writingService.writeToGridfs(file, fileName, fileMetaData);
	}

	@Test
	public void readFromMongoGridFs() {

		InputStream singleFileFromGridfs = readingService.getSingleFileFromGridfs(fileName);
		System.out.println("Content: " + StringUtil.getStringFromInputStream(singleFileFromGridfs));
	}
}
