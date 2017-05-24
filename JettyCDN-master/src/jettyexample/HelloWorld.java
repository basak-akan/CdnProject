package jettyexample;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
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
	private Hashtable<String, BufferedImage> imgList = new Hashtable<String, BufferedImage>();
	private BufferedImage sourceFile = null;
	
    @Override
    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
                                                      ServletException
    {
    	Map<String, String[]> map = request.getParameterMap();
    	//MAP KONTROLU
		if (imgList.containsKey(target)) {
			try {

				if (map.size() == 0) {
					response.setHeader("Content-Type", "image/jpg");
					ImageIO.write(imgList.get(target), "jpg", response.getOutputStream());
				}

				else if (map.size() == 1) {
					// gray işlemi
					BufferedImage sendImg = new Scalar().toGray(imgList.get(target));
					response.setHeader("Content-Type", "image/jpg");
					ImageIO.write(sendImg, "jpg", response.getOutputStream());

				} else if (map.size() == 2) {
					// scale işlemi
					BufferedImage sendImg = new Scalar().scale(imgList.get(target),
							Integer.parseInt(map.get("width")[0]), Integer.parseInt(map.get("height")[0]));
					response.setHeader("Content-Type", "image/jpg");
					ImageIO.write(sendImg, "jpg", response.getOutputStream());

				} else {
					// gray&scale

					BufferedImage sendImg = new Scalar().grayAndScale(imgList.get(target),
							Integer.parseInt(map.get("width")[0]), Integer.parseInt(map.get("height")[0]));
					response.setHeader("Content-Type", "image/jpg");
					ImageIO.write(sendImg, "jpg", response.getOutputStream());
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sourceFile =ImageIO.read( new File("path+target.jpg"));
		 if (sourceFile!=null)
		{
			 response.setHeader("Content-Type", "image/jpg");
				ImageIO.write(sourceFile, "jpg", response.getOutputStream());
		}
		// else ?? file için nasıl kontrolleri bastan yapcez
			 
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
		///////////////// en son olcak iş
		URL url = new URL(baseURL);
		BufferedImage originalImage = ImageIO.read(url.openStream());
		ImageIO.write(originalImage, "jpg", response.getOutputStream());
	 
	}
	
	
	
	
	
	
	
	
	
	
	
}