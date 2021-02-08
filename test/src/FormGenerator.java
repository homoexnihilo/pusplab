
/*
 * This class provides forms to be displayed to the user
 */
public class FormGenerator {
	

    private String formElement(String par) {
    	return '"' + par + '"';
    }
    
    
    /*
     * Form for requesting user name
     */
    public String nameRequestForm() {
    	String html = "Please enter your name";
    	html += "<p> <form name=" + formElement("input");
    	//html += " action=" + formElement(myURL); 
    	html += " method=" + formElement("get");
    	html += "<p> Name: <input type=" + formElement("text") + " name=" + formElement("user") + '>';
    	html += "<p> <input type=" + formElement("submit") + "value=" + formElement("Submit") + '>';
    	return html;
    }
    
    /*
     * Form for requesting project type
     */
    public String projectTypeRequestForm() {
    	String html = "<p>Please choose project type by entering 1 or 2:<br><br>"
    			+ "1: Type A <br>"
    			+ "2: Type B</P>";
    	html += "<p> <form name=" + formElement("input");
    	//html += " action=" + formElement(myURL); 
    	html += " method=" + formElement("get");
    	html += "<p> Type: <input type=" + formElement("text") + " name=" + formElement("type") + '>';
    	html += "<p> <input type=" + formElement("submit") + "value=" + formElement("Submit") + '>';
    	return html;
    }

    /*
     * Form for requesting success with respect to four factors
     */
    public String projectDataRequestForm() {
    	String[][] variables = {
    			{"Met operational performance", "s11"},
                {"Met technical performance", "s12"},
                {"Met project schedule", "s13"},
                {"Stayed on budget", "s14"}};
    	String html = "<p>Please assess the importance of the following factors (1-10, where 1 is least important)";    	
    	html += "<p> <form name=" + formElement("input2");
    	html += " method=" + formElement("get");
    	//html += " action=" + formElement(myURL);
    	for (int i=0; i<4; i++) {
    		html += "<p> " + variables[i][0];
    		html += ": <input type="+ formElement("text") + "name =" + variables[i][1]  + '>';
    	}
    	html += "<p> <input type=" + formElement("submit") + "value=" + formElement("Submit") + '>';
    	return html;
    }
	
}