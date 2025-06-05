package az.code.copart.dto.response;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

@Data
public class PageableResponse  <T> implements Serializable {
    private int pageNumber;
    private int pageSize;
    private int numberOfElements;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isFirst;
    private boolean isLast;
    private List <T> content;
}
