package com.github.yoshiyoshi441.type.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@MappedTypes(ZonedDateTime.class)
public class ZonedDateTimeTypeHandler extends BaseTypeHandler<ZonedDateTime> {
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Tokyo");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ZonedDateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, Timestamp.valueOf(parameter.toLocalDateTime()));
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Optional.ofNullable(rs.getTimestamp(columnName))
                .map(Timestamp::toLocalDateTime)
                .map(s -> s.atZone(ZONE_ID))
                .orElse(null);
    }

    @Override
    public ZonedDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Optional.ofNullable(rs.getTimestamp(columnIndex))
                .map(Timestamp::toLocalDateTime)
                .map(s -> s.atZone(ZONE_ID))
                .orElse(null);
    }

    @Override
    public ZonedDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Optional.ofNullable(cs.getTimestamp(columnIndex))
                .map(Timestamp::toLocalDateTime)
                .map(s -> s.atZone(ZONE_ID))
                .orElse(null);
    }
}
