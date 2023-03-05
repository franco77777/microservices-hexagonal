package com.pragma.users.infrastructure.output.adapter;

import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IObjectPersistencePort;
import com.pragma.users.infrastructure.exception.InfrastructureException;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.mapper.IObjectEntityMapper;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import com.pragma.users.infrastructure.output.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@RequiredArgsConstructor
public class ObjectJpaAdapter  implements IObjectPersistencePort {
    private final IUserRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    private final UserService service;

    @Override
    public UserModel saveObject(UserModel userModel, AuthorityName roles) {
//        ObjectEntity entityMapped = new ObjectEntity();
//        BeanUtils.copyProperties(objectModel, entityMapped);

        userModel.setRoles(roles);
        UserEntity userEntity = service.register(objectEntityMapper.toEntity(userModel));

        return objectEntityMapper.toUserModel(userEntity);

        }

    @Override
    public List<UserModel> getAllObjects() {
        List<UserEntity> entityList = objectRepository.findAll();
        if(entityList.isEmpty()){
            throw new InfrastructureException("No users found",HttpStatus.NOT_FOUND );
        }
        return objectEntityMapper.toUserModelList(entityList);
    }

    @Override
    public boolean emailExists(String email) {
        return objectRepository.findByEmail(email).isEmpty();

         //.orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email))
    }

    @Override
    public UserModel userExists(String email) {
        UserEntity userEntity = objectRepository.findByEmail(email).orElseThrow(()-> new InfrastructureException("User not found: " + email, HttpStatus.NOT_FOUND));
        return objectEntityMapper.toUserModel(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        objectRepository.deleteById(id);
    }


}
