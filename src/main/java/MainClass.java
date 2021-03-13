import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;

import org.apache.http.client.methods.*;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MainClass {

    private static final ObjectMapper mapper = new ObjectMapper();

    /*public static void fcn() throws ArrayIndexOutOfBoundsException{
        int num[] = {1, 2, 3, 4};
        System.out.println(num[5]);
    }*/

    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        StopWatch stopwatchLang3 = new StopWatch();
        stopwatchLang3.start();;
                SQLite var=new SQLite();
                var.insertStagpdfaLogs("ssdffsfsf","compliant:true",6,8);
                var.insertStagpdfaLogs("aaasfsffsfsf","compliant:false",11,4);
                var.printSQLContentOnConsole();
        stopwatchLang3.stop();
        stopwatch.stop();
        long millis = stopwatch.elapsed(TimeUnit.SECONDS);
        System.out.println("that took: " + stopwatch);
        System.out.println("time from stopwatchLang3: " + stopwatchLang3.getTime(TimeUnit.MILLISECONDS)/1000.0);

    }
}
