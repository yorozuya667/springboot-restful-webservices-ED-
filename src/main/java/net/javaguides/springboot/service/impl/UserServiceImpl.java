package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AutoUserMapper mapper;   // <-- инжектим бин

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.toUser(userDto);                 // было AutoUserMapper.MAPPER...
        User saved = userRepository.save(user);
        return mapper.toUserDto(saved);                     // было AutoUserMapper.MAPPER...
    }

    @Override
    public UserDto getUserByID(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(mapper::toUserDto)                     // было через MAPPER
                .toList();
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        User existing = userRepository.findById(dto.getId()).orElseThrow();
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        return mapper.toUserDto(userRepository.save(existing));
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
