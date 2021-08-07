package com.github.yoshiyoshi441.type.handler;

import com.github.yoshiyoshi441.entity.LastName;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@MappedTypes(LastName.class)
public class LastNameTypeHandler extends BaseTypeHandler<LastName> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LastName parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public LastName getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Optional.ofNullable(rs.getString(columnName))
                .map(LastName::new)
                .orElse(null);
    }

    @Override
    public LastName getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Optional.ofNullable(rs.getString(columnIndex))
                .map(LastName::new)
                .orElse(null);
    }

    @Override
    public LastName getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Optional.ofNullable(cs.getString(columnIndex))
                .map(LastName::new)
                .orElse(null);
    }
}
