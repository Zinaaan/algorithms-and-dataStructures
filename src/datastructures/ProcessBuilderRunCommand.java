package datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * @author lzn
 * @date 2023/08/16 21:59
 * @description
 */
public class ProcessBuilderRunCommand {

    public static void main(String[] args) {
        try {
            String scriptPath;
            String osName = System.getProperty("os.name").toLowerCase();

            // Determine the operating system and set the script path accordingly
            if (osName.contains("win")) {
                scriptPath = Paths.get("src", "main", "datastructures", "run.bat").toString();
            } else {
                scriptPath = Paths.get("src", "main", "datastructures", "run.sh").toString();
            }

            // Execute the script using ProcessBuilderRunCommand
            ProcessBuilder pb;
            if (osName.contains("win")) {
                pb = new java.lang.ProcessBuilder("cmd.exe", "/c", scriptPath);
            } else {
                pb = new java.lang.ProcessBuilder("bash", "-c", scriptPath);
            }

            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Print output
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Get exit value
            int exitCode = process.waitFor();
            System.out.println("Exit Value: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
