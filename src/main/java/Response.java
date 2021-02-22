import java.util.ArrayList;
import java.util.List;

public class Response {
    private String compliant;
    private String pdfaflavour;
    private List<String> ruleViolationClause = new ArrayList<String>();

    public Response() {
    }

    public Response(String compliant, String pdfaflavour) {
        this.compliant = compliant;
        this.pdfaflavour = pdfaflavour;
    }

    public void addRuleViolationClause(String Clause){
    this.ruleViolationClause.add(Clause);
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


}
