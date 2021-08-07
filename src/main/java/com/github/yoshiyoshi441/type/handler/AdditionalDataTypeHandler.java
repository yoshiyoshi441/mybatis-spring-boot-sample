package com.github.yoshiyoshi441.type.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yoshiyoshi441.entity.AdditionalData;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(AdditionalData.class)
public class AdditionalDataTypeHandler extends BaseTypeHandler<AdditionalData> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, AdditionalData parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, serialize(parameter));
    }

    @Override
    public AdditionalData getNullableResult(ResultSet rs, String columnName) throws SQLException {
        var data = rs.getString(columnName);
        if (data == null) {
            return null;
        }
        return deserialize(data);
    }

    @Override
    public AdditionalData getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        var data = rs.getString(columnIndex);
        if (data == null) {
            return null;
        }
        return deserialize(data);
    }

    @Override
    public AdditionalData getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        var data = cs.getString(columnIndex);
        if (data == null) {
            return null;
        }
        return deserialize(data);
    }

    private AdditionalData deserialize(String data) throws SQLException {
        try {
            return OBJECT_MAPPER.readValue(data, AdditionalData.class);
        } catch (JsonProcessingException e) {
            throw new SQLException(e);
        }
    }

    private String serialize(Object data) throws SQLException {
        try {
            return OBJECT_MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new SQLException(e);
        }
    }
}
