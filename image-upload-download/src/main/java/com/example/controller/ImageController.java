package com.example.controller;
import com.example.dao.Image;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.el.stream.Optional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.service.ImageService;


@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/")
	public String uploadPage() {
		return "upload";
	}
	
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("file") CommonsMultipartFile file, Model model) throws IOException {
	    // Check if the file is null or empty
	    if (file == null || file.isEmpty()) {
	        model.addAttribute("message", "Please select a file to upload");
	        return "upload";
	    }

	    // Create a new Image object
	    Image image = new Image();
	    image.setName(file.getOriginalFilename());
	    image.setType(file.getContentType());
	    image.setImage(file.getBytes());

	    // Check if imageService is properly initialized
	    if (imageService == null) {
	        throw new IllegalStateException("ImageService is not initialized");
	    }

	    // Save the image
	    imageService.saveImage(image);

	    // Convert image to Base64
	    String base64Image = Base64.encodeBase64String(image.getImage());

	    // Add attributes to the model
	    model.addAttribute("image", base64Image);
	    model.addAttribute("message", "File uploaded successfully!");

	    System.out.println("Base64 value: " + base64Image);
	    return "upload";
	}	
	
	@GetMapping("/download-page")
	public String downloadPage() {
		return "download";
	}
	
	
	
	
	@GetMapping("/view-image")
	public ResponseEntity<byte[]> viewImage(@RequestParam long id) {
	    java.util.Optional<Image> imageData = imageService.getImage(id);
	    
	    if (imageData.isPresent()) {
	        Image image = imageData.get();
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(image.getType()))
	                .body(image.getImage());
	    } else {
	        System.err.println("Image not found for ID: " + id);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	    }
	}

	
	@GetMapping("/download-image/{id}")
	public void downloadImage(@PathVariable long id, HttpServletResponse response) throws IOException {
	    java.util.Optional<Image> imageData = imageService.getImage(id);
	    
	    if (imageData.isPresent()) {
	        Image image = imageData.get();
	        
	        System.out.println("Downloading image: " + image.getName() + ", Type: " + image.getType());
	        
	        response.setContentType(image.getType());
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"");
	        
	        response.getOutputStream().write(image.getImage());
	        response.getOutputStream().flush(); 
	    } else {
	        System.err.println("Image not found for ID: " + id);
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found!");
	    }
	}

}


