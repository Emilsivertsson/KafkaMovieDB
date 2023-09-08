package org.CodeForPizza.writer;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import java.io.FileWriter;

/**
 * This class uses FileWriter to write the Json object to a file.
 */
@Slf4j
public class FileWrite {
    public void writeToFile(JSONObject movieInfo) {
        System.out.println("Writing movie information to file...");

        try {
            FileWriter file = new FileWriter("Console-FileWrite-Consumer/src/main/java/org/CodeForPizza/log/TopicLog.txt", true);
            file.write(movieInfo.toJSONString()+ ",\n");
            file.flush();
            file.close();
            System.out.println("Movie information written to file.");
        } catch (Exception e) {
            log.error("Error writing to file.");
        }
    }
}