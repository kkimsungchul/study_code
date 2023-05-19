package com.nhn.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonPropertyReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPropertyReader.class);

    public ConfigVO readProperty(){
        ConfigVO config = new ConfigVO();
        try {
            //프로젝트 최상의 디렉토리의 config 파일을 읽을때 사용하며, config.json 파일을 프로젝트 폴더의 root디렉토리 밑에 위치시키면 됨
//            ObjectMapper objectMapper = new ObjectMapper();
//            config = objectMapper.readValue(new File(System.getProperty("user.dir")+ "/config.json"), ConfigVO.class);

            //config 포함하여 빌드하여 읽을때
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = JsonPropertyReader.class.getClassLoader().getResourceAsStream("config.json");
            config = objectMapper.readValue(inputStream, ConfigVO.class);


            LOGGER.info(config.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }
}
