package modelo;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LeerJson {

	JSONParser parser = new JSONParser();
	Espia espia;
	Agencia agencia = new Agencia();

	public void leer() {
		 JSONParser parser = new JSONParser();
    
    try {
        
        Object obj = parser.parse(new FileReader("src/InformeDeEspias.json"));
        JSONObject jsonObject = (JSONObject) obj;

        JSONArray array = (JSONArray) jsonObject.get("Espias");

        
        for(int i = 0 ; i < array.size() ; i++) {
            JSONObject jsonObject1 = (JSONObject) array.get(i);
            
            Espia e = new Espia(jsonObject1.get("alias").toString(),jsonObject1.get("codigo").toString());
            agencia.reclutarEspia(e);
        }
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
}