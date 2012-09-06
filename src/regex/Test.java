package regex;

public class Test {
	
    public static void main(String  args[]) {
    	String str="\\uaddd";
    	System.out.println(str.replaceAll("\\\\","\\"));
    	
    	String str1="<p>ahshhf</p>";
    	System.out.println(str1.replaceAll("(?is)(</p>|<p>)", ""));
    }
}
