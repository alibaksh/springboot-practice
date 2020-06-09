package com.springbootpractice.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.springbootpractice.restservices.dtos.UserMapStructsDto;
import com.springbootpractice.restservices.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "email", target = "emailAddress")
	UserMapStructsDto userToUserDto(User input);

	List<UserMapStructsDto> userListToUserDtoList(List<User> input);
}
