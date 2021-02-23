import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public class CustomJsonDeserializer {


    private JsonNode rootNode;

    public CustomJsonDeserializer(JsonNode rootNode) {
        this.rootNode=rootNode;
    }

    //for viewing the structure of JSON is usefull this online tool: http://jsonviewer.stack.hu/
    public List<String> getClauseArray(){
        List<String> clause = new ArrayList<String>();
        ArrayNode arrayNode=(ArrayNode) rootNode.at("/validationProfile/rules");
        JsonNode arrayElement;
        for(int i=0; i<arrayNode.size();i++){
            arrayElement = arrayNode.get(0).at("/ruleId");
            clause.add(arrayElement.get("clause").asText());
        }
        return clause;
    }

    public String getAttributeValueFromRoot(String attribute){
        return rootNode.get(attribute).asText();
    }
}
