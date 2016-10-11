package hello;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
    	System.out.println("convertToDatabaseColumn :"+date);
        Instant instant = Instant.from(date);
    	System.out.println("convertToDatabaseColumn Date :"+Date.from(instant));
        return Date.from(instant);
    	//Date d=new Date(""+date.getYear()+"-"+(date.getMonthValue()+1)+"-"+date.getDayOfMonth());
    	
    	//return d;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
    	 //Instant instant = value.toInstant();
         //return LocalDate.from(instant);
    	System.out.println("convertToEntityAttribute :"+value);
    	LocalDate ld=LocalDate.of(value.getYear(), value.getMonth()+1, value.getDay());
    	System.out.println("convertToEntityAttribute LocalDate:"+value.toInstant());
    	System.out.println("convertToEntityAttribute LocalDate:"+LocalDate.now());
        //return ld;
        return LocalDate.now();
    }
}