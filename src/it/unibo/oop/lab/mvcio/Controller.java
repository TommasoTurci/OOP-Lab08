package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    private String path = System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt";
    private File curfile = new File(path);
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    /**
     * Sets file to specified directory.
     * @param newFile
     */
    public void setFile(final File newFile) {
        curfile = newFile;
    }
    /**
     * Gets current file.
     * @return file
     */
    public File getFile() {
        return curfile;
    }
    /**
     * Gets current file path as a string.
     * @return file path as string
     */
    public String getPath() {
        return curfile.getAbsolutePath();
    }
    /**
     * Saves string into current file.
     * @param input
     */
    public void editFile(final String input) {
        try (PrintStream editable = new PrintStream(curfile)){
            editable.print(input);
        } catch (FileNotFoundException e) {
            System.err.println("File not found at current directory.");
            e.printStackTrace();
        }
    }



}
