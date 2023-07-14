package main.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private final String loggerName;
    private final String filePath;

    public Logger(String loggerName, String filePath) {
        this.loggerName = loggerName;
        this.filePath = filePath;
    }

    /**
     * Write log file with unified style on hard disk.
     * @param message defines error message.
     * @param logType defines level of the log.
     */
    public void log(String message, String logType) {
        String line = formatMessage(message, logType);
        try {
            outputToLog(filePath, line);
        } catch (Exception ignored) {
        }
    }

    /**
     * A helper to unify Log messages
     * @param message
     * @param level
     * @return
     */
    public String formatMessage(String message, String level) {
        String datePart = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        datePart = formatter.format(new Date());
        return datePart + ":" + loggerName + ": [" + level + "] " + message;
    }

    /**
     * A helper to write the logs on the output file
     * @param pathname
     * @param logLine
     * @throws IOException
     */
    private static synchronized void outputToLog(String pathname, String logLine) throws IOException {
        File logFile = new File(pathname);
        Files.writeString(logFile.toPath(), logLine + "\n",
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

}
