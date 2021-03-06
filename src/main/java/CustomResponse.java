import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CustomResponse {
    private String compliant;
    @JsonIgnore
    private String pdfaflavour;
    @JsonIgnore
    private ArrayList<String> ruleValidationExceptions;

    //added comment

    public CustomResponse() {
    }

    public CustomResponse(String compliant, String pdfaflavour, ArrayList<String> ruleValidationExceptions) {
        this.compliant = compliant;
        this.pdfaflavour = pdfaflavour;
        this.ruleValidationExceptions = ruleValidationExceptions;
    }

    public String getCompliant() {
        return compliant;
    }

    public void setCompliant(String compliant) {
        this.compliant = compliant;
    }

    public String getPdfaflavour() {
        return pdfaflavour;
    }

    public void setPdfaflavour(String pdfaflavour) {
        this.pdfaflavour = pdfaflavour;
    }


    public ArrayList<String> getRuleValidationExceptions() {
        return ruleValidationExceptions;
    }
}
