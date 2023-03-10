package com.pragma.users.infrastructure.output.adapter;

import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.infrastructure.exception.InfrastructureException;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.mapper.IUserEntityMapper;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper objectEntityMapper;



    @Override
    public UserModel saveUser(UserModel userModel) {


        UserEntity userEntity = userRepository.save(objectEntityMapper.toEntity(userModel));
        return objectEntityMapper.toUserModel(userEntity);

    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> entityList = userRepository.findAll();
        if(entityList.isEmpty()){
            throw new InfrastructureException("No users found",HttpStatus.NOT_FOUND );
        }
        return objectEntityMapper.toUserModelList(entityList);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isEmpty();

         //.orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email))
    }

    @Override
    public UserModel userExists(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(()-> new InfrastructureException("User not found: " + email, HttpStatus.NOT_FOUND));
        return objectEntityMapper.toUserModel(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
