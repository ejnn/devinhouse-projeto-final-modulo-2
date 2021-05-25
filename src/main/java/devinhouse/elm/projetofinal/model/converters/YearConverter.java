package devinhouse.elm.projetofinal.model.converters;

import javax.persistence.*;

import java.time.Year;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, String>{

    @Override
    public String convertToDatabaseColumn(Year year) {
	return year.toString();
    }

    @Override
    public Year convertToEntityAttribute(String year) {
	return Year.parse(year);
    }
}
