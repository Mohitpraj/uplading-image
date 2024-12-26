package com.example.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.dao.Image;
import com.example.repository.ImageRepository;
import com.example.util.DatabaseConnection;

@Service
@Component
public class ImageService {
	

//	private ImageRepository imageRepository;
//	
//	@Autowired 
//    public ImageService(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }
	
	
//	public void saveImage(CommonsMultipartFile file)throws IOException{
//		Image image = new Image();
//		image.setName(file.getOriginalFilename());
//		image.setType(file.getContentType());
//		image.setImage(file.getBytes());
//		imageRepository.save(image);
//	}
	
	public Optional<Image> getImage(Long id){
		String sql="SELECT * FROM image where id=?";
		Image image=null;
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(sql)){
			
			preparedStatement.setLong(1, id);
			ResultSet resultset=preparedStatement.executeQuery();
			if(resultset.next()) {
				image = new Image();
				image.setId(resultset.getLong("id"));
				image.setName(resultset.getString("name"));
				image.setType(resultset.getString("type"));
				image.setImage(resultset.getBytes("image"));
						
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
		return Optional.ofNullable(image);
		
	}
	public void saveImage(Image image) {
		String sql ="INSERT INTO image(name,type,image) VALUES(?,?,?)";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(sql)){
			
			preparedStatement.setString(1,image.getName());
			preparedStatement.setString(2, image.getType());
			preparedStatement.setBytes(3,image.getImage());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
		
	}

