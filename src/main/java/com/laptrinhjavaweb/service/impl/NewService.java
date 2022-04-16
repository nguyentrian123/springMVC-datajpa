package com.laptrinhjavaweb.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService {
	

	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;
	
	@Autowired
	ServletContext application;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for(NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
			
		}
		return models;
	}
	
	
	@Override
	public List<NewDTO> findByCategoryCode(String code,Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> list = newRepository.findByCategoryCodeContaining(code,pageable);
		
		for(NewEntity item : list) {
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
			
		}

		return models;
	}

	



	@Override
	public List<NewDTO> findByTitleContaining(String name,Pageable pageable) {
		List<NewDTO> models =  new ArrayList<>();
		List<NewEntity> list = newRepository.findByTitleContaining(name,pageable);
		
		for(NewEntity item : list) {
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
		}

		return models;
		
	}
	
	// Laasy toong
	@Override
	public int getTotalItem() {

		return (int)newRepository.count();
	}
	
	//take Sum follow categorycode
	@Override
	public int getTotalItems(String code) {
		
		List<NewEntity> list = newRepository.findByCategoryCodeContaining(code);
		int s = 0;
		for(NewEntity item : list) {
			s = s+1;
			
		}
		return s;
	}
	
	//take Sum follow title 
	@Override
	public int getTotalItem(String title) {
		
		List<NewEntity> list = newRepository.findByTitleContaining(title);
		int s = 0;
		for(NewEntity item : list) {
			s = s+1;
			
		}
		return s;
	}

	

	@Override
	public NewDTO findById(long id) {
		NewEntity entity = newRepository.findOne(id);// dc cung cap sang
		return newConverter.toDTO(entity); // đưa từ entity vào dto
	}
	
	
	/*@Override
	@Transactional // commit + rollback = @transaction
	public NewDTO insert(NewDTO newDto) {
		CategoryEntity  category = categoryRepository.findOneByCode(newDto.getCategoryCode());
		NewEntity newEntity = newConverter.toEntity(newDto);
		newEntity.setCategory(category);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}

	@Override
	@Transactional
	public NewDTO update(NewDTO dto) {
		NewEntity oldNew = newRepository.findOne(dto.getId());
		CategoryEntity  category = categoryRepository.findOneByCode(dto.getCategoryCode());
		oldNew.setCategory(categoryRepository.findOneByCode(dto.getCategoryCode()));
		NewEntity updateNew = newConverter.toEntity(oldNew, dto);
		oldNew.setCategory(categoryRepository.findOneByCode(dto.getCategoryCode()));
		
		return newConverter.toDTO(newRepository.save(oldNew));
	}
*/
	
	@Override
	@Transactional  // commit + rollback = @transaction
	public NewDTO save(NewDTO dto) {
		CategoryEntity  category = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		
		//MultipartFile file = (MultipartFile)dto.getThumbnail();
		
		// update
		if(dto.getId()!= null) 
		{
			NewEntity oldNew = newRepository.findOne(dto.getId());
			oldNew.setCategory(category);
			newEntity = newConverter.toEntity(oldNew, dto);
		}
		// create
		else 
		{
			newEntity = newConverter.toEntity(dto);
			//newEntity.setThumbnail(saveFile(dto.getThumbnail()));
			newEntity.setCategory(category);
			
		}
		return newConverter.toDTO(newRepository.save(newEntity));
	}

	
	
	
	
	private String saveFile(MultipartFile file)
	{
		if(null != file && !file.isEmpty())
		{
			try {
				byte[] bytes = file.getBytes();
				// lấy đường dẫn thư mục
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath+ File.separator+"image");
				if(!dir.exists())
				{
					// tạo đường dẫn đến thư mục mong muốn
					dir.mkdir(); 
				}
				
				String name = String.valueOf( new Date().getTime()+".jpg");
				File serverFile  = new File(dir.getAbsoluteFile()+ File.separator + name);
				
				// Ghi dữ liệu vào OS
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("file not found");
		}
		
		return null;
	}

	
	@Override
	@Transactional
	public void delete(long []ids) {
		for(long id: ids)
		{
			newRepository.delete(id);
		}
		
	}

	@Override
	public List<NewDTO> findAlls() {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll();
		for(NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);	
		}
		return models;
	}


	
	

	
}
