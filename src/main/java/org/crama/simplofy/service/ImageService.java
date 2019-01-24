package org.crama.simplofy.service;

import java.util.UUID;

import javax.servlet.ServletContext;

import org.crama.simplofy.controller.dto.ImageLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
	
	public ImageLink upload(MultipartFile file) {
	
		String linkName = null;
		String type = file.getContentType();
        type = type.substring(type.lastIndexOf("/") + 1);

        // Generate random name.
        String extension = type;
        extension = (extension != null && extension != "") ? "." + extension : extension;
        String name = UUID.randomUUID().toString() + extension;
 
        try {
            file.transferTo(resourceLoader.getResource("/images/" + name).getFile());

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        linkName = "/images/" + name;
        ImageLink link = new ImageLink();
        link.setLink(linkName);
        
        logger.info(link.toString());
        
        return link;
        
	}
        /*try {
           
            // Get file type
            String type = file.getContentType();
            type = type.substring(type.lastIndexOf("/") + 1);
 
            // Generate random name.
            String extension = type;
            extension = (extension != null && extension != "") ? "." + extension : extension;
            name = UUID.randomUUID().toString() + extension ;
 
            // Get absolute server path.
            String path = servletContext.getRealPath("");
            
            
            logger.info("path: " + path);
            
            String absoluteServerPath = path + "/" + name;
            
            File uploads = new File("/images");
 
            // Create link.
            //String path = request.getHeader("referer");
            linkName = "/images/" + name;
 
            // Save the file on server.
 
            //File savedFile = new File(absoluteServerPath);
            
            //TODO delete
            File savedFile = new File(uploads, name);
 
            try (InputStream input = file.getInputStream()) {
            	
                Files.copy(input, savedFile.toPath());
            } catch (Exception e) {
            	e.printStackTrace();
            	logger.error("Exception while saving file!!");
            }
            
            ImageLink link = new ImageLink();
            link.setLink(linkName);
            
            return link;
 
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("You either did not specify a file to upload or are " +
                "trying to upload a file to a protected or nonexistent " +
                "location.");
            
        }*/
		
        
	

	
	
}
