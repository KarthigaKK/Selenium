package KarthigaAcademy.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//Read Json to String
	String jsonToStringcontent=  FileUtils.readFileToString
			(new File(System.getProperty("user.dir")+"//src//test//java//KarthigaAcademy//Data//PurchaseOrder.json"),StandardCharsets.UTF_8);
	
	//String to hashmap--jackson databind
	ObjectMapper objmap=new ObjectMapper();
	List<HashMap<String,String>> data=objmap.readValue(jsonToStringcontent, new TypeReference<List<HashMap<String,String>>>(){
	});
	
	return data;
	}

}
