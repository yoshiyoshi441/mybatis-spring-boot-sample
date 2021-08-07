package com.github.yoshiyoshi441.mapper;

import com.github.yoshiyoshi441.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Mapper
@Repository
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({"INSERT INTO `test`.`user` (`first_name`, `last_name`, `additional_data`)",
            "VALUES(#{firstName}, #{lastName}, #{additionalData})"})
    public int save(UserEntity entity);

    @Select({"SELECT * FROM `test`.`user`"})
    public Set<UserEntity> findAll();

    @Select({"SELECT * FROM `test`.`user` WHERE `id` = #{id}"})
    public Optional<UserEntity> findById(Long id);
}
