package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Storage manager for loading and saving to disk
 */
public class Storage {
    private final Path path;
    private final TaskList tasks;

    /**
     * Create a Storage object to manage loading and saving of data to disk
     *
     * @param filename Name of data file
     * @param tasks    Object to be used for processing
     * @throws IOException If cannot create the parent directory of the data file
     */
    public Storage(String filename, TaskList tasks) throws IOException {
        this.path = Paths.get(filename);
        this.tasks = tasks;
        checkParentDir();
    }

    /**
     * Saves the current data in "tasks" to disk
     */
    public void save() {
        try {
            checkParentDir();
            FileWriter fw = new FileWriter(path.toFile());
            for (Task task : tasks) {
                String rawData = task.getRawData() + System.lineSeparator();
                fw.append(rawData);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("[Error] Fail to save data to disk");
            e.printStackTrace();
        }

    }

    /**
     * Load the data from the data file to "tasks"
     */
    public void load() {
        try {
            File f = path.toFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = Task.decodeTask(line);
                if (task != null) { //If this task is not invalid
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("[Warning] Data file not found");
            fnfe.printStackTrace();
        }
    }

    /**
     * Check if parent directory exists
     *
     * @throws IOException if cannot create directory
     */
    private void checkParentDir() throws IOException {
        Path parentPath = path.getParent();
        try {
            if (!Files.exists(parentPath)) {
                Files.createDirectory(parentPath);
            }
        } catch (IOException e) {
            System.out.printf("[Error] Cannot create %s directory", parentPath);
            e.printStackTrace();
            throw e;
        }
    }
}
