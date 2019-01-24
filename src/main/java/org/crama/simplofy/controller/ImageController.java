package org.crama.simplofy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.crama.simplofy.controller.dto.ImageLink;
import org.crama.simplofy.error.NoIdException;
import org.crama.simplofy.service.ImageService;
import org.crama.simplofy.utils.BaseUrlExtractor;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/image/upload", method=RequestMethod.POST, consumes = {"multipart/form-data"})
	@ResponseBody
    public ImageLink uploadImage(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file, HttpServletRequest req) 
    		throws NoIdException, NoSuchMessageException, InterruptedException {
		
		logger.info("file: " + file.getOriginalFilename());
		
		/*StringBuffer url = req.getRequestURL();
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String base = url.substring(0, url.length() - uri.length() + ctx.length());
		
		logger.info("base: " + base);
		
		String baseUrl = BaseUrlExtractor.getBaseEnvLinkURL();*/
		
		ImageLink link = imageService.upload(file);
		Thread.sleep(5000);
        return link;
    }
	
	
	/*@RequestMapping(value="/image/upload", method=RequestMethod.POST, consumes = {"multipart/form-data"})
	@ResponseBody
    public Map<Object, Object> uploadImage(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file, HttpServletRequest request) throws NoIdException, NoSuchMessageException, InterruptedException {
		
		String fileRoute = "/images/";
		 
        Map<Object, Object> responseData;
        try {
            responseData = Image.upload(request, fileRoute); // Use default
                                                                // image
                                                                // validation.
        } catch (Exception e) {
            e.printStackTrace();
            responseData = new HashMap<Object, Object>();
            responseData.put("error", e.toString());
        }
        
        Thread.sleep(5000);
        
        return responseData;
    }*/
	
	
}
