package org.CodeForPizza.writer;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import java.io.FileWriter;
@Slf4j
public class FileWrite {
    public void writeToFile(JSONObject movieInfo) {
        System.out.println("Writing movie information to file...");

        try {
            FileWriter file = new FileWriter("ConsoleConsumer/src/main/java/org/CodeForPizza/log/TopicLog.txt", true);
            file.write(movieInfo.toJSONString()+ ",\n");
            file.flush();
            file.close();
        } catch (Exception e) {
            log.error("Error writing to file.");
        }
        System.out.println("Movie information written to file.");
    }
}
