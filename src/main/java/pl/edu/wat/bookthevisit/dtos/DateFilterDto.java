package pl.edu.wat.bookthevisit.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DateFilterDto
{
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dateTo;
    private String spec;
}
