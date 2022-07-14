package api.reqres;

public class Payloads {
    public static String newUser(String name, String job){
        return "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}";
    }
}
