package client;

import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.UUID;

public class Client {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("This is Client");
        System.out.println("Usage: scurl [options] url");
        System.out.println("scurl Command 1: $ scurl http://httpbin.org/get");
        System.out.println("scurl Command 2: $ scurl -X GET http://httpbin.org/get");
        System.out.println("scurl Command 3: $ scurl -v http://httpbin.org/get");
        System.out.println("scurl Command 4: $ scurl -v -H \"X-Custom-Header: NA\" http://httpbin.org/get");
        System.out.println("scurl Command 5: $ scurl -v -X POST -d \"{'hello':'world'}\" -H \"Content-Type: application/json\" http://httpbin.org/post");
        System.out.println("scurl Command 6: $ scurl -v -L http://httpbin.org/status/302");
        System.out.println("scurl Command 7: $ scurl -F \"upload=@/Users/bakgyeongseo/desktop/temp.txt\" http://httpbin.org/post");
        System.out.println("=========================================");

        Scanner scanner = new Scanner(System.in);

        System.out.print("$ ");
        String temp [] = null;
        try {
            temp = scanner.nextLine().split(" ");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command");
        }

        String host = "";
        URI uri = null;
        Socket socket;
        PrintWriter out;
        BufferedReader in;

        if(temp[0].equals("scurl")) {
            // Command 1
            if(temp.length == 2) {
                host = temp[temp.length-1];

                try {
                    uri = new URI(host);
                    socket = new Socket(uri.getHost(), 80);

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    out.println("GET /get HTTP/1.1");
                    out.println("Host: httpbin.org");
                    out.println("Accept: */*");
                    out.println("User-Agent: curl/7.79.1");
                    out.println();
                    out.flush();

                    String line = in.readLine();
                    boolean check = false;

                    while( line != null )
                    {
                        if (!check) {
                        } else {
                            System.out.println( line );
                        }
                        line = in.readLine();
                        if(line.equals(""))
                            check = true;

//                    if(check==true && line.equals(""))
//                        break;
                    }

                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("End");
                }
            }
            else if(temp[1].equals("-X") && temp[2].equals("GET")) { // Command 2
                host = temp[temp.length-1];

                try {
                    uri = new URI(host);
                    socket = new Socket(uri.getHost(), 80);

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    out.println("GET /get HTTP/1.1");
                    out.println("Host: httpbin.org");
                    out.println("Accept: */*");
                    out.println("User-Agent: curl/7.79.1");
                    out.println();
                    out.flush();

                    String line = in.readLine();
                    boolean check = false;

                    while( line != null )
                    {
                        if (!check) {
                        } else {
                            System.out.println( line );
                        }
                        line = in.readLine();
                        if(line.equals(""))
                            check = true;
                    }

                    out.close();
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("end");
                }
            }
            else if(temp.length == 3 && temp[1].equals("-v")) { // Command 3
                host = temp[temp.length-1];

                try {
                    uri = new URI(host);
                    socket = new Socket(uri.getHost(), 80);

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader( new InputStreamReader(socket.getInputStream()));

                    out.println("GET /get HTTP/1.1");
                    out.println("Host: httpbin.org");
                    out.println("Accept: */*");
                    out.println("User-Agent: curl/7.79.1");
                    out.println();
                    out.flush();

                    String line = in.readLine();
                    while( line != null )
                    {
                        System.out.println( line );
                        line = in.readLine();
                    }

                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("End");
                }
            }
            else if(temp[1].equals("-v") && temp[2].equals("-H")) { // Command 4
                host = temp[temp.length-1];
                String customHead = temp[3] + " " + temp[4];
                customHead = customHead.replace("\"", "");

                try {
                    uri = new URI(host);
                    socket = new Socket(uri.getHost(), 80);

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    out.println("GET /get HTTP/1.1");
                    out.println("Host: httpbin.org");
                    out.println("Accept: */*");
                    out.println("User-Agent: curl/7.79.1");
                    out.println(customHead);
                    out.println();
                    out.flush();

                    String line = in.readLine();
                    while( line != null )
                    {
                        System.out.println( line );
                        line = in.readLine();
                    }

                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("End");
                }
            }
            else if(temp[1].equals("-v") && temp[2].equals("-X") && temp[3].equals("POST") && temp[4].equals("-d")) { // Command 5
                host = temp[temp.length-1];
                String contentType = (temp[7] + " " + temp[8]).replace("\"", "");

                String data = temp[5].replace("\"", "");
                System.out.println(data);
                try{
                    uri = new URI(host);
                    socket = new Socket(uri.getHost(), 80);

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    out.println("POST /post HTTP/1.1");
                    out.println("Host: httpbin.org");
                    out.println("Accept: */*");
                    out.println("User-Agent: curl/7.79.1");
                    out.println(contentType);
                    out.println("Content-Length: " + data.length());      // Content-length
                    out.println();

                    out.println(data);
                    out.flush();

                    String line = in.readLine();
                    while( line != null )
                    {
                        System.out.println( line );
                        line = in.readLine();
                    }

                    out.close();
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("End");
                }
            }
            else if(temp[1].equals("-v") && temp[2].equals("-L")) {    // Command 6
                host = temp[temp.length-1];

                try {
                    uri = new URI(host);
                    socket = new Socket(uri.getHost(), 80);

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    out.println("GET /status/302 HTTP/1.1");
                    out.println("Host: httpbin.org");
                    out.println("Accept: */*");
                    out.println("User-Agent: curl/7.79.1");
                    out.println();
                    out.flush();

                    String line = in.readLine();
                    while( line != null )
                    {
                        System.out.println(line);
                        line = in.readLine();
                    }

                    out.close();
                    in.close();
                    socket.close();

                    redirect(new URI("http://httpbin.org/redirect/1"));
                    redirect(new URI("http://httpbin.org/get"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("End");
                }
            }
            else if(temp[1].equals("-F")) { // Command 7
                host = temp[temp.length-1];

                BufferedReader fileReader = null;
                try {
                    fileReader = new BufferedReader(new FileReader("/Users/bakgyeongseo/desktop/temp.txt"));
                } catch (FileNotFoundException e) {
                    System.out.println("No File");
                }

                try{
                    uri = new URI(host);
                    socket = new Socket(uri.getHost(), 80);

                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String border = UUID.randomUUID().toString();

                    String requestBody= "";

                    requestBody += "--" + border+"\r\n";
                    requestBody += "Content-Disposition: form-data; name=\"upload\"; filename=\"temp.txt\"\r\n";
                    requestBody += "Content-Type: text/plain\r\n";
                    requestBody += "\r\n";

                    String str = "";
                    while ((str = fileReader.readLine()) != null) {
                        requestBody += str +"\r\n";
                    }

                    requestBody += "--"+border+"--\r\n";

                    out.println("POST /post HTTP/1.1");
                    out.println("Host: httpbin.org");
                    out.println("Accept: */*");
                    out.println("User-Agent: curl/7.79.1");
                    out.println("Content-Type: multipart/form-data; boundary="+border);
                    out.println("Content-Length: "+ requestBody.length());
                    out.println();
                    out.println(requestBody);
                    out.flush();

                    String line = in.readLine();
                    while( line != null )
                    {
                        System.out.println( line );
                        line = in.readLine();
                    }

                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("End");
                }
            }
        }
    }

    public static void redirect(URI uri) {

        try {
            Socket socket = new Socket(uri.getHost(), 80);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET " + uri.toString().substring(18) + " HTTP/1.1");
            out.println("Host: httpbin.org");
            out.println("Accept: */*");
            out.println("User-Agent: curl/7.79.1");
            out.println();
            out.flush();

            String line = in.readLine();
            while( line != null )
            {
                System.out.println(line);
                line = in.readLine();
            }

            out.close();
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
