package jettyexample;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class HelloWorld extends AbstractHandler
{
	
	public static String baseURL = "http://wallpaperswide.com/download";
	
	
    @Override
    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
                                                      ServletException
    {
    	
    	System.out.println("main " + target);
       
		// bulamadığımızda yapcaz.
		String resolution = "-1920x1080";
		resolution = target.substring(0, target.indexOf(".jpg"));
		baseURL = baseURL + (resolution + "-1920x1080.jpg");
		System.out.println(baseURL);
		System.out.println("<img src=\"" + baseURL +
		 "\"></img>");
		// response.getWriter().write("<a href=\"" + baseURL + "\"></a>");
		/*
		 * URL url = new URL(baseURL); HttpURLConnection connection =
		 * (HttpURLConnection) url.openConnection();
		 * connection.setRequestMethod("GET");
		 * 
		 * BufferedImage originalImage
		 * =ImageIO.read(connection.getInputStream());
		 * ImageIO.write(originalImage, "jpg", response.getOutputStream());
		 */

		// ImageIO.read(new URL)(baseURL);
		System.out.println(request.getRequestURI());
		String localPath = request.getRequestURI();
		String path = localPath;
		// sendImage("." + localPath, response);
		// Enumeration<String> parametersNames = request.getParameterNames();
		Map<String,String[]> map =request.getParameterMap();
		System.out.println(map.size());
		
		String newName;
		try {
			if (map.containsKey("color")&&map.get("color")[0].equals("gray"))//map.size() == 1
			 {
				if (map.size()==3&&map.containsKey("width")&&map.containsKey("height"))
				{
					newName = "gray" + (map.get("width"))[0] + "x" + (map.get("height"))[0] + target.substring(1);
					path = new Scalar().toGray(new Scalar().scale("." + localPath, Integer.parseInt(map.get("width")[0]),
									Integer.parseInt(map.get("height")[0]), target.substring(1)), newName);
				}
				else if (map.size()==1)
				{
					newName = "gray" + target.substring(1);
					path = new Scalar().toGray("." + localPath, newName);
				}
				
				//System.getProperty("user.dir");
				//System.out.println("1 "+ path);
			 }
			 else if (map.size()==2&&map.containsKey("width")&&map.containsKey("height"))
			 {
				 newName = (map.get("width"))[0] + "x" + (map.get("height"))[0] + target.substring(1) ;
				path = new Scalar().scale("." + localPath, Integer.parseInt((map.get("width"))[0]),
						Integer.parseInt((map.get("height"))[0]), newName  );
				//System.out.println("2 "+ path);
			 }
			System.out.println(path);
			sendImage(path, response);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
      //  response.getWriter().write("<h1>İkinci resim</h1>");
      //  response.getWriter().write("<img src=\"http://kpssdelisi.com/doga-resimleri1/9.jpg\"></img>"); */
        
/*        File file = new File("/home/basak/Desktop/Merhaba");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String satir = reader.readLine();
 
            while (satir!=null) {
                response.getWriter().write(satir);
                satir = reader.readLine();
            }
            reader.close(); */
            
    	
    	
		// resim dosyası gonderme
    	
            		
           
        // Inform jetty that this request has now been handled
 //   	response.sendRedirect("http://www.google.com");
    	
 //   	response.setStatus(HttpStatus.FORBIDDEN_403); // Buradaki hata kodları son kullanıcı için değildir browser içindir. 
    	
     //   baseRequest.setHandled(true);
		
		
    }

    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8088);
        ResourceHandler fileResourceHandler = new ResourceHandler();
       
        
        ContextHandler context = new ContextHandler();
	    context.setContextPath("/img");
        
        context.setHandler(new HelloWorld());
        
        server.setHandler(context);
        server.start();
        server.join();
    }

	void sendImage(String path, HttpServletResponse response) throws IOException 
	{
		BufferedImage bufferedImage = ImageIO.read(new File(path));
		System.out.println("send image" + path);
		response.setHeader("Content-Type", "image/jpg");// or png or gif, etc
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		/////////////////
		URL url = new URL(baseURL);
		BufferedImage originalImage = ImageIO.read(url.openStream());
		ImageIO.write(originalImage, "jpg", response.getOutputStream());
	 
	}
	
	
	
	
	
	
	
	
	
	
	
}