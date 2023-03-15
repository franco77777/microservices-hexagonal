package com.pragma.users.infrastructure.output.adapter;

import com.pragma.users.domain.exception.DomainException;
import com.pragma.users.domain.model.AuthorityNameModel;
import com.pragma.users.domain.model.TokenModel;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.infrastructure.exception.InfrastructureException;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.mapper.ITokenDtoMapper;
import com.pragma.users.infrastructure.output.mapper.IUserEntityMapper;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
import com.pragma.users.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper objectEntityMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ITokenDtoMapper tokenDtoMapper;



    @Override
    public TokenModel saveUser(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserEntity userEntity = userRepository.save(objectEntityMapper.toEntity(userModel));
        TokenDto token = jwtService.registerToken(userEntity);
        return tokenDtoMapper.toModel(token);

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
    public TokenModel userLogin(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(()-> new InfrastructureException("User not found: " + email, HttpStatus.NOT_FOUND));
        if(!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new InfrastructureException("Wrong password", HttpStatus.BAD_REQUEST);
        }
        TokenDto tokenDto = jwtService.authenticate(userEntity);
        return tokenDtoMapper.toModel(tokenDto);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel getUser(Long userId) {
        return objectEntityMapper.toUserModel(userRepository.findById(userId).orElseThrow(
                ()-> new InfrastructureException("User not found",HttpStatus.NOT_FOUND)));
    }

    @Override
    public TokenModel validateToken(String token) {
        TokenDto tokenDto = jwtService.validate(token);
        return tokenDtoMapper.toModel(tokenDto);
    }

    @Override
    public UserModel saveOwner(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
       UserEntity userEntity = userRepository.save(objectEntityMapper.toEntity(userModel));
        return objectEntityMapper.toUserModel(userEntity);
    }


}
