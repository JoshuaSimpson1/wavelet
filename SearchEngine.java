import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler{
    ArrayList<String> apps = new ArrayList<String>();

    public String handleRequest(URI url){
        if(url.getPath().equals("/")){
            return "Home Page";
        } 
        else if(url.getPath().equals("/add")){
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                apps.add(parameters[1]);
                return "App Added!";
            }
        }
        else if(url.getPath().contains("/search")){
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                if(parameters[1].equals("app")){
                    String appList = String.join(", ", apps);
                    return appList;
                }
            }
        }
        return "404, Not Found";
    }
}

class SearchEngine{
    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}