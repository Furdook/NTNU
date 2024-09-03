package project.modules;

import java.io.IOException;

public interface IIO { // Interface Input/Output
    public void readFile(String type, String filePath);
    public void createObject(String[] ref) throws IOException;
    public void writeToFile(String input) throws IOException;
}
