package com.ibm.ids.example;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowResult
 */
public class ShowResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WordCountFinder wordCountFinder;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowResult() {
        super();
        wordCountFinder = new WordCountFinder();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Parse input
		String content = request.getParameter("content");

		int wordCount = wordCountFinder.countWords(content);
		
		//Print response to the Web Page
		//response.setContentType("text/plain");
	    response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // get correct messages
        Locale locale = request.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("com.ibm.ids.example.Messages", locale);
        String wordcount = messages.getString("wordcount");
        String wordlist = messages.getString("wordlist");

        try{ 
            writeToVulnerableSink(getVulnerableSource(content));
        }catch (Exception e){
            String error_message = messages.getString("fileerror");            
            out.println(error_message + wordlist);
        }

        response.setContentType( "text/html" );
        out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>" );
        //out.println( "Hello, " + content );
        out.println(wordcount + wordCount);
        // echo input !! XSS security error here !!
        out.println( "</BODY></HTML>" );

	}
/*
    public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String name = request.getParameter( "name" );
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();
        out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>" );
        out.println( "Hello, " + name );
        out.println( "</BODY></HTML>" );
    }
*/

 
    private Pattern namePattern = Pattern.compile("^[a-zA-Z]{3,10}$");
    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
 
            String name = request.getParameter("name");
            Locale locale = request.getLocale();
            ResourceBundle messages = ResourceBundle.getBundle("com.ibm.ids.example.Messages", locale);
            String error = messages.getString("error");
            PrintWriter out = response.getWriter();

            if ( name == null ){
                String nobody = messages.getString("nobody");
                out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>"+nobody+"</BODY></HTML>");
            }else 
            if ( !namePattern.matcher( name ).matches() )
            {
                out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>" + error + " </BODY></HTML>");
            }else{ 
                response.setContentType( "text/html" );
                String hello = messages.getString("hello");
                String escapedName = name.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
                out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>"+hello+", " + escapedName + "</BODY></HTML>");
            }  
    }

    public static String getVulnerableSource(String file)
        throws java.io.IOException, java.io.FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[100];
        fis.read(buf);
        String ret = new String(buf);
        fis.close();
        return ret;
    }

    public static void writeToVulnerableSink(String str)
        throws java.io.FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(str);
        PrintWriter writer = new PrintWriter(fos);
        //writer.write(str); 
        writer.append(str);
    }
}

