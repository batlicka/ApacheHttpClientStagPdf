import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.File;
import java.io.IOException;


public class MainClass {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

            //přidej výjimku, co když odpověď nedojde ve fromátu JSON

            String filePath="D:\\dokumenty\\Vojta\\UTB\\diplom_prace\\profiles\\veraPDF-corpus\\PDF_A-1b\\6.1 File structure\\6.1.2 File header\\veraPDF test suite 6-1-2-t01-pass-a.pdf";
            String fileName="veraPDF test suite 6-1-2-t01-pass-a.pdf";

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://pdfa.k.utb.cz:8080/api/validate/auto");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(
                    "file", new File(filePath), ContentType.APPLICATION_OCTET_STREAM, fileName);//
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            CloseableHttpResponse response = client.execute(httpPost);

            /*HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println(responseString);*/



            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(response.getStatusLine().getProtocolVersion());
            System.out.println(response.getStatusLine().getReasonPhrase());

            String responseString = new BasicResponseHandler().handleResponse(response);
            System.out.println(responseString);

            // parse JSON
/*            ObjectMapper mapper = new ObjectMapper();
            Response res = mapper.readValue(responseString,Response.class);*/

            //podívat se do dokumentace Jackson, jaké může háze výjimky


            client.close();

        }
}
