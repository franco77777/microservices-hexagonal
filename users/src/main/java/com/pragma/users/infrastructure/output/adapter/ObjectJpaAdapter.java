package com.pragma.users.infrastructure.output.adapter;

import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IObjectPersistencePort;
import com.pragma.users.infrastructure.exception.NoDataFoundException;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.mapper.IObjectEntityMapper;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import com.pragma.users.infrastructure.output.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
            throw new NoDataFoundException();
        }
        return objectEntityMapper.toUserModelList(entityList);
    }

    @Override
    public UserModel emailExists(String email) {
        UserEntity user =objectRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email not found"));
        return objectEntityMapper.toUserModel(user);
         //.orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email))
    }



}
