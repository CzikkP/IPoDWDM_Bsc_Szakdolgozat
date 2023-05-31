package hu.czikk.input.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import hu.czikk.IncorrectFileNameException;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class CSV_Reader<T> {
    private Class<T> type;
    private File file;

    public CSV_Reader(Class<T> type, File file) {
        this.type = type;
        this.file = file;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return this.type;
    }

    public List<T> inputRead() throws IOException, IncorrectFileNameException {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper
                    .typedSchemaFor(this.getType())
                    .withHeader()
                    .withColumnSeparator(',');
            MappingIterator<T> complexIter = mapper
                    .readerWithTypedSchemaFor(this.getType())
                    .with(schema)
                    .readValues(file);
            return complexIter.readAll();
        } catch (FileNotFoundException e) {
            throw new IncorrectFileNameException("INPUT_READ_EXCEPTION =>> Incorrect filename or path to file: " + file, e);
        }
    }

    public String testInputRead(List<T> list) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = objectMapper.writeValueAsString(Stream.of(list).collect(Collectors.toList()));
        System.out.println("Converting List of " + type.getName().toUpperCase() + " objects to JSON...");
        return jsonString;
    }
}
