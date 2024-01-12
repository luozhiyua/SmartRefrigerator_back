package mise.foodservice.models;

//import jakarta.persistence.*;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

//import javax.persistence.*;
import javax.persistence.*;
//import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "food")
public class Food implements Serializable{
//    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column
    private String userId;
    @DateTimeFormat
    @Column
    private Date freshDate;
    @Column
    private String quantity;
    @Column
    private Category category;
    @Column
    private String address;
}
