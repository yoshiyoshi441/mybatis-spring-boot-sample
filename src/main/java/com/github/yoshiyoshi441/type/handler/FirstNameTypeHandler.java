package com.github.yoshiyoshi441.type.handler;

import com.github.yoshiyoshi441.entity.FirstName;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@MappedTypes(FirstName.class)
public class FirstNameTypeHandler extends BaseTypeHandler<FirstName> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FirstName parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public FirstName getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Optional.ofNullable(rs.getString(columnName))
                .map(FirstName::new)
                .orElse(null);
    }

    @Override
    public FirstName getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Optional.ofNullable(rs.getString(columnIndex))
                .map(FirstName::new)
                .orElse(null);
    }

    @Override
    public FirstName getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Optional.ofNullable(cs.getString(columnIndex))
                .map(FirstName::new)
                .orElse(null);
    }
}
