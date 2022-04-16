package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;

@Component 
public class NewConverter {
	
	
	// chuyển lại DTO để lưu vào db
	public NewDTO toDTO(NewEntity entity)
	{
		NewDTO result = new NewDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setShortDescription(entity.getShortDescription());
		result.setContent(entity.getContent());
		result.setThumbnail(entity.getThumbnail());
		result.setCategoryCode(entity.getCategory().getCode());
		return result;
		
	}
	
	public NewEntity toEntity(NewDTO dto)
	{
		NewEntity result = new NewEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		//result.setThumbnail(dto.getThumbnail());
		
		return result;
	}
	
	// tạo phương thức mới cho New cũ , dùng tính chất OVERLOADING
	// set laj content cho NewEntity(model ánh xạ vs csdl)
	public NewEntity toEntity(NewEntity result,NewDTO dto)
	{
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		//result.setThumbnail(dto.getThumbnail());
		
		return result;
	}
}
