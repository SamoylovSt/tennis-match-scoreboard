package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;
}
