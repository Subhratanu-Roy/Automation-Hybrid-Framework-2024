package SeleniumPractice.SeleniumHybridFrameworkNew.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//adding jackson-databind and apache-commons-io dependencies
public class JsonReader {

	public List<HashMap<String, String>> jsonDataToMap(String filepath) throws IOException {

	
		String data = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8); //encoding format to write as string
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> finalData = mapper.readValue(data,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return finalData;

	}

}
