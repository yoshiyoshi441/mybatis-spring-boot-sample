package com.github.yoshiyoshi441.mapper;

import com.github.yoshiyoshi441.entity.AdditionalData;
import com.github.yoshiyoshi441.entity.FirstName;
import com.github.yoshiyoshi441.entity.LastName;
import com.github.yoshiyoshi441.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class UserMapperTest {
    @Autowired
    private UserMapper mapper;

    @Sql("classpath:data/user_data.sql")
    @Test
    public void save_and_find() {
        var firstName = new FirstName("John");
        var lastName = new LastName("Smith");
        var additionalData = new AdditionalData("some data", 31);
        var entity = new UserEntity(firstName, lastName, additionalData);
        var count = mapper.save(entity);

        assertThat(count, is(1));

        var actual = mapper.findById(entity.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(actual.get().getId(), is(2L));
        assertThat(actual.get().getFirstName(), is(firstName));
        assertThat(actual.get().getLastName(), is(lastName));
        assertThat(actual.get().getAdditionalData(), is(additionalData));
        assertThat(actual.get().getCreatedAt(), is(not(nullValue())));
        assertThat(actual.get().getUpdatedAt(), is(not(nullValue())));

        var missing = mapper.findById(9L);
        assertThat(missing.isPresent(), is(false));
    }
}