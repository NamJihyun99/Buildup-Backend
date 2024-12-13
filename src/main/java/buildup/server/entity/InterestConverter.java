package buildup.server.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.EnumSet;

@Converter
public class InterestConverter implements AttributeConverter<EnumSet<Interest>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(EnumSet<Interest> attributes) {
        StringBuilder value = new StringBuilder();
        if (attributes == null || attributes.isEmpty()) {
            return "";
        }
        attributes.forEach(attribute -> value.append(attribute.name()).append(DELIMITER));
        if (value.charAt(value.length() - 1) == DELIMITER.charAt(0)) {
            value.deleteCharAt(value.length() - 1);
        }
        return value.toString();
    }

    @Override
    public EnumSet<Interest> convertToEntityAttribute(String dbData) {
        EnumSet<Interest> result = EnumSet.noneOf(Interest.class);
        if (dbData == null || dbData.isBlank()) {
            return result;
        }
        Arrays.stream(StringUtils.trimAllWhitespace(dbData).toUpperCase().split(DELIMITER))
                .forEach(token -> result.add(Interest.valueOf(token)));
        return result;
    }
}