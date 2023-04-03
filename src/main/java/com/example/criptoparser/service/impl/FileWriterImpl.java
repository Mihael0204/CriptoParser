package com.example.criptoparser.service.impl;

import com.example.criptoparser.service.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Component;

@Component
public class FileWriterImpl implements FileWriter {

    @Override
    public void writeDataToFile(String report, String path) {
        try {
            Files.write(Path.of(path), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + path,e);
        }
    }
}
