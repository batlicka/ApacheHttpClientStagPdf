import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.HttpEntity;

import org.apache.http.client.methods.*;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainClass {

    private static final ObjectMapper mapper = new ObjectMapper();

    /*public static void fcn() throws ArrayIndexOutOfBoundsException{
        int num[] = {1, 2, 3, 4};
        System.out.println(num[5]);
    }*/

    public static void main(String[] args) throws IOException {
            //přidej výjimku, co když odpověď nedojde ve fromátu JSON
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            ObjectMapper objectMapper = new ObjectMapper();

            File file = new File("RuleViolationException.json");
            JsonNode rootNode2 = objectMapper.readTree(file);

            ArrayList<String> arr= new ArrayList<String>();

            ArrayNode arrayNode = (ArrayNode) rootNode2.at("/RuleViolationExceptions");

            for (int i = 0; i < arrayNode.size(); i++) {
                arr.add(arrayNode.get(i).asText());
            }
            ///////////////////////////
            String filePath="D:\\dokumenty\\Vojta\\UTB\\diplom_prace\\profiles\\veraPDF-corpus\\PDF_A-1b\\6.1 File structure\\6.1.2 File header\\veraPDF test suite 6-1-2-t01-pass-a.pdf";
            String fileName="veraPDF test suite 6-1-2-t01-pass-a.pdf";

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://pdfa.k.utb.cz:8080/api/validate/auto");//http://pdfa.k.utb.cz:8080/api/validate/auto
            //http://localhost:8080/api/validate/auto
            //http://pdfa.k.utb.cz:8080/api/validate/auto

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //builder.addBinaryBody("file", new File(filePath), ContentType.APPLICATION_OCTET_STREAM, fileName);
            builder.addBinaryBody("file",  new File(filePath));
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            CloseableHttpResponse response = client.execute(httpPost);

            /*System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getProtocolVersion());
            System.out.println(response.getStatusLine().getReasonPhrase());*/

            String responseString = new BasicResponseHandler().handleResponse(response);
            System.out.println(responseString);

            // parse JSON
            ObjectMapper mapper = new ObjectMapper();
            try{
                JsonNode rootNode = mapper.readTree(responseString);
                CustomJsonDeserializer des = new CustomJsonDeserializer(rootNode);

                Response responseCurrent=new Response(
                        des.getAttributeValueFromRoot("compliant"),
                        des.getAttributeValueFromRoot("pdfaflavour"),
                        des.getClauseArray()
                );
                //rest api rozhodne, jak se výjimka ošetří
                //na zobrazování chyb použít běžné http kody a chybu specifikovat v jeho správě

                System.out.println("|Compliant: " + responseCurrent.getCompliant() +"|pdfaflavour: "+responseCurrent.getPdfaflavour());
                System.out.println("List of Clauses: " + responseCurrent.getListRuleViolationClause());
            }catch(UnrecognizedPropertyException e1){
                System.out.println(e1.getMessage());
            }catch(JsonMappingException e2){
                System.out.println(e2.getMessage());
            }catch (JsonParseException e3){
                System.out.println("response can't be processed, because response is not in JSON format");
            }




            System.out.println("end");

            //podívat se do dokumentace Jackson, jaké může háze výjimky


            client.close();


        }
}
