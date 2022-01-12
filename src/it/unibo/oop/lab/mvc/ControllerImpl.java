package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a controller interface that prints strings and has memory of the strings it printed.
 */
public class ControllerImpl implements Controller {

    private final List<String> stringList = new LinkedList<>();
    private String curString;

    /**
     * @param toSet
     */
    @Override
    public void setNextString(final String toSet) {
         if (toSet != null) {
            stringList.add(toSet);
            curString = toSet;
         } else {
            throw new IllegalStateException();
         }
    }
    /**
     * 
     */
    @Override
    public String getNextString() {
        String tmp = "";
        for (final String cnt : getStringHistory()) {
            tmp = tmp.concat(cnt + "\n");
        }
        return tmp;
    }
    /**
     * 
     */
    @Override
    public List<String> getStringHistory() {
        return stringList;
    }
    /**
     * 
     */
    @Override
    public void printCurrentString() {
        if (curString != null) {
            System.out.println(curString);
        } else { 
            throw new IllegalStateException(); 
        }

    }

}
