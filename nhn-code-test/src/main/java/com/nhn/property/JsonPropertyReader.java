package com.nhn.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * config.json 파일을 읽어오는 클래스
 * @author 김성철
 */
public class JsonPropertyReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPropertyReader.class);

    /**
     * config.json 파일을 읽어오는 메소드
     * @return ConfigVO
     */
    public ConfigVO readProperty(){
        ConfigVO config = new ConfigVO();
        try {

            //config 포함하여 빌드하여 읽을때
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = JsonPropertyReader.class.getClassLoader().getResourceAsStream("config.json");
            config = objectMapper.readValue(inputStream, ConfigVO.class);
            LOGGER.info("readProperty Success  config : {}" ,config.toString());

        } catch (Exception e) {
            LOGGER.error("readProperty Fail , " , e);
            return null;
        }
        return config;
    }
}
