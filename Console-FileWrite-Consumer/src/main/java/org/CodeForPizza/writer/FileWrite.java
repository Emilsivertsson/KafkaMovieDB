package org.CodeForPizza.writer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import java.io.FileWriter;

/**
 * FileWriter to write the Movie object to a logfile. The logfile is located in the same folder as the project.
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class FileWrite {

    private FileWriter file;

    public void writeToFile(MovieDTO movieInfo) {
        System.out.println("Writing movie information to file...");

        try {
            file = new FileWriter("Console-FileWrite-Consumer/src/main/java/org/CodeForPizza/log/TopicLog.txt", true);
            file.write(movieInfo.toString()+ ",\n");
            file.flush();
            file.close();
            System.out.println("Movie information written to file.");
        } catch (Exception e) {
            log.error("Error writing to file.");
            throw new RuntimeException(e.getMessage());
        }
    }
}
