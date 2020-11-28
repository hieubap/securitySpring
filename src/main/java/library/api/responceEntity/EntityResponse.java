package library.api.responceEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class EntityResponse<T> {
    private int status;
    private Timestamp time;
    private String message;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private T data;


    public EntityResponse(int status, Timestamp time, String message, T data) {
        this.status = status;
        this.time = time;
        this.message = message;
        this.data = data;
    }
    public EntityResponse(HttpStatus status, String message, T data) {
        this.status = status.value();
        this.time = new Timestamp(System.currentTimeMillis());
        this.message = message;
        this.data = data;
    }


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
